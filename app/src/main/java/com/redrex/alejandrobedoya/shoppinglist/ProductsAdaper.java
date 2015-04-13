package com.redrex.alejandrobedoya.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


public class ProductsAdaper extends ArrayAdapter<Product> {

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
            Product product = items.get(position);
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
                ImageView imagen = (ImageView) v.findViewById(R.id.imgProduct); System.out.println("***** URL IMAGEN:" + product.getImage());   downloadPics.DisplayImage(product.getImage(), imagen);



            }
            return v;
        }
}
