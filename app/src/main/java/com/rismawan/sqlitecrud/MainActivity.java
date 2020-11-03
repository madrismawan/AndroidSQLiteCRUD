package com.rismawan.sqlitecrud;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText  Nama, Syarat, Alamat;

    //Deklarasi Variabel
    private String  setNama, setAlamat, setSyarat; 
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button simpan = findViewById(R.id.button);
        Button view = findViewById(R.id.button2);
        Nama = findViewById(R.id.instansi);
        Alamat = findViewById(R.id.alamat);
        Syarat = findViewById(R.id.syarat);

        //Inisialisasi dan Mendapatkan Konteks dari DBMahasiswa
        dbHelper = new DBHelper(getBaseContext());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplication(), RecyclerView.class);
                startActivity(intent);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Toast.makeText(getApplicationContext(),"Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                clearData();
            }
        });
    }

    //Berisi Statement-Statement Untuk Menyimpan Data Pada Database
    private void saveData(){
        setNama = Nama.getText().toString();
        setAlamat = Alamat.getText().toString();
        setSyarat = Syarat.getText().toString();
        SQLiteDatabase create = dbHelper.getWritableDatabase();

        //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
        ContentValues values = new ContentValues();
        values.put(DBHelper.JobEntry.Instansi, setNama);
        values.put(DBHelper.JobEntry.Alamat, setAlamat);
        values.put(DBHelper.JobEntry.Syarat, setSyarat);

        //Menambahkan Baris Baru, Berupa Data Yang Sudah Diinputkan pada Kolom didalam Database
        create.insert(DBHelper.JobEntry.NamaTabel, null, values);
    }

    private void clearData(){
        Nama.setText("");
        Syarat.setText("");
        Alamat.setText("");
    }
}