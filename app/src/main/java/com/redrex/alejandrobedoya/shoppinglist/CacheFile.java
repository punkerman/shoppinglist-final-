package com.redrex.alejandrobedoya.shoppinglist;

import android.content.Context;

import java.io.File;

/**
 * Created by alejandro on 4/12/2015.
 */
public class CacheFile {
    private File cacheDir;
    public CacheFile(Context context) {
        // Buscar el directorio en la SD para salvar las imágenes
        if (android.os.Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED)) {
            // Si la SD está montada
            cacheDir = new File( android.os.Environment.getExternalStorageDirectory(), "SERVTEC");
        } else {
            // Si no está montada, crear el directorio en el contexto
            // de nuesta app
            cacheDir = context.getCacheDir();
        }
        if (!cacheDir.exists()) {
            // Crear el directorio
            cacheDir.mkdirs();
        }
    }
    public File getFile(String url) {
        // Identificar las imágenes por hash
        String filename = String.valueOf(url.hashCode());
        File f = new File(cacheDir, filename);
        return f;
    }
    public void clear() {
        // Listar todos los archivos de la cache
        File[] files = cacheDir.listFiles();
        if (files == null) return;
        // Borrar todos los archivos del directorio
        for (File f : files) f.delete();
    }
}
