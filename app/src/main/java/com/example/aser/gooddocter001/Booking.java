package com.example.aser.gooddocter001;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Path;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Booking extends AppCompatActivity {
    String time,dID,etime,date,uID,dname,uname,aId;
    Integer fee;
    DatabaseReference mref,mref2,mref3;
    String status;
    FirebaseAuth firebaseAuth;
    TextView text_time,text_date;
    Button button;
    int position;
    List<String> timeSlotlUpdated;
    ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        get_Intent();
        text_time=(TextView)findViewById(R.id.show_time);
        text_date=(TextView)findViewById(R.id.show_date);
        text_date.setText(date);
        mProgress=new ProgressDialog(this);
        cal_eTime();
        loadDoc();
        button= (Button) findViewById(R.id.buttonShowCustomDialog);
        // add button  listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                payment_dialogue();
            }
        });


    }
    public void payment_dialogue(){

        // custom dialog
        final Dialog dialog = new Dialog(Booking.this);
        dialog.setContentView(R.layout.dialouge_layout);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
       TextView text_fee=dialog.findViewById(R.id.tfee);
        text_fee.setText(String.valueOf(fee+" INR"));

        text.setText("One More Step!" +
                "Click OK to pay and book appoinmet");
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        Button cancleB=(Button)dialog.findViewById(R.id.dialogButtonCancel);
        cancleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passIntent();
                dialog.dismiss();

            }
        });

        dialog.show();
    }
    public void cal_eTime(){
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
        DateFormat sdf = new SimpleDateFormat("hh:mm");
        try {
            // To get the date object from the string just called the
            // parse method and pass the time string to it. This method
            // throws ParseException if the time string is invalid.
            // But remember as we don't pass the date information this
            // date object will represent the 1st of january 1970.
            Date date = sdf.parse(time);
            long curTimeInMs = date.getTime();
            Date afterAddingMins = new Date(curTimeInMs + (15 * ONE_MINUTE_IN_MILLIS));
            etime=new SimpleDateFormat("kk:mm").format(afterAddingMins);
            String s;
            s=time+" - "+etime;
//            text_time.setText(afterAddingMins.toString());
            //    String mytime = java.text.DateFormat.getTimeInstance().format(afterAddingMins);
            text_time.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void book_apt(){
        mProgress.setMessage("Booking");
        mProgress.show();
        mref=FirebaseDatabase.getInstance().getReference("Appointments").child("Booked");
        uID=FirebaseAuth.getInstance().getCurrentUser().getUid();
        status="booked";
        aId=mref.push().getKey();
        Apntments_Model apoinmentsModel=new Apntments_Model(dID,uID,status,etime,time,date,dname,uname,aId);
        mref.child(aId).setValue(apoinmentsModel);
        mref.child(aId).child("active").setValue("0");
        Toast.makeText(Booking.this, "Appointment Booked", Toast.LENGTH_LONG).show();
    mProgress.dismiss();
    }

    public void passIntent(){
        mref=FirebaseDatabase.getInstance().getReference("Appointments").child("Booked");
        uID=FirebaseAuth.getInstance().getCurrentUser().getUid();
        status="booked";
        aId=mref.push().getKey();
        Intent intent=new Intent(Booking.this,checksum.class);
        intent.putExtra("dID",dID);
        intent.putExtra("uID",uID);
        intent.putExtra("status","booked");
        intent.putExtra("etime",etime);
        intent.putExtra("stime",time);
        intent.putExtra("date",date);
        intent.putExtra("dname",dname);
        intent.putExtra("uname",uname);
        intent.putExtra("aID",aId);
        intent.putExtra("fee",fee.toString());
        startActivity(intent);
    }

    public void loadDoc(){
        mProgress.setMessage("Please wait");
        mProgress.show();
        mref=FirebaseDatabase.getInstance().getReference("Doctors").child(dID);

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fee=Integer.valueOf(dataSnapshot.child("fee").getValue(String.class));
                dname=dataSnapshot.child("name").getValue(String.class);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgress.dismiss();
            }
        });
        uID=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mref2=FirebaseDatabase.getInstance().getReference("Users").child(uID);
        mref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uname=dataSnapshot.child("name").getValue(String.class);
            mProgress.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgress.dismiss();
            }
        });
    }
    public void get_Intent(){
        Intent intent=getIntent();
        time=intent.getStringExtra("time");
        dID=intent.getStringExtra("dkey");
        date=intent.getStringExtra("date");

    }
}
