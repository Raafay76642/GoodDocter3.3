package com.example.aser.gooddocter001;

import android.content.ClipData;
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
import android.widget.AdapterView;
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
   public List<String> timeSlotlList;
  ArrayAdapter<String> arrayAdapter;


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
        timeSlot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(DetailActivity.this,Booking.class);
                    intent.putExtra("dkey",key);
                    intent.putExtra("time",timeSlotlList.get(position));
                    intent.putExtra("date",date);
                    startActivity(intent);

                }
        });




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

                            String selectedDate = dayOfMonth + ":" + (monthOfYear ) + ":" + year;
                            txtDate.setText(selectedDate);
                            date=txtDate.getText().toString();
                            mref=FirebaseDatabase.getInstance().getReference("Doctors").child(key).child(selectedDate);
                            mref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    timeSlotlList.clear();
                                    if (dataSnapshot.exists()) {

                                        String title1 = dataSnapshot.child("slot1").getValue(String.class);
                                        timeSlotlList.add(title1);
                                        String title2 = dataSnapshot.child("slot2").getValue(String.class);
                                        timeSlotlList.add(title2);
                                        String title3 = dataSnapshot.child("slot3").getValue(String.class);
                                        timeSlotlList.add(title3);
                                        String title4 = dataSnapshot.child("slot4").getValue(String.class);
                                        timeSlotlList.add(title4);
                                        String title5 = dataSnapshot.child("slot5").getValue(String.class);
                                        timeSlotlList.add(title5);
                                        arrayAdapter.notifyDataSetChanged();

                                    }
                                    else {
                                        timeSlotlList.clear();
                                        arrayAdapter.notifyDataSetChanged();
                                        timeSlot.setEnabled(false);
                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            c.add(Calendar.DATE, +1);
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.show();

        }



    }



}
