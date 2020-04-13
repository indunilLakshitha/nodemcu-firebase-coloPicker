package com.hasitha.nodemcu;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Map;

import static android.graphics.Color.*;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class SearchActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE ="key";
    FirebaseDatabase database;
    DatabaseReference ref;
    EditText deviceNo;
    TextView r,g,b,clolorView;
    FloatingActionButton bk;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        deviceNo=(EditText)findViewById(R.id.txtDeviceNo);
        r=(TextView)findViewById(R.id.txtR);
        g=(TextView)findViewById(R.id.txtG);
        b=(TextView)findViewById(R.id.txtB);
        clolorView=(TextView)findViewById(R.id.txtColor);
        Button btnGet=(Button)findViewById(R.id.btnGet);
        Button btnSa=(Button)findViewById(R.id.btnSave);
        bk=(FloatingActionButton)findViewById(R.id.btnBack);
        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getColor();
                clolorView.setBackgroundColor(Color.rgb(Float.parseFloat(r.getText().toString()),Float.parseFloat(g.getText().toString()),Float.parseFloat(b.getText().toString())));
            }
        });

        btnSa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveColor();
            }
        });

    }
    public  void saveColor(){
        Intent intent = new Intent(SearchActivity.this, SaveActivity.class);
        intent.putExtra("r",r.getText().toString() );
        intent.putExtra("g",g.getText().toString() );
        intent.putExtra("b",b.getText().toString() );
        startActivity(intent);
    }


   public Void getColor(String deviceNo){
            database  = FirebaseDatabase.getInstance();
            ref= database.getReference("color/"+deviceNo);


           ref.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {

                   Map<String,String> map=(Map<String, String>)dataSnapshot.getValue();
                   String R=map.get("R");
                   String G=map.get("G");
                   String B=map.get("B");

//                   Toast.makeText(SearchActivity.this,n,Toast.LENGTH_SHORT).show();
                   r.setText(R.toString());
                   g.setText(G.toString());
                   b.setText(B.toString());
                   System.out.print("firebase retriev succses");
               }

               @Override
               public void onCancelled(DatabaseError error) {
                   Toast.makeText(SearchActivity.this,"ERROR",Toast.LENGTH_SHORT).show();

               }
           });


     return null;
   }




}
