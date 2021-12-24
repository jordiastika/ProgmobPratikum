package com.example.projectpraktikum;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseAdapter extends CursorAdapter {

    private LayoutInflater layoutInflater;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)

    public DatabaseAdapter(Context context, Cursor c, int flags){
        super(context, c, flags);
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = layoutInflater.inflate(R.layout.row_data, viewGroup,false);
        MyHolder holder = new MyHolder();
        holder.ListID = (TextView)v.findViewById(R.id.ListID);
        holder.ListNama = (TextView)v.findViewById(R.id.ListNama);
        holder.ListAlamat = (TextView)v.findViewById(R.id.ListAlamat);
        holder.ListJenis = (TextView)v.findViewById(R.id.ListJenis);
        holder.ListUsia = (TextView)v.findViewById(R.id.ListUsia);
        holder.ListAgama = (TextView)v.findViewById(R.id.ListAgama);
        v.setTag(holder);
        return v;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MyHolder holder= (MyHolder)view.getTag();
        holder.ListID.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.c_id)));
        holder.ListNama.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.c_nama)));
        holder.ListAlamat.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.c_alamat)));
        holder.ListJenis.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.c_id)));
        holder.ListUsia.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.c_id)));
        holder.ListAgama.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.c_id)));



    }

    }
    class MyHolder{
        TextView ListID;
        TextView ListNama;
        TextView ListAlamat;
        TextView ListJenis;
        TextView ListUsia;
        TextView ListAgama;
}
