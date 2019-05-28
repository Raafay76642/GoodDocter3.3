package com.example.aser.gooddocter001;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Health_Record extends AppCompatActivity {
CheckBox cbF,cbH,cbS,cbD,cbHP;
Spinner bp;
Button bSave;
EditText details;
DatabaseReference mref;
FirebaseAuth firebaseAuth;
String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__record);
        cbD=findViewById(R.id.cbD);
        cbF=findViewById(R.id.cbF);
        bSave=findViewById(R.id.bSave);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });
        cbH=findViewById(R.id.cbH);
        cbS=findViewById(R.id.cbS);
        cbHP=findViewById(R.id.cbHP);
        bp=findViewById(R.id.bp);
        mref= FirebaseDatabase.getInstance().getReference("Users");
        details=findViewById(R.id.details);
        firebaseAuth=FirebaseAuth.getInstance();
        uid=firebaseAuth.getInstance().getCurrentUser().getUid();
        getdata();
    }
    public void savedata(){
        mref=mref.child(uid).child("HR");
        if(cbD.isChecked()){
            mref.child("diabetes").setValue("1");
        }
        else {
            mref.child("diabetes").setValue("0");
        }
        if(cbF.isChecked()){
            mref.child("fever").setValue("1");
        }
        else {
            mref.child("fever").setValue("0");
        }
        if(cbH.isChecked()){
            mref.child("headache").setValue("1");
        }
        else {
            mref.child("headache").setValue("0");
        }
        if(cbHP.isChecked()){
            mref.child("hp").setValue("1");
        }
        else {
            mref.child("hp").setValue("0");
        }
        if(cbS.isChecked()){
            mref.child("smoker").setValue("1");
        }
        else {
            mref.child("smoker").setValue("0");
        }
        mref.child("bp").setValue(String.valueOf(bp.getSelectedItemPosition()));
        mref.child("details").setValue(details.getText().toString());
    }
    public void getdata(){
        mref.child(uid).child("HR").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    HealthD_model healthDModel=dataSnapshot.getValue(HealthD_model.class);
                    if (healthDModel.getSmoker().equals("1")){
                        cbS.setChecked(true);
                    }
                    if (healthDModel.getDiabetes().equals("1")){
                        cbD.setChecked(true);
                    }
                    if (healthDModel.getFever().equals("1")){
                        cbF.setChecked(true);
                    }
                    if (healthDModel.getHeadache().equals("1")){
                        cbH.setChecked(true);
                    }
                    if (healthDModel.getHp().equals("1")){
                        cbHP.setChecked(true);
                    }
                    bp.setSelection(Integer.parseInt(healthDModel.getBp()));
                    details.setText(healthDModel.getDetails());

                }
                else
                    Toast.makeText(Health_Record.this, "Kindly update your health details", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
