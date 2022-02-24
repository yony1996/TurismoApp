package com.example.turismoapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import com.example.turismoapp.Entidades.Hoteles;
import com.example.turismoapp.Entidades.User;


import java.util.ArrayList;

public class DBHoteles extends DBHelper {
    Context context;

    public DBHoteles(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertHotel(String nombre, String telefono1, String telefono2, String direccion, String url, String descripcion, String image) {

        long id = 0;
        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono1", telefono1);
            values.put("telefono2", telefono2);
            values.put("direccion", direccion);
            values.put("url", url);
            values.put("descripcion", descripcion);
            values.put("image", image);
            id = db.insert(TABLE_HOTELS, null, values);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
    public ArrayList<Hoteles> mostrarHoteles() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Hoteles> listaHoteles = new ArrayList<>();
        Hoteles hotels = null;
        Cursor cursorHoteles = null;

        cursorHoteles = db.rawQuery("SELECT id,nombre,image FROM " + TABLE_HOTELS, null);
        listaHoteles.clear();
        if (cursorHoteles.moveToFirst()) {

            do {
                hotels = new Hoteles();
                hotels.setId(cursorHoteles.getInt(0));
                hotels.setNombre(cursorHoteles.getString(1));
                hotels.setImage(cursorHoteles.getString(2));
                listaHoteles.add(hotels);
            } while (cursorHoteles.moveToNext());
        }
        cursorHoteles.close();

        return listaHoteles;
    }
    public Hoteles verHoteles(int id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Hoteles hotels = null;
        Cursor cursorHoteles = null;

        cursorHoteles = db.rawQuery("SELECT * FROM " + TABLE_HOTELS + " WHERE id=" + id + " LIMIT 1", null);
        if (cursorHoteles.moveToFirst()) {
            hotels = new Hoteles();
            hotels.setId(cursorHoteles.getInt(0));
            hotels.setNombre(cursorHoteles.getString(1));
            hotels.setTelefono1(cursorHoteles.getString(2));
            hotels.setTelefono2(cursorHoteles.getString(3));
            hotels.setDireccion(cursorHoteles.getString(4));
            hotels.setUrl(cursorHoteles.getString(5));
            hotels.setDescripcion(cursorHoteles.getString(6));
            hotels.setImage(cursorHoteles.getString(7));
        }
        cursorHoteles.close();
        return hotels;
    }
    public long registerUser(String cedula,String nombre,String email,String contrasena,String rol){
        long id = 0;
        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("cedula", cedula);
            values.put("nombre",nombre);
            values.put("email", email);
            values.put("contrasena", contrasena);
            values.put("rol", rol);
            id = db.insert(TABLE_USERS, null, values);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
    public User loginUser(String email, String contrasena){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        User user = null;
        Cursor cursorUser = null;

        cursorUser = db.rawQuery("SELECT email,contrasena,rol FROM " + TABLE_USERS + " WHERE email=? AND contrasena= ?",new String[]{email,contrasena});
        if (cursorUser.moveToFirst()) {
            user = new User();
            user.setRol(cursorUser.getString(2));

        }
        cursorUser.close();
        return user;
    }
}
