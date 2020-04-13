package com.hasitha.nodemcu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {
RecyclerView his;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        his =(RecyclerView)findViewById(R.id.showHistory);

    }

    public  void showHistory(){
        try {
            DB DBObject=new DB(HistoryActivity.this);
            SQLiteDatabase sqLiteDatabase =DBObject.getReadableDatabase();
            if(sqLiteDatabase!=null){
                Cursor cursor =sqLiteDatabase.rawQuery("select * from Color_DB",null);
                StringBuffer stringBuffer=new StringBuffer();
                if(cursor.getCount()==0){
                    Toast.makeText(HistoryActivity.this,"no data returned",Toast.LENGTH_LONG).show();

                }
                else {
                    while (cursor.moveToNext())
                    {
                        stringBuffer.append("ID: "+cursor.getInt(0)+"\n");
                        stringBuffer.append("Name: "+cursor.getString(1)+"\n");
                        stringBuffer.append("Red: "+cursor.getString(2)+"\n");
                        stringBuffer.append("Green: "+cursor.getString(3)+"\n");
                        stringBuffer.append("Blue: "+cursor.getString(4)+"\n");
                    }


                }
            }
            else {
                Toast.makeText(HistoryActivity.this,"database is null",Toast.LENGTH_LONG).show();

            }
        }
        catch (Exception e){

        }
    }
}
