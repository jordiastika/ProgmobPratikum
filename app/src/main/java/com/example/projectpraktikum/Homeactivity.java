package com.example.projectpraktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homeactivity extends AppCompatActivity {

    DatabaseHelper db;
    TextView nama,alamat,jenisKelamin,usia,agama;
    Button tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nama = findViewById(R.id.Nama);
        alamat = findViewById(R.id.alamat);
        jenisKelamin = findViewById(R.id.jenisKelamin);
        usia = findViewById(R.id.Usia);
        agama = findViewById(R.id.Agama);
        tambah = findViewById(R.id.buttonTambah);

        db = new DatabaseHelper(this);

        String Nama = getIntent().getExtras().getString("nama");
        String Alamat = getIntent().getExtras().getString("alamat");
        String Jenkel = getIntent().getExtras().getString("jenisKelamin");
        String Usia = getIntent().getExtras().getString("usia");
        String Agama = getIntent().getExtras().getString("agama");

        nama.setText(Nama);
        alamat.setText(Alamat);
        jenisKelamin.setText(Jenkel);
        usia.setText(Usia);
        agama.setText(Agama);


    }

}