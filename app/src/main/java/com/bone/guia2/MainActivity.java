package com.bone.guia2;

import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Declaracion de variables
    private RadioButton cambiar, noCambiar;
    private EditText txtUrl, txtcambiar;
    private TextView lblEstado;
    private Button btnDescargar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializaremos los controles

        cambiar = (RadioButton) findViewById(R.id.cambiar);
        noCambiar = (RadioButton) findViewById(R.id.noCambiar);
        txtUrl = (EditText) findViewById(R.id.txtURL);
        txtcambiar = (EditText) findViewById(R.id.txtcambiar);
        lblEstado = (TextView) findViewById(R.id.lblEstado);
        btnDescargar = (Button) findViewById(R.id.descargar);
        progressBar= (ProgressBar)findViewById(R.id.progressBar);

        btnDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (estaChequeado()) {
                    Valor();
                    new Descargar(MainActivity.this,
                            lblEstado,
                            btnDescargar,
                            progressBar
                    ).execute(txtUrl.getText().toString());

                }else{
                    Toast.makeText(MainActivity.this,"Seleccione una opcion", Toast.LENGTH_SHORT).show();

                }

            }
        });
        verifyStoragePermissions(this);
    }


    //esto es para activar perimiso de escritura y lectura en versiones de android 6 en adelante
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //persmission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }



    private void Valor(){

        if(cambiar.isChecked()){

          txtcambiar.setEnabled(true);
        }
        if(noCambiar.isChecked()) {

            txtcambiar.setEnabled(false);
        }
    }
    boolean estaChequeado(){
if(cambiar.isChecked() || noCambiar.isChecked()){

    return true;
}else{
    return false;
}


    }

    }






