package com.example.tarea24pm01_gustavobrocato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import android.os.Bundle;

import com.example.tarea24pm01_gustavobrocato.Config.SQLiteConnection;
import com.example.tarea24pm01_gustavobrocato.Config.Transacciones;
import com.example.tarea24pm01_gustavobrocato.DrawingView.SignatureView;

public class ActivityAgregarFirma extends AppCompatActivity {

    SQLiteConnection conexion;
    private SignatureView signatureView;
    private Button btnSave, btnClear;
    EditText descripcion;

    byte[] signatureBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_firma);

        conexion = new SQLiteConnection(this, Transacciones.namedb, null, 1);
        signatureView = findViewById(R.id.signatureView);
        btnSave = findViewById(R.id.btnSave);
        btnClear = findViewById(R.id.btnClear);
        descripcion = (EditText) findViewById(R.id.txtDescripcion);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap signatureBitmap = signatureView.getSignatureBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                signatureBytes = stream.toByteArray();
                if(descripcion.getText().toString().trim().isEmpty()){
                    descripcion.setError("Porfavor ingrese una descripción para la imagen, no se permiten campos vacios!!!");
                } else if (signatureView.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Porfavor Ingrese su firma!", Toast.LENGTH_LONG).show();
                }else{
                    saveSignature();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clear();
            }
        });


    }

    private void saveSignature() {


        try {
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(Transacciones.descripcion, descripcion.getText().toString());
            valores.put(Transacciones.firma, signatureBytes);
            Long result = db.insert(Transacciones.tablaFirmas, Transacciones.id, valores);
            Toast.makeText(this, getString(R.string.respuesta), Toast.LENGTH_SHORT).show();
            db.close();
            signatureView.clear();
            descripcion.setText("");
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.errorIngreso), Toast.LENGTH_SHORT).show();
        }
    }
}