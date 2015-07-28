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
 * Created by alejandro on 7/26/2015.
 */
public class MyListMapCursorAdapter extends CursorAdapter {
    private MyListDbAdapter dbAdapter = null;


    public MyListMapCursorAdapter(Context context, Cursor c)throws SQLException {
        super(context, c);
        dbAdapter = new MyListDbAdapter(context);
        dbAdapter.abrir();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.maplist_resultview,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView item = (TextView) view.findViewById(R.id.textViewMapList1);
        item.setText(cursor.getString(cursor.getColumnIndex(MyListContract.ResultEntry.Column_Item)));
        TextView item2 = (TextView) view.findViewById(R.id.textViewMapList2);
        item2.setText(cursor.getString(cursor.getColumnIndex(MyListContract.ResultEntry.Columm_Cant)));

    }
}