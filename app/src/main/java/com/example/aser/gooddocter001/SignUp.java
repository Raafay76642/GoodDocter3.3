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
import android.widget.ImageView;
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
    private DatabaseReference databaseReference;

    String id;
    final static int Gallery_Pick = 1;

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
       final String email = editTextEmail.getText().toString().trim();
       final String pass = editTextPass.getText().toString();
       final String repass = editTextRePass.getText().toString();
       final String Name = displayname.getText().toString();
       final String Gender = gender.getSelectedItem().toString();
       final String Country = country.getSelectedItem().toString();
       final String Age=s_age.getText().toString();
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
                            databaseReference=FirebaseDatabase.getInstance().getReference();
//                            display some message here
        ProfileModel profileModel =new ProfileModel(Name,Gender,Country,Age,id,email);
                            databaseReference.child("Users").child(id).setValue(profileModel);
                            databaseReference.child("Users").child(id).child("role").setValue("user");
                            Toast.makeText(SignUp.this,"Successfully registered",Toast.LENGTH_LONG).show();


                        }
                        else{
                            //display some message here
                            Toast.makeText(SignUp.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                        finish();
                        opensignin();



                    }
                });


    }

    @Override
    public void onClick(View view){


    }

}
