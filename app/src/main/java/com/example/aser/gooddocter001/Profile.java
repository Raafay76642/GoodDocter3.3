package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        uneditable();
    }
    public void openChooseSpeciality(View view)
    {
        Intent intentdocter = new Intent(this, ChooseSpeciality.class);
        startActivity(intentdocter);
    }

    public void uneditable()
    {
        EditText ename = findViewById(R.id.name);
        EditText eprof = findViewById(R.id.profession);
        EditText email = findViewById(R.id.email);
        EditText epassword = findViewById(R.id.password);
        ename.setEnabled(false);
        eprof.setEnabled(false);
        email.setEnabled(false);
        epassword.setEnabled(false);
    }
    public void uneditable(View view)
    {
        EditText ename = findViewById(R.id.name);
        EditText eprof = findViewById(R.id.profession);
        EditText email = findViewById(R.id.email);
        EditText epassword = findViewById(R.id.password);
        ename.setEnabled(false);
        eprof.setEnabled(false);
        email.setEnabled(false);
        epassword.setEnabled(false);
    }
    public void editable(View view)
    {
        EditText ename = findViewById(R.id.name);
        EditText eprof = findViewById(R.id.profession);
        EditText email = findViewById(R.id.email);
        EditText epassword = findViewById(R.id.password);
        ename.setEnabled(true);
        eprof.setEnabled(true);
        email.setEnabled(true);
        epassword.setEnabled(true);
    }
}