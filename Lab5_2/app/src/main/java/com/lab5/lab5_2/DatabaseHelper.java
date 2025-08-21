package com.lab5.lab5_2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "kafeteria.db";
    private static final int DB_VERSION = 1;

    public static final String T_DRINKS = "drinks";
    public static final String T_SNACKS = "snacks";
    public static final String T_LOCATIONS = "locations";

    public DatabaseHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        // Napoje
        db.execSQL("CREATE TABLE " + T_DRINKS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, description TEXT, price REAL)");
        db.execSQL("INSERT INTO " + T_DRINKS + " (name,description,price) VALUES" +
                "('Coca-Cola','Orzeźwiający napój gazowany',8.0)," +
                "('Pepsi','Orzeźwiający napój gazowany',8.0)," +
                "('Sok pomarańczowy','100% sok, świeży',10.0)");

        // Przekąski
        db.execSQL("CREATE TABLE " + T_SNACKS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, description TEXT, price REAL)");
        db.execSQL("INSERT INTO " + T_SNACKS + " (name,description,price) VALUES" +
                "('Frytki','Złociste i chrupiące',6.0)," +
                "('Burger','Wołowina, ser, sałata',18.0)," +
                "('Ciastko','Czekoladowe, świeże',7.0)");

        // Lokalizacje (z nazwą zasobu obrazka mapy w kolumnie map_res)
        db.execSQL("CREATE TABLE " + T_LOCATIONS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, address TEXT, hours TEXT, map_res TEXT)");
        db.execSQL("INSERT INTO " + T_LOCATIONS + " (name,address,hours,map_res) VALUES" +
                "('Kafeteria Central','ul. Przykładowa 10, Warszawa','10:00–22:00','map_central')," +
                "('Kafeteria Mokotów','ul. Zielona 5, Warszawa','9:00–21:00','map_mokotow')");
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + T_DRINKS);
        db.execSQL("DROP TABLE IF EXISTS " + T_SNACKS);
        db.execSQL("DROP TABLE IF EXISTS " + T_LOCATIONS);
        onCreate(db);
    }

    public Cursor getDrinks() {
        return getReadableDatabase().rawQuery("SELECT * FROM " + T_DRINKS, null);
    }
    public Cursor getSnacks() {
        return getReadableDatabase().rawQuery("SELECT * FROM " + T_SNACKS, null);
    }
    public Cursor getLocations() {
        return getReadableDatabase().rawQuery("SELECT * FROM " + T_LOCATIONS, null);
    }
}
