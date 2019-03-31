package com.example.aser.gooddocter001;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Booked_apntments extends Fragment implements View.OnClickListener {

    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booked_apntments, container, false);
         btn=(Button)view.findViewById(R.id.btt1);
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 open_calling();
             }
         });
        return view;
    }
    public void open_calling(){
        Intent intent=new Intent(getActivity(),Calling.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }
}
