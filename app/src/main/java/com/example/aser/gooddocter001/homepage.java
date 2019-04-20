package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class homepage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    BottomNavigationView BottomNav;
    TextView htName;
    DatabaseReference mref;
    String id;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        BottomNav = findViewById(R.id.navmenu);
        BottomNav.setOnNavigationItemSelectedListener(navlistner);
        firebaseAuth = FirebaseAuth.getInstance();
        htName = findViewById(R.id.htName);
        mref = FirebaseDatabase.getInstance().getReference("Users");
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        get_Name();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        } else {
            finish();
            openlogin();
        }
    }

    public void openlogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.profile:
                            KillThisActivity();
                            openprofile();
                            break;
                        case R.id.appointments:
                            KillThisActivity();
                            open_apntmnts();
                            break;
                        case R.id.welcome:
                            openhome();
                            break;
                    }
                    return true;
                }

            };


    public void openprofile() {
        Intent intentdocter = new Intent(this, Profile.class);
        startActivity(intentdocter);
    }

    public void openhome() {
        Intent intentdocter = new Intent(this, homepage.class);
        startActivity(intentdocter);
    }

    public void KillThisActivity() {
        this.finish();
    }

    public void open_apntmnts() {
        Intent intentapent = new Intent(this, Apntments.class);
        startActivity(intentapent);
    }


    public void openChooseSpeciality(View view) {
        Intent intentdocter = new Intent(this, ChooseSpeciality.class);
        startActivity(intentdocter);
    }

    public void openProfile(View view) {
        Intent intentdocter = new Intent(this, Profile.class);
        startActivity(intentdocter);
    }

    public void logout(View view) {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public  String get_Name() {
            mref.child(id).child("name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Name = dataSnapshot.getValue(String.class);
                    htName.setText(Name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });return Name;
    }
}