package com.redrex.alejandrobedoya.shoppinglist.BdConexion;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.redrex.alejandrobedoya.shoppinglist.R;

import java.sql.SQLException;

/**
 * Created by alejandro on 7/19/2015.
 */
public class MyPointsPricesCursorAdapter extends CursorAdapter{
    private MyListDbAdapter dbAdapter = null;


    public MyPointsPricesCursorAdapter(Context context,Cursor c) throws SQLException {
        super(context,c);
        dbAdapter = new MyListDbAdapter(context);
        dbAdapter.abrir();

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.my_points_resultview,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView item = (TextView) view.findViewById(R.id.textViewPoints);
        item.setText(cursor.getInt(cursor.getColumnIndex("total")));
    }
}
