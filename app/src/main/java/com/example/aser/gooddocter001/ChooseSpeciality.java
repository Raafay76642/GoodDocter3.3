package com.example.aser.gooddocter001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseSpeciality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_speciality);
    }
    public void openDocterList(View view)
    {
        Intent intent = new Intent(this, DoctersList.class);
        startActivity(intent);
    }
}
