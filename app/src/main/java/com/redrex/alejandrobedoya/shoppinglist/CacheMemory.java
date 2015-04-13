package com.redrex.alejandrobedoya.shoppinglist;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by alejandro on 4/12/2015.
 */
public class CacheMemory {
    private static final String TAG = "MemoriaCache";

    private Map<String, Bitmap> cache = Collections.synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 1.5f, true));

    private long size=0;

    private long limit=100000000;
    public CacheMemory(){

        setLimit(Runtime.getRuntime().maxMemory()/4);
    }
    public void setLimit(long new_limit){
        limit=new_limit;
        Log.i(TAG, "MemoriaCache usará hasta " + limit / 1024. / 1024. + "MB en la SD");
    }
    //Lo usamos para obtener la imagen desde la cache
    public Bitmap get(String id){
        try{
            //Verificamos si existe el id de la imagen en la cache
            //Si no la bajamos previamente, devolver null
            if(!cache.containsKey(id))
                return null;
            //Si ya la salvamos, devolvemos la imagen
            return cache.get(id);
        }catch(NullPointerException ex){
            ex.printStackTrace();
            return null;
        }
    }
    //LO usamos para notificar que colocamos un archivo en la cache
    public void put(String id, Bitmap bitmap){
        try{
            //Verificamos si existe el id en la cache
            //Si existe, borramos el espacio informado
            if(cache.containsKey(id))
                size-=getSizeInBytes(cache.get(id));
            //Y agregamos el ítem al tope de la lista
            cache.put(id, bitmap);
            size+=getSizeInBytes(bitmap);
            checkSize();
        }catch(Throwable th){
            th.printStackTrace();
        }
    }
    private void checkSize() {
        Log.i(TAG, "Tamaño de la cache="+size+" length="+cache.size());
        //Verificamos si el tamaño actual es mayor que el límite
        if(size>limit){
            // El item menos accedido, se borra
            Iterator<Map.Entry<String, Bitmap>> iter=cache.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry<String, Bitmap> entry=iter.next();
                size-=getSizeInBytes(entry.getValue());
                iter.remove();
                if(size<=limit) break;
            }
            Log.i(TAG, "Se limpió la cache. El nuevo tamaño es "+cache.size());
        }
    }
    public void clear() {
        try{
            // Borrar cache
            cache.clear();
            size=0;
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }
    long getSizeInBytes(Bitmap bitmap) {
        if(bitmap==null) return 0;
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}

