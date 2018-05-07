package com.example.ingarukal11.preel2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtNombres, txtApellidos, txtEdad;
    Button btnRegistrar, btnListar, btnEditar, btnEliminar;

    BD_SQLite bd = new BD_SQLite(this, "alumnos", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombres = (EditText)findViewById(R.id.txtNombres);
        txtApellidos = (EditText)findViewById(R.id.txtApellidos);
        txtEdad = (EditText)findViewById(R.id.txtEdad);

        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnListar = (Button)findViewById(R.id.btnListar);
        btnEditar = (Button)findViewById(R.id.btnEditar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bd.open();
                bd.insertarAlumno(txtNombres.getText().toString(),
                        txtApellidos.getText().toString(),
                        Integer.parseInt(txtEdad.getText().toString()));

                Toast.makeText(getApplicationContext(), "Alumno registrado",
                        Toast.LENGTH_LONG).show();

                txtNombres.setText("");
                txtNombres.requestFocus();
                txtApellidos.setText("");
                txtEdad.setText("");

                bd.close();

            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), ListadoAlumnos.class);
                startActivity(i);

            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), EditarAlumno.class);
                startActivity(i);

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EliminarAlumno.class);
                startActivity(i);
            }
        });
    }
}
