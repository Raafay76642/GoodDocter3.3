package com.example.aser.gooddocter001;

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

import java.util.Calendar;
import java.util.Date;

public class DetailActivity extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker;
    TextView txtDate;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ListView timeSlot;
    String timeSlotsArray[]={"10:00 PM","10:30 AM ","11:00 PM ","11:30 AM","12:00 PM"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        txtDate=(TextView) findViewById(R.id.in_date);
        btnDatePicker.setOnClickListener(this);
        timeSlot = findViewById(R.id.timeScheduleList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.time_slots_layout_design, R.id.textView, timeSlotsArray);
        timeSlot.setAdapter(arrayAdapter);



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

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            c.add(Calendar.DATE, +7);
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.show();

        }



    }



}
