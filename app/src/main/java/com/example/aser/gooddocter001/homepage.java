package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class homepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        BottomNavigationView bottomnav =findViewById(R.id.navmenu);
        bottomnav.setOnNavigationItemSelectedListener(navlistner);
        uneditable();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.Category:
                            KillThisActivity();
                           openChooseSpeciality();
                            break;
                        case R.id.Record:
                            open_apntmnts();
                            break;
                        case R.id.home:
                            openhome();
                            break;
                    }
                    return true;
                }

            };

    public void openChooseSpeciality(View view)
    {
        Intent intentdocter = new Intent(this, ChooseSpeciality.class);
        startActivity(intentdocter);
    }
    public void openChooseSpeciality()
    {
        Intent intentdocter = new Intent(this, ChooseSpeciality.class);
        startActivity(intentdocter);
    }
    public void openhome()
    {
        Intent intentdocter = new Intent(this, homepage.class);
        startActivity(intentdocter);
    }
    public void KillThisActivity()
    {
        this.finish();
    }
    public void open_apntmnts()
    {
        Intent intentdocter = new Intent(this, Apntments.class);
        startActivity(intentdocter);
    }
    public void uneditable()
    {
        EditText ename;
        ename = findViewById(R.id.name);
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
