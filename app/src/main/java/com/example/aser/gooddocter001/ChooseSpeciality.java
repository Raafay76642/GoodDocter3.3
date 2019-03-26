package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ChooseSpeciality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_speciality);
        BottomNavigationView bottomnav =findViewById(R.id.navmenu);
        bottomnav.setSelectedItemId(R.id.profile);
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
                        case R.id.profile:
                            openChooseSpeciality();
                            break;
                        case R.id.appointments:
                            KillThisActivity();
                            open_apntmnts();
                            break;
                        case R.id.welcome:
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
    public void open_apntmnts()
    {
        Intent intentapent = new Intent(this, Apntments.class);
        startActivity(intentapent);
    }
}
