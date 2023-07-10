package com.helcode.syncsqlite_mysql.DataBase;

import android.provider.BaseColumns;

public  class BDContact {

public static final String DATABASE_NAME= "contastos.db";
public static final String TABLE_NAME= "contastos";

public static class Contactoa implements BaseColumns{
    public static String ID = "id";
    public static String NOMBRE= "nombre";
    public static String APELLIDOS= "apellidos";
    public static String TELEFONO = "telefono";
}

}
