package com.pronostico7.practicasqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

public class serviciosActivity extends AppCompatActivity {

    String idjuego = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://www.diabets.life/wspronos/obtenerresultados.php?id="+idjuego, new AsyncHttpResponseHandler()
        {
            @Override
            public void onStart() {

                Log.d("OnStart: ", "OK update");
                // Toast toast1 = Toast.makeText(getApplicationContext(), "cargando", Toast.LENGTH_SHORT);
                //toast1.show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {

                String data= new String(response);
                Log.d("retultados: ", ""+ data);


                try {
                    JSONObject jsonArray = new JSONObject(data);
                    String texto = "";
                    Log.d("JSON ARRAY", jsonArray.toString());
                    //Log.d("JSON ARRAY de objeto", jsonArray.toString());
                    for (int i = 0; i < jsonArray.getJSONArray("metas").length(); i++) {

                        JSONObject temp = jsonArray.getJSONArray("metas").getJSONObject(i);
                        String idresultado = temp.getString("idresultado");
                        String pronostico1 = temp.getString("pronostico1");
                        String pronostico2 = temp.getString("pronostico2");
                        String equipo1 = temp.getString("equipo1");
                        String equipo2 = temp.getString("equipo2");


                        Log.d("servicio ", idresultado.toString());
                        //Log.d("aaaaaa: ", idresultado);
                        //Log.d("aaaaaa: ", pronostico1);
                        //Log.d("aaaaaa: ", pronostico2);
                        //Log.d("aaaaaa: ", equipo1);
                        //Log.d("aaaaaa: ", equipo2);

                       /* TextView equipo1layout = (TextView)findViewById(R.id.tvekipo1);
                        TextView equipo2layout = (TextView)findViewById(R.id.tvekipo2);
                        TextView marcador1 = (TextView)findViewById(R.id.valor1);
                        TextView marcador2 = (TextView)findViewById(R.id.valor2);

                        marcador1.setText(pronostico1);
                        marcador2.setText(pronostico2);
                        equipo1layout.setText(equipo1);
                        equipo2layout.setText(equipo2);*/
                        //linearLayout.addView(child1);

                    }
                } catch(Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "Por favor Conéctese a Internet ", Toast.LENGTH_SHORT);
                toast1.show();
                Log.d("onFailure: ", "OK");

            }

            @Override
            public void onRetry(int retryNo) {

                // Log.d("onRetry: ", "OK");
            }
        });
    }
}
