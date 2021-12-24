package com.example.projectpraktikum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String database_name = "DB_DATA";
    public static final String table_name = "biodata";
    public static final String c_id = "id";
    public static final String c_nama = "nama";
    public static final String c_alamat = "lamat";
    public static final String c_jenis = "jeniskelamin";
    public static final String c_usia = "usia";
    public static final String c_agama = "agama";

    private static SQLiteDatabase db;


    public DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "("
                + c_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + c_nama + " TEXT,"
                + c_alamat + " TEXT,"
                + c_jenis + " TEXT,"
                + c_usia + " TEXT,"
                + c_agama + " TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    //get all sqlite data
    public Cursor allData() {
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name, null);
        return cur;
    }

    //get 1 daya by id
    public Cursor  oneData(long id){
        Cursor cur = db.rawQuery("SELECT * FROM "+table_name+ "WHERE "+ c_id + "="+id,null);
        return cur;
    }
    //Insert Data to Database
    public static void insertData(ContentValues values) {
        db.insert(table_name, null, values);
    }
    //update data
    public void updateData(ContentValues values, long id){
        db.update(table_name, values, c_id + "=" + id, null);
    }
    public void deleteData(long id) {
        db.delete(table_name, c_id + "=" + id, null);
    }
}