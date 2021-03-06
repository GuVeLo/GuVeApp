package com.example.guvelo.navdrawertest;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonActivity extends AppCompatActivity {

    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        //INSTANCIAMOS NUESTRO LIST VIEW
        ListView listaUsuarios = (ListView) findViewById(R.id.lista_usuarios);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);


        //Google creo esto para informar de violaciones de politicas relacionadas con los hijos en ejecucion (Thread) y la maquina virtual (Dalvik)
        //Con esto se crea un alerta al violar dicha politica, se crea una traza de la pila de ejecucion (Stack Trace)
        //Siempre cuando accedemos a la red, debemos agregar estas dos lineas ...
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //LLAMAMOS UN JSON DESDE UN URL
        String url = "http://server2.solcloud.cl/academia/retorno.json";
        HttpURLConnection conn = null;
        try{
            conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            InputStream is = conn.getInputStream();

            BufferedReader buff = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = "-";

            while( (line = buff.readLine()) !=null ){
                sb.append(line).append(" \n");
            }

            String jsonString = sb.toString().trim(); //Quitamos los espacion en blanco

            System.out.println(jsonString);

            JSONObject jsonObj = new JSONObject(sb.toString().trim()); //AHORA INSTANCIAMOS LA CLASE JSON OBJECT
            this.jsonArray = jsonObj.getJSONArray("usuarios"); // OBTENER EL ARREGLO JSON

            //RECORRERMOS EL JSON ARRAY
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject o    = jsonArray.getJSONObject(i);
                String nombre   = o.getString("nombre");
                String apellido = o.getString("apellido");
                adapter.add(nombre + " " + apellido );
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        //ASOCIAMOS EL LIST VIEW AL ADAPTADOR
        listaUsuarios.setAdapter(adapter);


        System.out.println("position = ");

    }
}
