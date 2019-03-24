package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ChooseSpeciality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_speciality);
        BottomNavigationView bottomnav =findViewById(R.id.navmenu);
        bottomnav.setSelectedItemId(R.id.Category);
        bottomnav.setOnNavigationItemSelectedListener(navlistner);


    }
    public void openDocterList(View view)
    {
        Intent intent = new Intent(this, DoctersList.class);
        startActivity(intent);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.Category:
                            openChooseSpeciality();
                            break;
                        case R.id.Record:
                            Toast.makeText(ChooseSpeciality.this,
                                    "record", Toast.LENGTH_LONG).show();
                            break;
                        case R.id.home:
                            KillThisActivity();
                            openhome();


                    }
                    return true;
                }

            };

    public void KillThisActivity()
    {
        this.finish();;
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
}
