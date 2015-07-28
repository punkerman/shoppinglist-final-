package com.redrex.alejandrobedoya.shoppinglist.BdConexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

import com.redrex.alejandrobedoya.shoppinglist.R;

import java.sql.SQLException;


public class MyListDbAdapter  {
    private Context contexto;
    private MyListHelper dbHelper;
    private SQLiteDatabase db;
    private String[] columnas = new String[]{MyListContract.ResultEntry._ID,MyListContract.ResultEntry.Column_Item,MyListContract.ResultEntry.Columm_Cant,MyListContract.ResultEntry.Column_Relation,MyListContract.ResultEntry.Column_Position};
    private String[] ColumnasPromPrices = new String[]{MyListContract.PromPricesResultEntry._ID,MyListContract.PromPricesResultEntry.Column_Price,MyListContract.PromPricesResultEntry.Column_Description,MyListContract.PromPricesResultEntry.Column_Points,MyListContract.PromPricesResultEntry.Column_Image};
    private String[] ColumnasMyPoints = new String[] {MyListContract.MyPointsResultEntry._ID,MyListContract.MyPointsResultEntry.Column_Points,MyListContract.MyPointsResultEntry.Column_Date};

    public MyListDbAdapter(Context context){

        this.contexto=context;
    }
    public MyListDbAdapter abrir() throws SQLException {
        dbHelper = new MyListHelper(contexto);
        db = dbHelper.getWritableDatabase();
        return this;

    }
    public void cerrar() {
        dbHelper.close();
    }


    public Cursor getCursor() throws android.database.SQLException
    {
        Cursor c = db.query( true, MyListContract.ResultEntry.Table_Name, columnas, null, null, null, null, null, null);

        return c;
    }
    public long insert(ContentValues reg) throws SQLException {

       // if (db == null)
            abrir();

        return db.insert(MyListContract.ResultEntry.Table_Name, null, reg);
    }
    public long insertPoints(ContentValues reg)throws SQLException{
        abrir();
        return db.insert(MyListContract.MyPointsResultEntry.Table_Name,null,reg);
    }
    public Cursor getProPricesCursor(){
        Cursor c = db.query(true,MyListContract.PromPricesResultEntry.Table_Name,ColumnasPromPrices,null,null,null,null,null,null);
        return c;
    }
    public int getMypointsCursor() throws SQLException {

        Cursor c = db.rawQuery("SELECT Sum("+MyListContract.MyPointsResultEntry.Column_Points +") AS total  FROM "+MyListContract.MyPointsResultEntry.Table_Name,null);
        c.moveToFirst();
        int total = c.getInt(0);
        return total;


    }
    public  Cursor getMapProdList(String sector){

        Cursor c = db.rawQuery("select * from " + MyListContract.ResultEntry.Table_Name + " where " + MyListContract.ResultEntry.Column_Position + " = '" + String.valueOf(sector) + "'", null);
        Log.d(null, sector);
        return c;
    }
    public void borrar(int position){
       db.execSQL("delete from " + MyListContract.ResultEntry.Table_Name + " where " +MyListContract.ResultEntry._ID + " = " +position);
    }
    public Integer contar(String seccion){

        Cursor c = db.rawQuery("select * from " +MyListContract.ResultEntry.Table_Name + " where " + MyListContract.ResultEntry.Column_Position + " = '" +String.valueOf(seccion)+ "'" ,null );

        return c.getCount();
    }

}
