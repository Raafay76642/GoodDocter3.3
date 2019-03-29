package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class Profile extends AppCompatActivity {
    EditText name,gender,city,country,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        city = findViewById(R.id.city);
        country = findViewById(R.id.countery);
        age = findViewById(R.id.age);
        uneditable();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    public void addProfile(View view)
    {
        String Name = name.getText().toString();
        String Gender = gender.getText().toString();
        String City = city.getText().toString();
        String Country = country.getText().toString();
        String Age = age.getText().toString();

    }





    public void uneditable()
    {
        name.setEnabled(false);
        gender.setEnabled(false);
        city.setEnabled(false);
        country.setEnabled(false);
        age.setEnabled(false);
    }
    public void uneditable(View view)
    {

        name.setEnabled(false);
        gender.setEnabled(false);
        city.setEnabled(false);
        country.setEnabled(false);
        age.setEnabled(false);
    }
    public void editable(View view)
    {
        name.setEnabled(true);
        gender.setEnabled(true);
        city.setEnabled(true);
        country.setEnabled(true);
        age.setEnabled(true);
    }




}
