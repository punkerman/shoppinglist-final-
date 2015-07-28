package com.redrex.alejandrobedoya.shoppinglist.BdConexion;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrex.alejandrobedoya.shoppinglist.R;

import java.sql.SQLException;

public class MyListCursorAdapter extends CursorAdapter {
    private MyListDbAdapter dbAdapter = null ;

    public MyListCursorAdapter(Context context, Cursor c) throws SQLException {
        super(context, c);
        dbAdapter = new MyListDbAdapter(context);
        dbAdapter.abrir();
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.my_list_resultview, parent, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        /*
        TextView tv = (TextView) view ;

        tv.setText(cursor.getString(cursor.getColumnIndex(MyListContract.ResultEntry.Column_Item)));
        */

        TextView item= (TextView) view.findViewById(R.id.textViewArt);
        item.setText(cursor.getString(cursor.getColumnIndex(MyListContract.ResultEntry.Column_Item)));
        TextView cant= (TextView) view.findViewById(R.id.textViewCant);
        cant.setText(cursor.getString(cursor.getColumnIndex(MyListContract.ResultEntry.Columm_Cant)));
        TextView rel=(TextView) view.findViewById(R.id.textViewRel);
        rel.setText("UNIDADES");

    }
}
