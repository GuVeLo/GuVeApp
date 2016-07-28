package com.example.guvelo.navdrawertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuVeLo on 21-07-2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION_BD = 1;
    private static final String NOMBRBE_BASE = "BASE01";
    public static final String NOMBRE_TABLA = "TABLA_USUARIO";

    private static final String KEY_ID ="campo_id";
    private static final String KEY_APELLIDO ="campo_apellido";
    private static final String KEY_USUARIO ="campo_usuario";


    public DataBaseHelper(Context context){
        super(context,NOMBRBE_BASE,null,VERSION_BD);
    }
    /*
    *PATH /data/data/com/example/guvelo/navdrawertest/BASE01.db
    * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Aqui crearemos las tablas
        Log.d("BASE CREADA", "BASE DE DATOS CR");
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + NOMBRE_TABLA);
        sb.append("( nombre TEXT , apellido TEXT)");
        db.execSQL(sb.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.close();

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.d("BANDERA", "onOpen !!!!!!!!!!");
        super.onOpen(db);
    }

    public void addUsuario(Usuario usuarioPojo){
        SQLiteDatabase db = this.getWritableDatabase();

        if(db==null){
            onCreate(db);
        }

        ContentValues values = new ContentValues();
        values.put(KEY_USUARIO, usuarioPojo.getNombre());
        values.put(KEY_APELLIDO, usuarioPojo.getApellido());

        db.insert(NOMBRE_TABLA,null,values);
        db.close();
    }

    public List<Usuario> getAllUsuarios(){
        List<Usuario> usuarioList = new ArrayList<>();
        String sql = "SELECT "+KEY_USUARIO+" , "+KEY_APELLIDO+" FROM "+NOMBRE_TABLA;

        SQLiteDatabase db = this.getWritableDatabase();
        if(db==null){
            onCreate(db);
            db = this.getWritableDatabase();
        }
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor!=null && cursor.moveToFirst()){
            do{
                Usuario usuario = new Usuario();
                usuario.setApellido(cursor.getString(0));
                usuario.setNombre(cursor.getString(1));
                usuarioList.add(usuario);
            }while(cursor.moveToNext());

        }
        db.close();
        return usuarioList;
    }
}
