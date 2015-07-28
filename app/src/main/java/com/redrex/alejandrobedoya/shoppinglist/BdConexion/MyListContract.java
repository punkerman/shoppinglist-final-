package com.redrex.alejandrobedoya.shoppinglist.BdConexion;

import android.provider.BaseColumns;
import android.text.format.Time;

public class MyListContract {
    public static long normalizeData(long startDate){
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate,time.gmtoff);
        return time.setJulianDay(julianDay);
    }
    // crear una clase para cada tabla de la Bd
    public static final class ResultEntry implements BaseColumns{
        public static final String Table_Name = "MyList";
        public static final String Column_Item = "item";
        public static final String Columm_Cant = "cantidad";
        public static final String Column_Relation = "relacion";
        public static final String Column_Position = "posicion";

    }
    public static final class PromPricesResultEntry implements BaseColumns{
        public static final String Table_Name = "PromPrices";
        public static final String Column_Image = "imagen";
        public static final String Column_Price="premio";
        public static final String Column_Description= "descripcion";
        public static final String Column_Points= "puntos";
    }
    public static final class MyPointsResultEntry implements BaseColumns{
        public static final String Table_Name= "PromPoints";
        public static final String Column_Points= "puntos";
        public static final String Column_Date= "fecha";

    }

}
