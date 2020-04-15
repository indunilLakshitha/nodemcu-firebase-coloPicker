package com.hasitha.nodemcu;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
    TextView r,g,b,clolorView,err;
    Button btnGet;
    FloatingActionButton bk,share;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        deviceNo=(EditText)findViewById(R.id.txtDeviceNo);

        if(isNetworkAvailable()==false){
            openDialog();
        }

        r=(TextView)findViewById(R.id.txtR);
        g=(TextView)findViewById(R.id.txtG);
        b=(TextView)findViewById(R.id.txtB);
        err=(TextView)findViewById(R.id.txtError);
        clolorView=(TextView)findViewById(R.id.txtColor);
        btnGet=(Button)findViewById(R.id.btnGet);
        Button btnSa=(Button)findViewById(R.id.btnSave);
        bk=(FloatingActionButton)findViewById(R.id.btnBack);
        share=(FloatingActionButton)findViewById(R.id.btnShare);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareColor();
            }
        });
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
                String dNo=deviceNo.getText().toString();
                if(dNo.equals("")){
                    err.setText("Please Enter a Device No");

                }else   {
                    getColor(dNo);
                }


//                alternativeGetColor();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void alternativeGetColor(){
        r.setText("120");
        g.setText("134");
        b.setText("254");
        clolorView.setBackgroundColor(Color.rgb(Float.parseFloat(r.getText().toString()),Float.parseFloat(g.getText().toString()),Float.parseFloat(b.getText().toString())));

    }

   public Void getColor(String deviceNo){

            database  = FirebaseDatabase.getInstance();
       ref= database.getReference("color").child(deviceNo);

                ref.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                            Log.e("COLOR",""+dataSnapshot.getValue());
                         String R = null,G = null,B=null;
                        for (DataSnapshot postSnapshot_variable : dataSnapshot.getChildren()) {

                          if(postSnapshot_variable.getKey().equals("R")){
                              r.setText(postSnapshot_variable.getValue().toString());
                          }
                            if(postSnapshot_variable.getKey().equals("G")){
                                g.setText(postSnapshot_variable.getValue().toString());
                            }
                            if(postSnapshot_variable.getKey().equals("B")){
                                b.setText(postSnapshot_variable.getValue().toString());
                            }

                            clolorView.setBackgroundColor(Color.rgb(Float.parseFloat(r.getText().toString()),Float.parseFloat(g.getText().toString()),Float.parseFloat(b.getText().toString())));

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(SearchActivity.this,"ERROR",Toast.LENGTH_SHORT).show();

                    }
                });


       return null;


   }

   public void shareColor(){
        String R=r.getText().toString();
        String G=g.getText().toString();
        String B=b.getText().toString();
        String myText=R +" "+G+" "+B;
       Intent share = new Intent( Intent.ACTION_SEND);
       share.setType("text/plain");
       share.putExtra(Intent.EXTRA_TEXT, myText);
       startActivity(Intent.createChooser(share, "Share using"));
   }

    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
