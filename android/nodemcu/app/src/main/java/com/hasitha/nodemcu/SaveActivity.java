package com.hasitha.nodemcu;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity {
Button save;
EditText colorName;
TextView er,colorr,g,b,r;
DB Db;
    String red,green,blue;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
         red = extra.getString("r");
         green = extra.getString("g");
         blue = extra.getString("b");
        er = findViewById(R.id.lblErr);
        Db=new DB(SaveActivity.this);

        createDb();
//        colorCode.setText(message);
        save=(Button)findViewById(R.id.btnSave);
        colorName=(EditText)findViewById(R.id.txtColorNamr);
        colorr=(TextView)findViewById(R.id.lblColor);
        r=(TextView)findViewById(R.id.txtR);
        g=(TextView)findViewById(R.id.txtG);
        b=(TextView)findViewById(R.id.txtB);
        r.setText(red);
        g.setText(green);
        b.setText(blue);

        colorr.setBackgroundColor(Color.rgb(Float.parseFloat(r.getText().toString()),Float.parseFloat(g.getText().toString()),Float.parseFloat(b.getText().toString())));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorName.getText().toString().equals("")){
                    er.setText("Please Enter A Color Name");
                }else {
                    String c=er.getText().toString();
                    saveColor(c);
                }
            }
        });
    }

    public  void createDb(){
        try
        {
            Db.getReadableDatabase();
        }
        catch (Exception e)
        {
            Toast.makeText(SaveActivity.this,"error",Toast.LENGTH_LONG).show();

        }
    }
    public void saveColor(String colorName){
        try
        {
            SQLiteDatabase sq=Db.getWritableDatabase();
             if(sq!=null){
                 ContentValues contentValues=new ContentValues();
                 contentValues.put("Name",colorName);
                 contentValues.put("Red",red);
                 contentValues.put("Green",green);
                 contentValues.put("Blue",blue);
                long checkIfWueryRuns= sq.insert("Color_DB",null,contentValues);
                if(checkIfWueryRuns!=-1){
                    Toast.makeText(SaveActivity.this,"Values Inserted",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(SaveActivity.this,"Values not inserted",Toast.LENGTH_LONG).show();

                }
             }
             else {
                 Toast.makeText(SaveActivity.this,"Database is null",Toast.LENGTH_LONG).show();

             }
        }
        catch (Exception e)
        {
            Toast.makeText(SaveActivity.this,"error",Toast.LENGTH_LONG).show();

        }
    }
}
