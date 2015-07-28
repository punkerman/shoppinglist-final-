package com.redrex.alejandrobedoya.shoppinglist.BdConexion;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrex.alejandrobedoya.shoppinglist.R;

import java.sql.SQLException;

public class PromPricesCursorAdapter extends CursorAdapter {
    private MyListDbAdapter dbAdapter =  null;
    public PromPricesCursorAdapter(Context context, Cursor c) throws SQLException{
        super(context, c);
        dbAdapter = new MyListDbAdapter(context);
        dbAdapter.abrir();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.my_proprices_resultview,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView imageView = (ImageView) view.findViewById(R.id.imgPromPrices);
        Integer pricePoints = cursor.getInt(cursor.getColumnIndex(MyListContract.PromPricesResultEntry.Column_Points));
      if ( pricePoints == 30000){
          imageView.setImageResource(R.drawable.gold);
      }
        if ( pricePoints == 25000){
            imageView.setImageResource(R.drawable.silver);
        }
        if ( pricePoints == 20000){
            imageView.setImageResource(R.drawable.secadora);
        }
        if ( pricePoints == 15000){
            imageView.setImageResource(R.drawable.batidora);
        }
        if ( pricePoints == 13000){
            imageView.setImageResource(R.drawable.licuadora);
        }
        Log.d(null,cursor.getString(cursor.getColumnIndex(MyListContract.PromPricesResultEntry.Column_Price)));

        TextView item = (TextView) view.findViewById(R.id.textView2PromPrices);
        item.setText(cursor.getString(cursor.getColumnIndex(MyListContract.PromPricesResultEntry.Column_Price)));
        TextView item2 = (TextView) view.findViewById(R.id.textView1PromPrices);
        item2.setText(cursor.getString(cursor.getColumnIndex(MyListContract.PromPricesResultEntry.Column_Description)));
        TextView item3 = (TextView) view.findViewById(R.id.textView3PromPrices);
        item3.setText(cursor.getString(cursor.getColumnIndex(MyListContract.PromPricesResultEntry.Column_Points)));

    }
}
