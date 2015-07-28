package com.redrex.alejandrobedoya.shoppinglist;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListContract;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListDbAdapter;

import java.sql.SQLException;
import java.util.List;


public class ProductsAdaper extends ArrayAdapter<Product> {

    private MyListDbAdapter ldbAdapter;

    private EditText cantidad;
    public DownloadPics downloadPics;
        private List<Product> items;
        public ProductsAdaper(Context context, List<Product> items) {
            super(context, R.layout.resultview, items); this.items = items;
            downloadPics = new DownloadPics(this.getContext());
        }
        @Override
        public int getCount() {
            return items.size();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater li = LayoutInflater.from(getContext());
                v = li.inflate(R.layout.resultview, null);
            }
            final Product product = items.get(position);
            if (product != null) {
                // producto
                TextView item = (TextView)v.findViewById(R.id.txt_producto);
                item.setText(String.valueOf(product.getProduct()));
                // marca
                TextView marca = (TextView)v.findViewById(R.id.txt_marca);
                marca.setText(product.getBrand());
                // precio total
                TextView precio= (TextView)v.findViewById(R.id.txtvw_precio);
                precio.setText(product.getPrice()+' '+product.getRelation());
                // imagen
                ImageView imagen = (ImageView) v.findViewById(R.id.imgProduct); System.out.println("***** URL IMAGEN:" + product.getImage());
                downloadPics.DisplayImage(product.getImage(), imagen);
                //boton mas
                ImageButton plusBtn = (ImageButton) v.findViewById(R.id.choose);
                plusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ldbAdapter = new MyListDbAdapter(getContext());
                        try {
                            ldbAdapter.abrir();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        LayoutInflater li = LayoutInflater.from(getContext());
                        View prompt = li.inflate(R.layout.product_confirmation, null);
                        final EditText ins= (EditText) prompt.findViewById(R.id.cant);
                        builder.setView(prompt);
                        builder.setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                     final   Double cant= Double.parseDouble(ins.getText().toString());
                                     final   ContentValues reg = new ContentValues();

                                        reg.put(MyListContract.ResultEntry.Column_Item,String.valueOf(product.getProduct()));
                                        reg.put(MyListContract.ResultEntry.Columm_Cant,cant);
                                        reg.put(MyListContract.ResultEntry.Column_Relation,String.valueOf(product.getRelation()));
                                        reg.put(MyListContract.ResultEntry.Column_Position,String.valueOf(product.getPosition()));


                                        try {
                                            ldbAdapter.insert(reg);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }

                                        Toast.makeText(getContext(), "item agregado" , Toast.LENGTH_LONG).show();
                                        //  agregar a base de datos


                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Cancelamos el cuadro de dialogo
                                        dialog.cancel();
                                    }
                                });
                        builder.create();
                        builder.show();
                    }
                });
            }
            return v;
        }



}
