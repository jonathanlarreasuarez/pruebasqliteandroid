package com.pronostico7.practicasqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pronostico7.practicasqlite.entidades.usuario;
import com.pronostico7.practicasqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class listarListView extends AppCompatActivity {

    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<usuario> listaUsuarios;

    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_list_view);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
        listViewPersonas= (ListView) findViewById(R.id.listViewPersonas);

        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="id: "+listaUsuarios.get(pos).getId()+"\n";
                informacion+="Nombre: "+listaUsuarios.get(pos).getNombre()+"\n";
                informacion+="Telefono: "+listaUsuarios.get(pos).getTelefono()+"\n";

                Toast.makeText(getApplicationContext(),informacion, Toast.LENGTH_LONG).show();

                usuario user=listaUsuarios.get(pos);

                Intent intent=new Intent(listarListView.this,DetalleUsuarioActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("usuario",user);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        usuario usuarios=null;
        listaUsuarios=new ArrayList<usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            usuarios=new usuario();
            usuarios.setId(cursor.getInt(0));
            usuarios.setNombre(cursor.getString(1));
            usuarios.setTelefono(cursor.getString(2));

            listaUsuarios.add(usuarios);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getId()+" - "
                    +listaUsuarios.get(i).getNombre());
        }

    }
}
