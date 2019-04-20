
package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    EditText name,age,email;
    Spinner country,gender;
    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseprofile,databasegetdata;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseprofile= FirebaseDatabase.getInstance().getReference("Users");
        databasegetdata= FirebaseDatabase.getInstance().getReference("Users");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        country = findViewById(R.id.country);
        age = findViewById(R.id.age);
        email=findViewById(R.id.pemail);
        firebaseAuth = FirebaseAuth.getInstance();


        uneditable();
        getData();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


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


    public void saveProfile(View view)
    {

        String Name = name.getText().toString();
        String Gender = gender.getSelectedItem().toString();
        String Country = country.getSelectedItem().toString();
        String Age = age.getText().toString();

            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            ProfileModel profileModel =new ProfileModel (Name,Gender,Country,Age);
            databaseprofile.child(id).setValue(profileModel);
            final Toast toast = Toast.makeText(Profile.this, "Data is Saved", Toast.LENGTH_LONG);
            toast.show();
            getData();
            uneditable();


    }
//    public void getData(View view) {
//
//        databasegetdata.child(id).child("age").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                age.setText(value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                age.setText("Eroor");
//            }
//        });
//        databasegetdata.child(id).child("name").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        String value = dataSnapshot.getValue(String.class);
//                        name.setText(value);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        name.setText("Eroor");
//                    }
//                });
//    }
    public void getData() {
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        databasegetdata.child(id).child("age").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                age.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                age.setText("Eroor");
            }
        });
        databasegetdata.child(id).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                name.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                name.setText("Eroor");
            }
        });
    }
    public void uneditable(View view)
    {
        name.setEnabled(false);
        age.setEnabled(false);
    }
    public void uneditable()
    {

        name.setEnabled(false);
        gender.setEnabled(false);
        country.setEnabled(false);
        age.setEnabled(false);
        email.setEnabled(false);
    }

    public void editable(View view) {
        name.setEnabled(true);
        age.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,homepage.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
