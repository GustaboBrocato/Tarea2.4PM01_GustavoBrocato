package com.example.tarea24pm01_gustavobrocato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button agregarFirma, verFirmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarFirma = (Button) findViewById(R.id.btnAgregarFirma);
        verFirmas = (Button) findViewById(R.id.btnVerFirmas);

        View.OnClickListener buttonClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<?> actividad = null;
                if (view.getId()==R.id.btnAgregarFirma) {
                    actividad = ActivityAgregarFirma.class;
                }
                if (view.getId()==R.id.btnVerFirmas) {
                    actividad = ActivityVerFirmas.class;
                }
                if (actividad != null) {
                    moveActivity(actividad);
                }
            }
        };

        agregarFirma.setOnClickListener(buttonClick);
        verFirmas.setOnClickListener(buttonClick);
    }

    private void moveActivity(Class<?> actividad) {
        Intent intent = new Intent(getApplicationContext(), actividad);
        startActivity(intent);
    }
}