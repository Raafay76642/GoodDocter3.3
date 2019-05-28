package com.example.aser.gooddocter001;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        checkforPermission();
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

    public void openHealthD(View view){
        Intent intent=new Intent(this,Health_Record.class);
        startActivity(intent);
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
    public void checkforPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Camera permission not granted", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.CAMERA}, 101);

            // Permission is not granted
        }
        else if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.RECORD_AUDIO}, 101);

//            Toast.makeText(this, "Microphone permission not granted", Toast.LENGTH_SHORT).show();
            // Permission is not granted
        }
        else if (ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.MODIFY_AUDIO_SETTINGS, Manifest.permission.MODIFY_AUDIO_SETTINGS}, 101);

//            Toast.makeText(this, "Speaker permission not granted", Toast.LENGTH_SHORT).show();
            // Permission is not granted

        }
        else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 101);

//            Toast.makeText(this, "Speaker permission not granted", Toast.LENGTH_SHORT).show();
            // Permission is not granted

        }
        else if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE}, 101);

//            Toast.makeText(this, "Speaker permission not granted", Toast.LENGTH_SHORT).show();
            // Permission is not granted

        }

    }

}