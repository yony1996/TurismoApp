package com.example.turismoapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Hotels.db";
    public static final String TABLE_HOTELS = "tblhotel";
    public static final String TABLE_USERS = "tblusers";

    public static final String CREATE_TABLE_HOTELS = "CREATE TABLE " + TABLE_HOTELS + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT ," +
            "categoria TEXT,"+
            "accecibilidad REAL,"+
            "referencia TEXT,"+
            "habitaciones INTEGER,"+
            "precio REAL,"+
            "telefono1 TEXT," +
            "telefono2 TEXT," +
            "direccion TEXT," +
            "url TEXT," +
            "descripcion TEXT," +
            "image TEXT);";
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "cedula TEXT NOT NULL," +
            "nombre TEXT NOT NULL," +
            "email TEXT NOT NULL," +
            "contrasena TEXT NOT NULL," +
            "rol TEXT NOT NULL);";
    public static final String INSERT_USER = "INSERT INTO " + TABLE_USERS + " VALUES (1,'1234567890','admin','admin@gmail.com', '12345678','admin');";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_HOTELS);
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(INSERT_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_HOTELS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }
}
