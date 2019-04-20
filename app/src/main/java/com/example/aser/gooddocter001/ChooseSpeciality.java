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

    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.profile:
                            openProfile();
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


    public void openProfile()
    {
        Intent intentdocter = new Intent(this, Profile.class);
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
    public void doc_list_card(View view){

        Intent intent= new Intent(this, DoctersList.class);
        intent.putExtra("selected","Cardiologist");
        startActivity(intent);
    }
    public void doc_list_dent(View view){

        Intent intent= new Intent(this, DoctersList.class);
        intent.putExtra("selected","Dentist");
        startActivity(intent);
    }
    public void doc_list_neuro(View view){

        Intent intent= new Intent(this, DoctersList.class);
        intent.putExtra("selected","Neuro Surgeon");
        startActivity(intent);


    }
    public void doc_list_ortho(View view){

        Intent intent= new Intent(this, DoctersList.class);
        intent.putExtra("selected","Orthopedist");
        startActivity(intent);
    }
}
