package com.helcode.syncsqlite_mysql.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    public static int VERSION =1;

    public database(@Nullable Context context) {
        super(context, BDContact.DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createContactos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createContactos(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+BDContact.TABLE_NAME+"("+BDContact.Contactoa.ID+" INTEGER PRIMARY KEY, "+BDContact.Contactoa.NOMBRE+" TEXT, "+BDContact.Contactoa.APELLIDOS+" TEXT, "+BDContact.Contactoa.TELEFONO+" TEXT);");

    }

    public void getContactos(int id,String nombre ,String apellido, String telefono, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BDContact.Contactoa.ID,id);
        contentValues.put(BDContact.Contactoa.NOMBRE,nombre);
        contentValues.put(BDContact.Contactoa.APELLIDOS,apellido);
        contentValues.put(BDContact.Contactoa.TELEFONO,telefono);
        db.insert(BDContact.TABLE_NAME,null,contentValues);
    }

    public Cursor readContacto(SQLiteDatabase db) {
        return db.query(BDContact.TABLE_NAME,null,null,null,null,null,null);
    }
}
