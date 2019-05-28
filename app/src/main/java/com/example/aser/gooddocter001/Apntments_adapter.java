package com.example.aser.gooddocter001;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Apntments_adapter extends RecyclerView.Adapter<Apntments_adapter.ApntmentsViewHolder> {

    private Context mCtx;
    private List<Apntments_Model> apntmentsList;
    String x;

    public Apntments_adapter(Context mCtx, List<Apntments_Model> apntmentsList) {
        this.mCtx = mCtx;
        this.apntmentsList = apntmentsList;
    }

    @NonNull
    @Override
    public ApntmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycler_apt_design, parent, false);
        return new ApntmentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApntmentsViewHolder holder, int position) {
        DatabaseReference mref2,mref;
        Apntments_Model apntments_model = apntmentsList.get(position);
        holder.textViewName.setText(apntments_model.dName);
        holder.textViewDate.setText(apntments_model.date);
        holder.textViewtime.setText(apntments_model.sTime);
        mref= FirebaseDatabase.getInstance().getReference("Appointments").child("Booked").child(apntments_model.getaID());
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    x=dataSnapshot.child("status").getValue(String.class);
                    holder.cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (x.equals("booked")){
                                Intent intent=new Intent(mCtx,Appoinments_details.class);
                                intent.putExtra("aID",apntments_model.aID);
                                mCtx.startActivity(intent);
                            }
                            else {
                                Intent intent=new Intent(mCtx,Prescription.class);
                                intent.putExtra("aID",apntments_model.aID);
                                mCtx.startActivity(intent);
                            }

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mref2= FirebaseDatabase.getInstance().getReference("Doctors").child(apntments_model.getdID());
        mref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Glide.with(mCtx).load(dataSnapshot.child("profilePic").getValue(String.class)).fitCenter().into(holder.Docpic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return apntmentsList.size();
    }

    class ApntmentsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewDate, textViewtime;
        CardView cardView;
        ImageView Docpic;

        public ApntmentsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name1);
            textViewDate = itemView.findViewById(R.id.text_date1);
            textViewtime = itemView.findViewById(R.id.text_view_time1);
            cardView= itemView.findViewById(R.id.cardview1);
            Docpic=itemView.findViewById(R.id.DocImg1);
        }
    }
}