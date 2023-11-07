package com.example.tarea24pm01_gustavobrocato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tarea24pm01_gustavobrocato.Adapters.CustomAdapterFirmas;
import com.example.tarea24pm01_gustavobrocato.Config.SQLiteConnection;
import com.example.tarea24pm01_gustavobrocato.Config.Transacciones;
import com.example.tarea24pm01_gustavobrocato.Models.firmaItem;

import java.util.ArrayList;
import java.util.List;

public class ActivityVerFirmas extends AppCompatActivity {

    SQLiteConnection conexion;
    RecyclerView lista;
    Button regresar;
    List<firmaItem> dataList;
    CustomAdapterFirmas adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_firmas);

        conexion = new SQLiteConnection(this, Transacciones.namedb, null, 1);
        lista = (RecyclerView) findViewById(R.id.recycleviewFirmas);
        regresar = (Button) findViewById(R.id.btnRegresarFirmas);
        dataList = new ArrayList<>();

        getFirmas();

        // Configuración del administrador de diseño y adaptador para el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(layoutManager);
        adapter = new CustomAdapterFirmas(this, dataList);
        lista.setAdapter(adapter);

        View.OnClickListener buttonClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<?> actividad = null;
                if (view.getId()==R.id.btnRegresarFirmas) {
                    actividad = MainActivity.class;
                }
                if (actividad != null) {
                    moveActivity(actividad);
                }
            }
        };

        regresar.setOnClickListener(buttonClick);
    }

    private void getFirmas() {
        try {
            SQLiteDatabase db = conexion.getReadableDatabase();
            dataList.clear();

            Cursor cursor = db.rawQuery(Transacciones.SelectTableFirmas, null);

            while (cursor.moveToNext()) {
                String description = cursor.getString(cursor.getColumnIndex(Transacciones.descripcion));
                byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(Transacciones.firma));
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                dataList.add(new firmaItem(imageBitmap, description));
            }

            cursor.close();

            adapter.notifyDataSetChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void moveActivity(Class<?> actividad) {
        Intent intent = new Intent(getApplicationContext(), actividad);
        startActivity(intent);
    }
}