package com.redrex.alejandrobedoya.shoppinglist.BdConexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyListHelper extends SQLiteOpenHelper {
    public static final int version=21;
    public static String DataBaseName = "myList.db";

    public MyListHelper(Context context){
        super(context, DataBaseName,null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
       final String SQL_CREATE_TABLE_MYLIST="CREATE TABLE " + MyListContract.ResultEntry.Table_Name + "(" +
               MyListContract.ResultEntry._ID + " INTEGER PRIMARY KEY, " +
               MyListContract.ResultEntry.Column_Item + " TEXT NOT NULL, " +
               MyListContract.ResultEntry.Columm_Cant + " DOUBLE NOT NULL, "  +
               MyListContract.ResultEntry.Column_Relation + " TEXT NOT NULL, " +
               MyListContract.ResultEntry.Column_Position + " TEXT NOT NULL" +
               ");";


        db.execSQL(SQL_CREATE_TABLE_MYLIST);

        final String SQL_CREATE_TABLE_PROMPRICES = "CREATE TABLE " + MyListContract.PromPricesResultEntry.Table_Name + "(" +
                MyListContract.PromPricesResultEntry._ID + " INTEGER PRIMARY KEY, " +
                MyListContract.PromPricesResultEntry.Column_Points + " INTEGER NOT NULL, " +
                MyListContract.PromPricesResultEntry.Column_Price + " TEXT NOT NULL, " +
                MyListContract.PromPricesResultEntry.Column_Description + " TEXT NOT NULL," +
                MyListContract.PromPricesResultEntry.Column_Image + " TEXT NOT NULL" +
                ");";
        db.execSQL(SQL_CREATE_TABLE_PROMPRICES);
        db.execSQL("INSERT INTO " + MyListContract.PromPricesResultEntry.Table_Name + "(" + MyListContract.PromPricesResultEntry.Column_Price + " , " + MyListContract.PromPricesResultEntry.Column_Description +
                   " , " + MyListContract.PromPricesResultEntry.Column_Points + " , " + MyListContract.PromPricesResultEntry.Column_Image + ") values ('Canaston premium','Productos variados con un valor de 1000 Bs','30000','R.drawable.nodisponible')");
        db.execSQL("INSERT INTO " + MyListContract.PromPricesResultEntry.Table_Name + "(" + MyListContract.PromPricesResultEntry.Column_Price + " , " + MyListContract.PromPricesResultEntry.Column_Description +
                " , " + MyListContract.PromPricesResultEntry.Column_Points + " , " + MyListContract.PromPricesResultEntry.Column_Image + ") values ('Canaston silver','Productos variados con un valor de 750 Bs','25000','R.drawable.nodisponible')");
        db.execSQL("INSERT INTO " + MyListContract.PromPricesResultEntry.Table_Name + "(" + MyListContract.PromPricesResultEntry.Column_Price + " , " + MyListContract.PromPricesResultEntry.Column_Description +
                " , " + MyListContract.PromPricesResultEntry.Column_Points + " , " + MyListContract.PromPricesResultEntry.Column_Image + ") values ('Secadora + plancha de pelo','Secadora y plancha de pelo marca Oster','20000','R.drawable.nodisponible')");
        db.execSQL("INSERT INTO " + MyListContract.PromPricesResultEntry.Table_Name + "(" + MyListContract.PromPricesResultEntry.Column_Price + " , " + MyListContract.PromPricesResultEntry.Column_Description +
                " , " + MyListContract.PromPricesResultEntry.Column_Points + " , " + MyListContract.PromPricesResultEntry.Column_Image + ") values ('Batidora','Batidora con bowl marca Oster','15000','R.drawable.nodisponible')");
        db.execSQL("INSERT INTO " + MyListContract.PromPricesResultEntry.Table_Name + "(" + MyListContract.PromPricesResultEntry.Column_Price + " , " + MyListContract.PromPricesResultEntry.Column_Description +
                " , " + MyListContract.PromPricesResultEntry.Column_Points + " , " + MyListContract.PromPricesResultEntry.Column_Image + ") values ('Licuadora','Licuadora de 2 Lts marca Oster','13000','R.drawable.nodisponible')");
        final String SQL_CREATE_TABLE_MYPOINTS = "CREATE TABLE " + MyListContract.MyPointsResultEntry.Table_Name + "(" +
                MyListContract.MyPointsResultEntry._ID + " INTEGER PRIMARY KEY, " +
                MyListContract.MyPointsResultEntry.Column_Points + " INTEGER NOT NULL," +
                MyListContract.MyPointsResultEntry.Column_Date + " TEXT NOT NULL " +
                ");";
       db.execSQL(SQL_CREATE_TABLE_MYPOINTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    db.execSQL(" DROP TABLE IF EXISTS " + MyListContract.ResultEntry.Table_Name);
    db.execSQL(" DROP TABLE IF EXISTS " + MyListContract.PromPricesResultEntry.Table_Name);
    db.execSQL(" DROP TABLE IF EXISTS " + MyListContract.MyPointsResultEntry.Table_Name);
        onCreate(db);

    }
}
