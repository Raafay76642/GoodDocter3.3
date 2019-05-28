package com.example.aser.gooddocter001;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Apntments extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private Apntments_adapter adapter;
    private List<Apntments_Model> apntments_List;
    DatabaseReference mref;
    FirebaseAuth firebaseAuth;
    Button bBooked,bHistory;
    private ProgressDialog mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apntments);
        firebaseAuth=FirebaseAuth.getInstance();
        mref= FirebaseDatabase.getInstance().getReference("Doctors");
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        apntments_List = new ArrayList<>();
        adapter = new Apntments_adapter(this, apntments_List);
        recyclerView1.setAdapter(adapter);
        bBooked=findViewById(R.id.bBooked);
        mProgressBar=new ProgressDialog(this);
        bBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_data_booked();
            }
        });
        bHistory=findViewById(R.id.bhistory);
        bHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apntments_List.clear();
                get_data_history();
            }
        });
            get_data_booked();

    }
    public void get_data_booked(){
        String key=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mProgressBar.setMessage("Loading");
        mProgressBar.show();
        Query query =FirebaseDatabase.getInstance().getReference("Appointments").child("Booked").orderByChild("status").equalTo("booked");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    apntments_List.clear();
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Apntments_Model apntments_model = snapshot.getValue(Apntments_Model.class);
                            String test=apntments_model.getuID();
                            if (test.equals(key)){
                                apntments_List.add(apntments_model);
                            }
                        }
                        mProgressBar.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        apntments_List.clear();
                        adapter.notifyDataSetChanged();
                        recyclerView1.setEnabled(false);
                        mProgressBar.dismiss();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    mProgressBar.dismiss();
                    adapter.notifyDataSetChanged();

                }
            });
    }
    public void get_data_history(){
        mProgressBar.setMessage("Loading");
        mProgressBar.show();
        String key=FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query =FirebaseDatabase.getInstance().getReference("Appointments").child("Booked").orderByChild("status").equalTo("complete");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    apntments_List.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Apntments_Model apntments_model = snapshot.getValue(Apntments_Model.class);
                        String test=apntments_model.getuID();
                        if (test.equals(key)){
                            apntments_List.add(apntments_model);
                        }}
                    adapter.notifyDataSetChanged();
                    mProgressBar.dismiss();
                }
                else {
                    apntments_List.clear();
                    adapter.notifyDataSetChanged();
                    recyclerView1.setEnabled(false);
                    mProgressBar.dismiss();
                    Toast.makeText(Apntments.this, "No Completed Appointment", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgressBar.dismiss();
                adapter.notifyDataSetChanged();

            }
        });
    }
    public void open_homepage(){
        Intent intentdocter = new Intent(this, homepage.class);
        startActivity(intentdocter);

    }


    @Override
    public void onBackPressed() {
        open_homepage();
        super.onBackPressed();
    }


}
