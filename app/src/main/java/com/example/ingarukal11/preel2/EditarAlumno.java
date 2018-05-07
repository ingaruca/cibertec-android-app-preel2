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

public class EditarAlumno extends AppCompatActivity {

    EditText txtID, txtNombres, txtApellidos, txtEdad;
    Button btnBuscar, btnGrabar;
    int id, edad;
    String nombres, apellidos;

    BD_SQLite bd = new BD_SQLite(this, "alumnos", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alumno);

        txtID = (EditText)findViewById(R.id.txtIDEditar);
        txtNombres = (EditText)findViewById(R.id.txtNombresEditar);
        txtApellidos = (EditText)findViewById(R.id.txtApellidosEditar);
        txtEdad = (EditText)findViewById(R.id.txtEdadEditar);

        btnBuscar = (Button)findViewById(R.id.btnBuscarEditar);
        btnGrabar = (Button)findViewById(R.id.btnGrabarEditar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
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

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    bd.open();

                    id = Integer.parseInt(txtID.getText().toString());
                    nombres = txtNombres.getText().toString();
                    apellidos = txtApellidos.getText().toString();
                    edad = Integer.parseInt(txtEdad.getText().toString());

                    bd.editarAlumno(id, nombres, apellidos, edad);

                    Toast.makeText(getApplicationContext(), "Cambios grabados",
                            Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    bd.close();
                }
                catch (SQLException e2){
                    e2.printStackTrace();
                }

            }
        });

    }
}
