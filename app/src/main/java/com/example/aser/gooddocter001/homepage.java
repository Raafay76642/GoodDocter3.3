package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class homepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        BottomNavigationView bottomnav =findViewById(R.id.navmenu);
        bottomnav.setOnNavigationItemSelectedListener(navlistner);


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
                            Toast.makeText(homepage.this,
                                    "record", Toast.LENGTH_LONG).show();
                            break;
                        case R.id.home:
                            openhome();


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
        this.finish();;
    }

}
