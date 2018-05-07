package com.example.ingarukal11.preel2;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarAlumno extends AppCompatActivity {

    EditText txtID, txtNombres, txtApellidos, txtEdad;
    Button btnBuscarEliminar, btnEliminarAlumno;

    BD_SQLite bd = new BD_SQLite(this, "alumnos", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_alumno);

        txtID = (EditText)findViewById(R.id.txtIDEliminar);
        txtNombres = (EditText)findViewById(R.id.txtNombresEliminar);
        txtApellidos = (EditText)findViewById(R.id.txtApellidosEliminar);
        txtEdad = (EditText)findViewById(R.id.txtEdadEliminar);

        btnBuscarEliminar = (Button)findViewById(R.id.btnBuscarEliminar);
        btnEliminarAlumno = (Button)findViewById(R.id.btnEliminarAlumno);

        btnBuscarEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bd.open();

                try{
                    Cursor cursor = bd.buscarAlumno(Integer.parseInt(txtID.getText().toString()));

                    if (cursor.moveToFirst()){
                        txtNombres.setText(cursor.getString(1));
                        txtApellidos.setText(cursor.getString(2));
                        txtEdad.setText(cursor.getInt(3));
                    }

                    Toast.makeText(getApplicationContext(), "Alumno encontrado!",
                            Toast.LENGTH_LONG).show();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }

                bd.close();

            }
        });

        btnEliminarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bd.open();
                bd.eliminarAlumno(Integer.parseInt(txtID.getText().toString()));
                bd.close();

                Toast.makeText(getApplicationContext(), "Alumno Eliminado",
                        Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);


            }
        });
    }
}
