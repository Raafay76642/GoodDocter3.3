package com.example.aser.gooddocter001;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Prescription extends AppCompatActivity {
    private ProgressDialog mProgressBarsaving;
    ImageView prescrip;
    TextView pdes;
    DatabaseReference mref;
    String p;
    String aID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        prescrip=findViewById(R.id.prescrip);
        prescrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent=new Intent(Prescription.this,Show_Image.class);
             intent.putExtra("link",p);
             startActivity(intent);


            }
        });
        pdes=findViewById(R.id.Pdes);
        mref= FirebaseDatabase.getInstance().getReference("Appointments");
        get_intent();
        getData();

    }
    public void get_intent(){
        Intent intent=getIntent();
        aID=intent.getStringExtra("aID");
//        Toast.makeText(this, aID, Toast.LENGTH_SHORT).show();
    }
    public void getData(){
        mref.child("Booked").child(aID).child("prescription").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                p=dataSnapshot.getValue(String.class);
            Glide.with(Prescription.this).load(dataSnapshot.getValue(String.class)).fitCenter().into(prescrip);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mref.child("Booked").child(aID).child("PresDres").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pdes.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
