package com.hasitha.nodemcu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {

    private static final String DTABASE_NAME="color_details";
    private static final int DTABASE_VERSION=1;
    private  String queryToCreateDatabase="create table Color_DB (ID INTEGER PRIMARY KEY AUTOINCREMENT"+
            ",Name VARCHAR(255),Red VARCHAR(255),Green VARCHAR(255),Blue VARCHAR(255))";
    Context context;
    public DB(Context context) {
        super(context, DTABASE_NAME, null, DTABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try
        {
            db.execSQL(queryToCreateDatabase);
            Toast.makeText(context,"table created",Toast.LENGTH_LONG).show();

        }
        catch (Exception e)
        {
            Toast.makeText(context,"error making table",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
