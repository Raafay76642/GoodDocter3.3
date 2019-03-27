package com.example.aser.gooddocter001;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText eEid ,ePassword ;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eEid = findViewById(R.id.eeid);
        ePassword = findViewById(R.id.epassword);
        bLogin= findViewById(R.id.blogin);


    }

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
