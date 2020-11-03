package com.rismawan.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class UpdateActivity extends AppCompatActivity {
    private DBHelper MyDatabase;
    private EditText NewInstasi, NewAlamat, NewSyarat;
    private Button Update;
    private String getInstasi, getAlamat, getSyarat, getId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setTitle("Update Data");
        MyDatabase = new DBHelper (getBaseContext());
        NewInstasi = findViewById(R.id.uInstansi);
        NewAlamat = findViewById(R.id.uAlamat);
        NewSyarat = findViewById(R.id.uSyarat);
        Update = findViewById(R.id.uButton);

        getId = getIntent().getExtras().getString("SendId");
        NewInstasi.setText(getIntent().getExtras().getString("SendInstansi"));
        NewAlamat.setText(getIntent().getExtras().getString("SendAlamat"));
        NewSyarat.setText(getIntent().getExtras().getString("SendSyarat"));

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpdateData();
                startActivity(new Intent(UpdateActivity.this, RecyclerView.class));
                finish();
            }
        });

    }

    private void setUpdateData() {
        getInstasi = NewInstasi.getText().toString();
        getAlamat = NewAlamat.getText().toString();
        getSyarat = NewSyarat.getText().toString();

        SQLiteDatabase database = MyDatabase.getReadableDatabase();

        //Memasukan Data baru pada 3 kolom (NIM, Nama dan Jurusan)
        ContentValues values = new ContentValues();
        values.put(DBHelper.JobEntry.Instansi, getInstasi);
        values.put(DBHelper.JobEntry.Alamat, getAlamat);
        values.put(DBHelper.JobEntry.Syarat, getSyarat);

        //Untuk Menentukan Data/Item yang ingin diubah, berdasarkan NIM
        String selection = DBHelper.JobEntry.ID + " LIKE ?";
        String[] selectionArgs = {getId};
        database.update(DBHelper.JobEntry.NamaTabel, values, selection, selectionArgs);
        Toast.makeText(getApplicationContext(), "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
    }
}