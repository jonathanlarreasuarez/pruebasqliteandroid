package com.pronostico7.practicasqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnOpcionRegistro:
                miIntent=new Intent(MainActivity.this,registrarUser.class);
                break;
            case R.id.button2:
                miIntent=new Intent(MainActivity.this,consultarUser.class);
                break;
            case R.id.btnConsultaSpinner:
                miIntent=new Intent(MainActivity.this,listarEspiner.class);
                break;
            case R.id.btnConsultaLista:
                miIntent=new Intent(MainActivity.this,listarListView.class);
                break;
            case R.id.btnServicio:
                miIntent=new Intent(MainActivity.this,serviciosActivity.class);
                break;
            /*case R.id.btnRegistroMascota:
                miIntent=new Intent(MainActivity.this,RegistroMascotaActivity.class);
                break;



            case R.id.btnConsultaListaMascota:
                miIntent=new Intent(MainActivity.this,ListaMascotasActivity.class);
                break;
            case R.id.btnConsultaListaPersonasRecycler:
                miIntent=new Intent(MainActivity.this,ListaPersonasRecycler.class);
                break;*/
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }
}
