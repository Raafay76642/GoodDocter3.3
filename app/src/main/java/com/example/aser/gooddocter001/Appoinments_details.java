package com.example.aser.gooddocter001;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Appoinments_details extends AppCompatActivity {
    EditText uname,timeInterval,date,status;
    TextView dname;
    ImageView dImg;
    Button call;
    DatabaseReference mref,mref2;
    Apntments_Model apntments_model;
    private ProgressDialog mProgressBar;
    String aID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinments_details);
        uname=findViewById(R.id.uname);
        dname=findViewById(R.id.dname);
//        Just for test purpose
        dname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        }
        });
        timeInterval=findViewById(R.id.time_interval);
        date=findViewById(R.id.date);
        dImg=findViewById(R.id.dimg);
        call=findViewById(R.id.call);
        mProgressBar=new ProgressDialog(this);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkforPermission();
            }
        });
        status=findViewById(R.id.status);
        get_intent();
        getApp_data();
        uneditable();
        verify();
    }
    public void get_intent(){
        Intent intent=getIntent();
        aID=intent.getStringExtra("aID");
    }
    public void getApp_data(){
        mProgressBar.setMessage("Loading");
        mProgressBar.show();
        mref= FirebaseDatabase.getInstance().getReference("Appointments").child("Booked").child(aID);
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                        Apntments_Model apntments_model = dataSnapshot.getValue(Apntments_Model.class);

//                               apntments_model.setuName(dataSnapshot.child(aID).getValue(Apntments_Model.class).getuName());
//                        apntments_model.setdName(dataSnapshot.child(aID).getValue(Apntments_Model.class).getdName());
//                        apntments_model.setsTime(dataSnapshot.child(aID).getValue(Apntments_Model.class).getsTime());
//                        apntments_model.seteTime(dataSnapshot.child(aID).getValue(Apntments_Model.class).geteTime());
//                        apntments_model.setDate(dataSnapshot.child(aID).getValue(Apntments_Model.class).getDate());
//                        apntments_model.setStatus(dataSnapshot.child(aID).getValue(Apntments_Model.class).getStatus());
                        uname.setText(apntments_model.getuName());
                        dname.setText(apntments_model.getdName());
                        timeInterval.setText(apntments_model.getsTime()+"-"+apntments_model.geteTime());
                        date.setText(apntments_model.getDate());
                        status.setText(apntments_model.getStatus());
                    mref2=FirebaseDatabase.getInstance().getReference("Doctors").child(apntments_model.getdID());
                    mref2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Glide.with(Appoinments_details.this).load(dataSnapshot.child("profilePic").getValue(String.class)).fitCenter().into(dImg);
                            mProgressBar.dismiss();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            mProgressBar.dismiss();

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgressBar.dismiss();

            }
        });
    }
public void uneditable(){
        uname.setEnabled(false);
        timeInterval.setEnabled(false);
        date.setEnabled(false);
        status.setEnabled(false);
        call.setEnabled(false);
}
public void verify(){
    mref= FirebaseDatabase.getInstance().getReference("Appointments").child("Booked").child(aID);
    mref.child("active").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           String s= dataSnapshot.getValue(String.class);
                if (s.equals("1")){
                    call.setEnabled(true);
                    call.setBackground(getResources().getDrawable(R.drawable.rountcorner));
                    call.setBackgroundColor(getResources().getColor(R.color.parrot));
                    call.setTextColor(getResources().getColor(R.color.white));
                }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

}
public void checkforPermission(){
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "Camera permission not granted", Toast.LENGTH_SHORT).show();
        // Permission is not granted
    }
   else if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "Microphone permission not granted", Toast.LENGTH_SHORT).show();
        // Permission is not granted
    }
    else if (ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS)
            != PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "Speaker permission not granted", Toast.LENGTH_SHORT).show();
        // Permission is not granted

    }

        else {
        Intent intent = new Intent(Appoinments_details.this, Calling.class);
        intent.putExtra("room",aID);
        startActivity(intent);
    }

}
}
