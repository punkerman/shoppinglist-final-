package com.redrex.alejandrobedoya.shoppinglist;

import android.net.Uri;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandro on 4/11/2015.
 */
public class WebServiceProducts extends AsyncTask<String, Void, String> {
    private final Extractdata listener;
    private String category;
    private String msg;

    public WebServiceProducts(Extractdata listener,String category) {
        this.listener = listener; this.category=category;
    }

    @Override
    protected String doInBackground(String... params) {
        // Obtener la direccion de consulta

        String url = Uri.parse("http://servtec.esy.es/webService/easyShopping.php").buildUpon().appendQueryParameter("categoria",category).toString();

        try {
            // Crear conexión http
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            // Conectarse
            HttpResponse response = client.execute(httpget);
            // Obtener la respuesta
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                msg = "Sin respuesta desde el server";
                return null;
            }
            // Obtener el contenido de la respuesta y convertirlo a una cadena
            // json
            InputStream is = entity.getContent();
            return streamToString(is);
        } catch (IOException e) {
            msg = "Problemas en la conexión";
        }
        return null;

    }
    @Override
    protected void onPostExecute(String sJson) {
        if (sJson == null) {
            if (listener != null) listener.onFetchFailure(msg);
            return;
        }
        try {
            // Convertir la cadena json en un array json

            JSONObject jsonObject=new JSONObject(sJson);
            JSONArray aJson =jsonObject.getJSONArray("productos");

            // Crear la lista
            List<Product> prods = new ArrayList<Product>();
            for (int i = 0; i < aJson.length(); i++) {
                JSONObject json = aJson.getJSONObject(i);
                Product prod = new Product();
                // producto
                prod.setProduct(new String(json.getString("articulo").getBytes("ISO-8859-1"), "UTF-8"));
                // marca
                prod.setBrand(new String(json.getString("marca").getBytes("ISO-8859-1"), "UTF-8"));
                // precio
                prod.setPrice(new String(json.getString("precio").getBytes("ISO-8859-1"), "UTF-8"));
                // relacion
                prod.setRelation(new String(json.getString("relacion").getBytes("ISO-8859-1"), "UTF-8"));
                // imagen
                prod.setImage(json.getString("imagen"));
                prods.add(prod);
                // precio
            }
            // Notificar al listener que ya están todos lo datos
            if (listener != null) listener.onFetchComplete(prods);
        } catch (JSONException e) {
            msg = "Respuesta inválida";
            System.out.println("**** Error JSON" + e.getMessage());
            if (listener != null) listener.onFetchFailure(msg);
            return;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * Esta función convierte la respuesta en una cadena
     *
     * @param //es la string que obtuvimos como respuesta
     * @return devuelve una string json
     * @throws IOException
     */
    public String streamToString(final InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return sb.toString();
    }
}
