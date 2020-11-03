package com.rismawan.sqlitecrud;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class RecyclerView extends AppCompatActivity {


    private ArrayList<String> IdList, NamaList,AlamatList,SyaratList ;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        getSupportActionBar().setTitle("Data Pekerjaan");
        /*
        dbHelper = new DBHelper(getBaseContext());

        MyAdapter myAdapter = new MyAdapter(this,NamaList,AlamatList,SyaratList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
        dbHelper = new DBHelper(RecyclerView.this);
        IdList = new ArrayList<>();
        NamaList = new ArrayList<>();
        AlamatList = new ArrayList<>();
        SyaratList = new ArrayList<>();
        getData();
        androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(IdList, NamaList, AlamatList, SyaratList);
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
    /*
    public void displayData(){
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"Tidak Ada Data",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                NamaList.add(cursor.getString(1));
                AlamatList.add(cursor.getString(2));
                SyaratList.add(cursor.getString(3));
            }
        }
    }*/

    @SuppressLint("Recycle")
    private void getData() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DBHelper.JobEntry.NamaTabel,null);
        cursor.moveToFirst();
        for (int i=0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            IdList.add(cursor.getString(0));
            NamaList.add(cursor.getString(1));
            AlamatList.add(cursor.getString(2));
            SyaratList.add(cursor.getString(3));

        }

    }
}