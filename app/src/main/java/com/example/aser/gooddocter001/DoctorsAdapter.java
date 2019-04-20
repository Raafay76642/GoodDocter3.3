package com.example.aser.gooddocter001;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.DoctorsViewHolder> implements View.OnClickListener {
    private Context mCtx;
    private List<Doctor_Model> doctorModelList;

    public DoctorsAdapter(Context mCtx, List<Doctor_Model> doctorModelList) {
        this.mCtx = mCtx;
        this.doctorModelList = doctorModelList;
    }

    @NonNull
    @Override
    public DoctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recylerview_design_doc, parent, false);
        return new DoctorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsViewHolder holder, int position) {
        Doctor_Model doctorModel = doctorModelList.get(position);
        holder.textViewName.setText(doctorModel.name);
        holder.textViewDep.setText( doctorModel.department);
        holder.textViewAge.setText( doctorModel.age);
        holder.textViewFee.setText( doctorModel.fee);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx,DetailActivity.class);
                intent.putExtra("key",doctorModel.getId());
                mCtx.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return doctorModelList.size();
    }

    @Override
    public void onClick(View v ) {

    }


        public class DoctorsViewHolder extends RecyclerView.ViewHolder {
            TextView textViewName, textViewDep, textViewAge, textViewFee;
            CardView cardView;
            public DoctorsViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.text_view_name);
                textViewDep = itemView.findViewById(R.id.text_dep);
                textViewAge = itemView.findViewById(R.id.text_view_age);
                textViewFee = itemView.findViewById(R.id.text_fee);
                cardView=itemView.findViewById(R.id.cardview);
            }
        }
    }