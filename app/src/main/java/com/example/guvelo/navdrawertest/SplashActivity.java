package com.example.guvelo.navdrawertest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        WebView gif = (WebView) findViewById(R.id.gif);
        gif.loadUrl("https://45.media.tumblr.com/52c5aa24385b18fc1e53be801dc16e9e/tumblr_o68vav2BZ71rtukzbo1_500.gif");

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 3000);

        DataBaseHelper db = new DataBaseHelper(this);
        SQLiteDatabase baseDeDatos = db.getWritableDatabase();
        System.out.println(db.getDatabaseName());

        //asi insertmos un registro en la tabla usuario
//        baseDeDatos.execSQL("INSERT INTO " + DataBaseHelper.NOMBRE_TABLA+ "('USUARIO','APELLIDO!!')");
    //    db.close(); //cerramos la conexion

        //AlertDialog dialog,
        //dialog.setTitle("TITULO DEL DIALOG");
        //dialog.setMessage("qwerty");
        //dialog.show();
    }

}
