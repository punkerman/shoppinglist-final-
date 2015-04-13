package com.redrex.alejandrobedoya.shoppinglist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alejandro on 4/12/2015.
 */
public class DownloadPics {
    // Inicializar MemoriaCache
    CacheMemory memoryCache = new CacheMemory();
    CacheFile archivoCache;
    // Creamos una colección (map) para guardar los pares de datos
// imagen y su respectiva URL
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    ExecutorService executorService;
    // handler para mostrar la imagen en la UI
    Handler handler = new Handler();
    public DownloadPics(Context context) {
        archivoCache = new CacheFile(context);
// Creamos un grupo de subprocesos que utiliza un numero
// fijo de hilos que bajarán las diferentes imágenes encoladas
        executorService = Executors.newFixedThreadPool(5);
    }
    // Esta es la imagen por defecto para mostrar
// mientras se baja la que corresponde, o en caso
// de que ocurra algun error en la bajada
    final int stub_id = R.drawable.nodisponible;
    public void DisplayImage(String url, ImageView imageView) {
// Guardamos la imagen y su URL en la collección
        imageViews.put(imageView, url);
// Verificamos si la imagen se encuentra en cache
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null) {
// Si la imagen la tenemos en nuestra SD, la mostramos
            imageView.setImageBitmap(bitmap);
        } else {
// encolamos para bajar la URL de la imagen
            queuePhoto(url, imageView);
// Y antes de comenzar a bajar, mostramos la imagen por defecto
            imageView.setImageResource(stub_id);
        }
    }
    private void queuePhoto(String url, ImageView imageView) {
// Guardamos la imagen y su url en un objeto PhotoToLoad
        PhotoToLoad p = new PhotoToLoad(url, imageView);
// Pasamos el objeto PhotoToLoad a la clase PhotosLoader
// y enviamos PhotosLoader al executor para realizar la tarea
// del bajado de fotos
        executorService.submit(new PhotosLoader(p));
    }
    // Tarea a encolar
    private class PhotoToLoad {
        public String url;
        public ImageView imageView;
        public PhotoToLoad(String u, ImageView i) {
            url = u; imageView = i;
        }
    }
    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;
        PhotosLoader(PhotoToLoad photoToLoad) {
            this.photoToLoad = photoToLoad;
        }
        @Override
        public void run() {
            try {
// Verificamos si la imagen ya está bajado
                if (imageViewReused(photoToLoad)) return;
// bajamos la imagen desde su URL
                Bitmap bmp = getBitmap(photoToLoad.url);
// Guadamos la imagen en la cache
                memoryCache.put(photoToLoad.url, bmp);
                if (imageViewReused(photoToLoad)) return;
// Obtenemos el Bitmap a mostrar
                BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);
// Hacemos que el BitmapDisplayer se agregue a la cola de mensajes
// Este se ejecutará en un un subproceso separado asociado al handler
//que definimos inicialmente.
                handler.post(bd);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
    private Bitmap getBitmap(String url) {
        File f = archivoCache.getFile(url);
// Verificamos si estamos tratando de decodificar un archivo que
// exite en nuestra cache
        Bitmap b = decodeFile(f);
        if (b != null) return b;
// OK, no existe. Bajamos la imagen desde la web
        try {
            Bitmap bitmap = null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl .openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();
// Creamos un nuevo FileOuputStream para guardar nuestro archivo.
            OutputStream os = new FileOutputStream(f);
// Cada pixel bajado de la web, lo pasamos a nuestro
//nuevo archivo
            utils.CopyStream(is, os);
            os.close();
            conn.disconnect();
// Tenemos el archivo. Vamos a redimensionarlo para reducir
// el consumo de memoria. Esto previene que si la url apunta
// a una foto sacada con una cámara de 20 megapixels,
// guardemos un archivo mountroso
            bitmap = decodeFile(f);
            return bitmap;
        } catch (Throwable ex) {
            ex.printStackTrace();
            if (ex instanceof OutOfMemoryError) memoryCache.clear();
            return null;
        }
    }
    // Decodifica la imagen y la reduce a un tamaño adecuado
    private Bitmap decodeFile(File f) {
        try {
// Decodifica la imagen
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            FileInputStream stream1 = new FileInputStream(f);
            BitmapFactory.decodeStream(stream1, null, o);
            stream1.close();
// Seteamos el ancho (y la altura) que tendrá la imagen.
// Debe ser divisible por 2
            final int REQUIRED_SIZE = 100;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1; while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
// Decodificar con los valores actuales luego de redimensionar
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            FileInputStream stream2 = new FileInputStream(f);
            Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, o2);
            stream2.close();
            return bitmap;
        }
        catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    boolean imageViewReused(PhotoToLoad photoToLoad) {
        String tag = imageViews.get(photoToLoad.imageView);
// Verificar si la url ya existe en nuestra collección
        if (tag == null || !tag.equals(photoToLoad.url)) return true;
        return false;
    }
    // Usada para mostrar la imagen en UI
    class BitmapDisplayer implements Runnable {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;
        public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
            bitmap = b; photoToLoad = p;
        }
        public void run() {
            if (imageViewReused(photoToLoad)) return;
// Mostrar el bitmap en UI
            if (bitmap != null) photoToLoad.imageView.setImageBitmap(bitmap);
            else photoToLoad.imageView.setImageResource(stub_id);
        }
    }
    public void clearCache() {
// Borrar la cache
        memoryCache.clear();
        archivoCache.clear();
    }
}
