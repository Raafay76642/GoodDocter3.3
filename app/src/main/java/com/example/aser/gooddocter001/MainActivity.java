package com.example.aser.gooddocter001;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //Test run
    public void opensignup(View view)
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public void openHomePage(View view)
    {
        Intent intent = new Intent(this, homepage.class);
        startActivity(intent);
    }
}
