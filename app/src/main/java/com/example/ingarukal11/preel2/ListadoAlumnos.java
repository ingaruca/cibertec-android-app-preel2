package com.example.ingarukal11.preel2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadoAlumnos extends AppCompatActivity {

    ListView listAlumnos;

    BD_SQLite bd = new BD_SQLite(this, "alumnos", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_alumnos);

        listAlumnos = (ListView)findViewById(R.id.listAlumnos);

        Cursor cursor;
        bd.open();

        cursor = bd.listarAlumnos();

        ArrayList<String> lista = new ArrayList<String>();

        String registro = "";

        do {
            registro = cursor.getInt(0) + "-" +
                    cursor.getString(1) + "-" +
                    cursor.getString(2) + "-" +
                    cursor.getInt(3);
            lista.add(registro);

        }
        while (cursor.moveToNext());

        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);

        listAlumnos.setAdapter(adap);

    }
}
