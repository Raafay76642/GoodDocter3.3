package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker;
    TextView txtDate;
    String date;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ListView timeSlot;
    DatabaseReference mref;
    String key;
    private List<String> timeSlotlList;
    ArrayAdapter<String> arrayAdapter;
    Query query;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        txtDate=(TextView) findViewById(R.id.in_date);
        btnDatePicker.setOnClickListener(this);
        timeSlot = findViewById(R.id.timeScheduleList);

        timeSlotlList=new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.time_slots_layout_design, R.id.textView, timeSlotlList);
        timeSlot.setAdapter(arrayAdapter);
        Intent intent=getIntent();
        key=intent.getStringExtra("key");
        mref=FirebaseDatabase.getInstance().getReference("Doctors").child(key).child("21:3:2019");



    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

//Data dialoague Lucnher
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + ":" + (monthOfYear ) + ":" + year);
                            date=txtDate.getText().toString();

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            c.add(Calendar.DATE, +1);
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.show();

        }

       mref.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               String z= dataSnapshot.getValue(String.class);
               timeSlotlList.add(z);
               arrayAdapter.notifyDataSetChanged();

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });


    }
//    ValueEventListener valueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            if (dataSnapshot.exists()) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String data=dataSnapshot.getValue(String.class);
//                    timeSlotlList.add(data);
//
//                }
//                arrayAdapter.notifyDataSetChanged();
//            }
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//
//        }
//    };



}
