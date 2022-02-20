package com.example.turismoapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import com.example.turismoapp.Entidades.Hoteles;


import java.util.ArrayList;

public class DBHoteles extends DBHelper{
    Context context;
    public DBHoteles(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertHotel(String nombre, String telefono, String direccion, String email,String image) {

        long id = 0;
        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("direccion", direccion);
            values.put("email", email);
            values.put("image",image);
            id = db.insert(TABLE_NAME, null, values);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public ArrayList<Hoteles> mostrarHoteles() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Hoteles> listaHoteles = new ArrayList<>();
        Hoteles hotels =null;
        Cursor cursorHoteles = null;

        cursorHoteles = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        listaHoteles.clear();
        if (cursorHoteles.moveToFirst()) {

          do {
              hotels=new Hoteles();
              hotels.setId(cursorHoteles.getInt(0));
              hotels.setNombre(cursorHoteles.getString(1));
              hotels.setTelefono(cursorHoteles.getString(2));
              hotels.setDireccion(cursorHoteles.getString(3));
              hotels.setEmail(cursorHoteles.getString(4));
              hotels.setImage(cursorHoteles.getString(5));
              listaHoteles.add(hotels);
            } while (cursorHoteles.moveToNext());
        }
        cursorHoteles.close();

        return listaHoteles;
    }
}
