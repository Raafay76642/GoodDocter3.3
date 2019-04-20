package com.example.aser.gooddocter001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctersList extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mref;
    private RecyclerView recyclerView;
    private DoctorsAdapter adapter;
    private List<Doctor_Model> doctorModelList;
    String selected;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docters_list);
        firebaseAuth=FirebaseAuth.getInstance();
        mref=FirebaseDatabase.getInstance().getReference("Doctors");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorModelList = new ArrayList<>();
        adapter = new DoctorsAdapter(this, doctorModelList);
        recyclerView.setAdapter(adapter);
        Intent intent=getIntent();
        selected=intent.getStringExtra("selected");
        Query query = FirebaseDatabase.getInstance().getReference("Doctors")
                .orderByChild("department")
                .equalTo(selected);
        query.addValueEventListener(valueEventListener);



    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            doctorModelList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Doctor_Model doctorModel = snapshot.getValue(Doctor_Model.class);
                    doctorModelList.add(doctorModel);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
