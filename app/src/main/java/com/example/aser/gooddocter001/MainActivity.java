package com.example.aser.gooddocter001;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText lEi ,lPassword ;
    Button bLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lEi = findViewById(R.id.lmail);
        lPassword = findViewById(R.id.lpassword);
        bLogin= findViewById(R.id.blogin);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        if(firebaseAuth.getCurrentUser() != null)
        {
            finish();
            openHomePage();
        }


    }

    public void opensignup(View view)
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public void openHomePage()
    {
        Intent intent = new Intent(this, homepage.class);
        startActivity(intent);
    }
    public void userLogin(View view)
    {
        String Email = lEi.getText().toString().trim();
        String Pass = lPassword.getText().toString().trim();
        if(TextUtils.isEmpty(Email))
        {
            //email is empty
            Toast.makeText(this,"Email Can't be left Blank",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Pass))
        {
            //email is empty
            Toast.makeText(this,"Enter the Password",Toast.LENGTH_LONG).show();
            return;
        }
        //both the edit text are not empty
        progressDialog.setMessage("loging in ...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    finish();
                    openHomePage();
                } else {

                        final String TAG = SignUp.class.getName();
                        Log.w(TAG, "signInWithEmail:failed", task.getException());
                        Toast.makeText(MainActivity.this, "User Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }



            }
        });

    }
    public void resetPassword(View view)
    {
        String Email = lEi.getText().toString().trim();
        if(TextUtils.isEmpty(Email))
        {
            //email is empty
            Toast.makeText(this,"Please Enter the Email To Reset Your Password",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Reseting Password ...");
        progressDialog.show();
        firebaseAuth.sendPasswordResetEmail(Email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this,"Password Reset.Check Your Mail",Toast.LENGTH_LONG).show();
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this,"Ooops Something Wrong.Try again",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
