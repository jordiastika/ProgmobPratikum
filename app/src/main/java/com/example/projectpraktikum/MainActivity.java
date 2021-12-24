package com.example.projectpraktikum;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getSimpleName();
    DatabaseHelper db;
    long id;
    EditText nama,alamat;
    TextView Alertnama,Alertalamat,Alertjenkel,Alertusia,Alertagama;

    RadioGroup gender;
    SeekBar seekBarUsia;
    TextView seekBarValue;
    CheckBox hindu,Katolik,Islam,Buddha,Kristen,Konghucu;
    Button buttonSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            db = new DatabaseHelper(this);

            id = getIntent().getLongExtra(DatabaseHelper.c_id, 0);
            /*text input*/
            nama = (EditText) findViewById(R.id.editNama);
            alamat = (EditText) findViewById(R.id.alamat);

        /*Radio Button*/
            gender = (RadioGroup) findViewById(R.id.jenisKelamin);

            /*cheekbox*/
            hindu = (CheckBox) findViewById(R.id.hindu);
            Katolik = (CheckBox) findViewById(R.id.Katolik);
            Islam = (CheckBox) findViewById(R.id.Islam);
            Buddha = (CheckBox) findViewById(R.id.Buddha);
            Kristen = (CheckBox) findViewById(R.id.Kristen);
            Konghucu = (CheckBox) findViewById(R.id.Konghucu);

            /*button sybmit*/
            buttonSubmit = (Button) findViewById(R.id.submitRegis);

            /* seekbar */
                seekBarUsia = (SeekBar) findViewById(R.id.seekBarUsia);
                seekBarValue = (TextView) findViewById(R.id.viewUsia);

            /*mendapatkan nilai seekbar  */
            seekBarUsia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    seekBarValue.setText(String.valueOf(progress + "tahun"));
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Apakah Sudah Yakin?");
                builder.setPositiveButton(
                        "Lanjut", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String Nama = nama.getText().toString();
                                String Alamat = alamat.getText().toString();
                                String Jenkel = "";
                                String Usia = seekBarValue.getText().toString();
                                String Agama = "";

                                /*percabangan jenis kelamin*/
                                switch (gender.getCheckedRadioButtonId()) {
                                    case R.id.laki:
                                        Jenkel = Jenkel + "Laki-laki";
                                        break;
                                    case R.id.perempuan:
                                        Jenkel = Jenkel + "Perempuan";
                                }
                                /*chekbox agama*/
                                if (hindu.isChecked()) {
                                    Agama = Agama + "Agama hindu";
                                }
                                if (Katolik.isChecked()) {
                                    Agama = Agama + "Agama Katolik";
                                }
                                if (Islam.isChecked()) {
                                    Agama = Agama + "Agama Islam";
                                }
                                if (Buddha.isChecked()) {
                                    Agama = Agama + "Agama Buddha";
                                }
                                if (Kristen.isChecked()) {
                                    Agama = Agama + "Agama Kristen";
                                }
                                if (Konghucu.isChecked()) {
                                    Agama = Agama + "Agama Konghucu";
                                }
                                /*toast untuk edit text*/
                                if (nama.getText().toString().trim().length() == 0) {
                                    Toast.makeText(getApplicationContext(), "Mohon isi nama", Toast.LENGTH_LONG).show();
                                } else if (alamat.getText().toString().trim().length() == 0) {
                                    Toast.makeText(getApplicationContext(), "Mohon isi Alamat", Toast.LENGTH_LONG).show();
                                } else {

                                    AlertDialog.Builder header = new AlertDialog.Builder(MainActivity.this);
                                    View view = getLayoutInflater().inflate(R.layout.alertdialog,null);
                                    Alertnama = (TextView) view.findViewById(R.id.alert_name);
                                    Alertnama.setText(" " + Nama);

                                    Alertalamat = (TextView) view.findViewById(R.id.alert_alamat);
                                    Alertalamat.setText(" " + Alamat);

                                    Alertjenkel = (TextView) view.findViewById(R.id.alert_jenkel);
                                    Alertalamat.setText(" " + Jenkel);

                                    Alertusia = (TextView) view.findViewById(R.id.alert_usia);
                                    Alertusia.setText(" " + Usia);

                                    Alertagama = (TextView) view.findViewById(R.id.alert_agama);
                                    Alertagama.setText(" " + Agama);


                                    /*intent untuk ke activity baru*/
                                    Intent hubung = new Intent(MainActivity.this, Homeactivity.class);
                                    Bundle b = new Bundle();
                                    b.putString("nama", Nama);
                                    b.putString("alamat", Alamat);
                                    b.putString("jenisKelamin", Jenkel);
                                    b.putString("usia", Usia);
                                    b.putString("agama", Agama);
                                    Toast.makeText(getApplicationContext(), Nama + Alamat + Jenkel + Usia + Agama, Toast.LENGTH_LONG).show();
                                    hubung.putExtras(b);
                                    startActivity(hubung);
                                }
                                ContentValues values = new ContentValues();
                                values.put(DatabaseHelper.c_nama,Nama);
                                values.put(DatabaseHelper.c_alamat,Alamat);
                                values.put(DatabaseHelper.c_jenis, Jenkel);
                                values.put(DatabaseHelper.c_usia, Usia);
                                values.put(DatabaseHelper.c_agama,Agama);
                                DatabaseHelper.insertData(values);
                            }
                        }).setNegativeButton(
                        "Kembali", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Selamat datang", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Halo", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Jangan lupa kembali lagi", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Sampai berjumpa lagi nanti", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent_kembali = new Intent(this, MainActivity.class);
    }

}