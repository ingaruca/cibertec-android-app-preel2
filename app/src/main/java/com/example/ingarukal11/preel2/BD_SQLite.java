package com.example.ingarukal11.preel2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD_SQLite extends SQLiteOpenHelper{

    public BD_SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table alumnos(_ID integer primary key autoincrement, nombres text, apellidos text, edad integer)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void open(){
        this.getWritableDatabase();
    }

    public void close(){
        this.close();
    }

    public void insertarAlumno(String nombres, String apellidos, int edad){
        ContentValues valores = new ContentValues();
        valores.put("nombres", nombres);
        valores.put("apellidos", apellidos);
        valores.put("edad", edad);

        this.getWritableDatabase().insert("alumnos", null, valores);
    }

    public Cursor listarAlumnos(){
        Cursor cursor = this.getReadableDatabase().query("alumnos", new String[]{"_ID", "nombres", "apellidos", "edad"}, null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public Cursor buscarAlumno(int id) throws SQLException{
        String[] campos =  new String[]{"_ID", "nombres", "apellidos", "edad"};
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query("alumnos", campos , "_ID='"+id+"'", null, null, null, null);

        return cursor;
    }

    public void eliminarAlumno(int id){
        this.getReadableDatabase().delete("alumnos", "_ID="+id, null);
    }

    public void editarAlumno(int id, String nombres, String apellidos, int edad){
        ContentValues valores = new ContentValues();
        valores.put("nombres", nombres);
        valores.put("apellidos", apellidos);
        valores.put("edad", edad);

        this.getWritableDatabase().update("alumnos", valores, "_ID="+id, null);
    }
}
