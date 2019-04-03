
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    EditText name,city,age; TextView Test;
    Spinner country,gender;
    DatabaseReference databaseprofile,databasegetdata;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseprofile= FirebaseDatabase.getInstance().getReference("profile");
        databasegetdata= FirebaseDatabase.getInstance().getReference("profile");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        age = findViewById(R.id.age);
        Test= findViewById(R.id.testText);
        uneditable();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

   public void saveProfile(View view)
    {

        String Name = name.getText().toString();
        String Gender = gender.getSelectedItem().toString();
        String City = city.getText().toString();
        String Country = country.getSelectedItem().toString();
        String Age = age.getText().toString();
        if (!TextUtils.isEmpty(Name)){
            id =databaseprofile.push().getKey();
            ProfileModel profileModel =new ProfileModel (Name,Gender,City,Country,Age,id);
            databaseprofile.child(id).setValue(profileModel);
            final Toast toast = Toast.makeText(Profile.this, "Data is Saved", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            final Toast toast = Toast.makeText(Profile.this, "Name Can't Be Blank", Toast.LENGTH_LONG);
            toast.show();
        }


    }
    public void getData(View view) {

        databasegetdata.child("-LbA99friU-hTgnv47pk").child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        Test.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Test.setText("Eroor");
                    }
                });
    }
    public void uneditable(View view)
    {
        name.setEnabled(false);
        gender.setEnabled(false);
        city.setEnabled(false);
        country.setEnabled(false);
        age.setEnabled(false);
    }
    public void uneditable()
    {

        name.setEnabled(false);
        gender.setEnabled(false);
        city.setEnabled(false);
        country.setEnabled(false);
        age.setEnabled(false);
    }

    public void editable(View view) {
        name.setEnabled(true);
        gender.setEnabled(true);
        city.setEnabled(true);
        country.setEnabled(true);
        age.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,homepage.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
