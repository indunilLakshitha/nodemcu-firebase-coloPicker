package com.hasitha.nodemcu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    DB Db;
    Button history;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ArrayList<ModelClass> modelClassArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        modelClassArrayList=new ArrayList<>();
        recyclerView =(RecyclerView)findViewById(R.id.rcyclrView);
        history=(Button)findViewById(R.id.btnGetHistory);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHistory();
            }
        });

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
                        modelClassArrayList.add(new ModelClass(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4)
                        ));
                    }
                    recyclerViewAdapter=new RecyclerViewAdapter((modelClassArrayList));
                    recyclerView.hasFixedSize();
                    recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                    recyclerView.setAdapter(recyclerViewAdapter);
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
