package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Booking extends AppCompatActivity {
    String time,dkey;
    DatabaseReference mref;
    FirebaseAuth firebaseAuth;
    String Ukey;
    TextView text_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        get_Intent();
        text_time=(TextView)findViewById(R.id.show_time);
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
//            text_time.setText(afterAddingMins.toString());
            String mytime = java.text.DateFormat.getTimeInstance().format(afterAddingMins);
            text_time.setText(mytime.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }





    }
    public void get_Intent(){
        Intent intent=getIntent();
        time=intent.getStringExtra("time");
        dkey=intent.getStringExtra("dkey");

    }
}
