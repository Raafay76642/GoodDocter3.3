package com.example.aser.gooddocter001;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button signupbtn;
    private EditText editTextEmail,displayname,s_age;
    private Spinner gender,country;
    private EditText editTextPass;
    private EditText editTextRePass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mref;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signupbtn = findViewById(R.id.signupbtn);
        editTextEmail = findViewById(R.id.email);
        editTextPass = findViewById(R.id.password);
        editTextRePass=findViewById(R.id.reTypePassword);
        displayname=findViewById(R.id.ename);
        s_age=findViewById(R.id.s_age);
        country=findViewById(R.id.country);
        gender=findViewById(R.id.gender);
        firebaseAuth = FirebaseAuth.getInstance();
        mdatabase=FirebaseDatabase.getInstance();
        mref=mdatabase.getReference().child("Users");
        progressDialog = new ProgressDialog(this);
    }
    public void opensignin(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void opensignin()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void registerUser(View  view)
    {
        String email = editTextEmail.getText().toString().trim();
        String pass = editTextPass.getText().toString();
        String repass = editTextRePass.getText().toString();
        String Name = displayname.getText().toString();
        String Gender = gender.getSelectedItem().toString();
        String Country = country.getSelectedItem().toString();
        String Age=s_age.getText().toString();
        if(TextUtils.isEmpty(Name))
        {
            //email is empty
            Toast.makeText(this,"Name Can't be left Blank",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Gender))
        {
            //email is empty
            Toast.makeText(this,"Please choose your gender",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"Email Can't be left Blank",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            //email is empty
            Toast.makeText(this,"Enter the Password",Toast.LENGTH_LONG).show();
            return;
        }
        if (!pass.equals(repass))
        {
            Toast.makeText(this,"Password Does Not Match",Toast.LENGTH_LONG).show();
            return;
        }
        //both the edit text are not empty
        progressDialog.setMessage("Registring User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            id=FirebaseAuth.getInstance().getCurrentUser().getUid();

                            //display some message here
                            ProfileModel profileModel =new ProfileModel(Name,Gender,Country,Age,id,email);
                            mref.child(id).setValue(profileModel);
                            Toast.makeText(SignUp.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            finish();
                            opensignin();


                        }else{
                            //display some message here
                            Toast.makeText(SignUp.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();



                    }
                });


    }

    @Override
    public void onClick(View view){


    }

}
