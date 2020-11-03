package com.rismawan.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DBHelper extends SQLiteOpenHelper {

    //InnerClass, untuk mengatur artibut seperti Nama Tabel, nama-nama kolom dan Query
    static abstract class JobEntry implements BaseColumns {
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tb_job";
        static final String ID = "ID";
        static final String Instansi = "Instansi";
        static final String Alamat = "Alamat";
        static final String Syarat = "Syarat";
    }

    private static final String NamaDatabse = "dbjob.db";
    private static final int VersiDatabase = 1;

    //Query yang digunakan untuk membuat Tabel
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+ JobEntry.NamaTabel+
            "("+ JobEntry.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ JobEntry.Instansi+" TEXT NOT NULL, "+ JobEntry.Alamat+
            " TEXT NOT NULL, "+ JobEntry.Syarat+" TEXT NOT NULL)";

    //Query yang digunakan untuk mengupgrade Tabel
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+ JobEntry.NamaTabel;

    DBHelper(Context context) {
        super(context, NamaDatabse, null, VersiDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /*
    public Cursor readAllData(){
        String query = "SELECT * FROM "+ JobEntry.NamaTabel;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }*/

}