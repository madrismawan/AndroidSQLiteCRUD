package com.rismawan.sqlitecrud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //Digunakan untuk deklarawi list array
    private ArrayList IdList, namaList, alamatList, syaratList;
    private ImageButton actionMenu;
    private Context context;

    public MyAdapter(ArrayList<String> idList, ArrayList<String> namaList, ArrayList<String> alamatList, ArrayList<String> syaratList) {
        this.namaList=namaList;
        this.IdList = idList;
        this.alamatList=alamatList;
        this.syaratList=syaratList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_data, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final String setNama = (String) namaList.get(position);
        final String setId = (String) IdList.get(position);
        final String setAlamat = (String) alamatList.get(position);
        final String setSyarat = (String) syaratList.get(position);
        holder.Nama.setText(setNama);
        holder.Alamat.setText(setAlamat);
        holder.Syarat.setText(setSyarat);

        actionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.action_data);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete:
                                //Menghapus Data Dari Database
                                DBHelper getDatabase = new DBHelper(v.getContext());
                                SQLiteDatabase DeleteData = getDatabase.getWritableDatabase();
                                //Menentukan di mana bagian kueri yang akan dipilih

                                String selection = DBHelper.JobEntry.ID + " LIKE ?";
                                //Menentukan Nama Dari Data Yang Ingin Dihapus
                                String[] selectionArgs = {setId};
                                DeleteData.delete(DBHelper.JobEntry.NamaTabel, selection, selectionArgs);

                                //Menghapus Data pada List dari Posisi Tertentu
                                int position = IdList.indexOf(setId);
                                IdList.remove(position);
                                notifyItemRemoved(position);
                                Toast.makeText(v.getContext(),"Data Dihapus",Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.update:
                                Intent formUpdate = new Intent(v.getContext(), UpdateActivity.class);
                                formUpdate.putExtra("SendId", setId);
                                formUpdate.putExtra("SendInstansi", holder.Nama.getText().toString());
                                formUpdate.putExtra("SendAlamat", holder.Alamat.getText().toString());
                                formUpdate.putExtra("SendSyarat", holder.Syarat.getText().toString());
                                context.startActivity(formUpdate);
                                ((Activity)context).finish();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return IdList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  Nama, Alamat, Syarat;
        public ViewHolder(View itemView) {
            super(itemView);
            context= itemView.getContext();
            actionMenu = itemView.findViewById(R.id.imageButton);
            Nama = itemView.findViewById(R.id.vinstansi);
            Alamat = itemView.findViewById(R.id.valamat);
            Syarat = itemView.findViewById(R.id.vsyarat);
        }
    }
}
