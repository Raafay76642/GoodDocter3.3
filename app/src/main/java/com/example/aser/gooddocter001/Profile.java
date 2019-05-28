
package com.example.aser.gooddocter001;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import id.zelory.compressor.Compressor;


public class Profile extends AppCompatActivity {
    EditText name,age,email;
    Spinner country,gender;
    private FirebaseAuth firebaseAuth;
    private ImageView ProfileImage;
    private ProgressDialog mProgressBarsaving;
    private ProgressDialog mProgressBarreteriving;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;
    private Uri getmImageUriwithoutCompress;

    DatabaseReference databaseprofile,databasegetdata;
    private Uri mImageUri;
    final static int Gallery_Pick = 1;
    TextView test;
    String ProfilePic;
    String id;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseprofile= FirebaseDatabase.getInstance().getReference("Users");
        databasegetdata= FirebaseDatabase.getInstance().getReference("Users");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        save=findViewById(R.id.save);
        country = findViewById(R.id.country);
        ProfileImage=findViewById(R.id.profile_pic);
        age = findViewById(R.id.age);
        email=findViewById(R.id.pemail);
        firebaseAuth = FirebaseAuth.getInstance();
        ProfileImage = (ImageView) findViewById(R.id.profile_pic);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mProgressBarreteriving = new ProgressDialog(Profile.this);
        mProgressBarsaving = new ProgressDialog(this);

        uneditable();
        getData();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            uploadFile();
        }
    });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        } else {
            finish();
            openlogin();
        }
    }


    public void open_gallery(View view)
        {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, Gallery_Pick);
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Pick && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Glide.with(this).load(mImageUri).into(ProfileImage);
        }
    }


    private void uploadFile() {
        mProgressBarsaving.setMessage("Saving. . .!");
        mProgressBarsaving.show();
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(id);

            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Toast.makeText(Profile.this, "Upload successful", Toast.LENGTH_LONG).show();
                        String Name = name.getText().toString();
                        String Gender = gender.getSelectedItem().toString();
                        String Country = country.getSelectedItem().toString();
                        String Age = age.getText().toString();
                        String Email=FirebaseAuth.getInstance().getCurrentUser().getEmail();
                        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                          fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                 ProfilePic=uri.toString();
                                databaseprofile.child(id).child("name").setValue(Name);
                                databaseprofile.child(id).child("profilePic").setValue(ProfilePic);
                                databaseprofile.child(id).child("country").setValue(Country);                            }
                        });

                        mProgressBarsaving.cancel();
                        final Toast toast = Toast.makeText(Profile.this, "Data is Saved", Toast.LENGTH_LONG);
                        toast.show();
                        getData();
                        uneditable();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Profile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }


    public void openlogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




    
    public void getData() {
        mProgressBarreteriving.setMessage("Please wait. . . !");
        mProgressBarreteriving.show();
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        databasegetdata.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProfileModel profileModel=dataSnapshot.getValue(ProfileModel.class);
                age.setText(profileModel.getAge());
                String Name = dataSnapshot.child("name").getValue(String.class);
                name.setText(profileModel.getName());
                email.setText(profileModel.getEmail());
                String x=profileModel.getCountry();
                switch (x){
                    case "Pakistan":
                    {
                        country.setSelection(0);
                        break;
                    }
                    case "India":
                    {
                        country.setSelection(1);
                        break;
                    }case "UAE":
                    {
                        country.setSelection(2);
                        break;
                    }
                }
                String y=profileModel.getGender();
                switch (y){
                    case "Male":
                    {
                        gender.setSelection(0);
                        break;
                    }
                    case "Female":
                    {
                        gender.setSelection(1);
                        break;
                    }case "Other":
                    {
                        gender.setSelection(2);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                age.setText("Eroor");
            }
        });
        databasegetdata.child(id).addValueEventListener(new ValueEventListener() {
            @Override


            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String value = dataSnapshot.child("profilePic").getValue(String.class);
                    Glide.with(Profile.this).load(value).placeholder(R.mipmap.ic_launcher).into(ProfileImage);
                }
                else {
                    Toast.makeText(Profile.this, ">>>", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                name.setText("Error");
            }

        });
mProgressBarreteriving.cancel();
    }
    public void uneditable(View view)
    {
        name.setEnabled(false);
        age.setEnabled(false);
    }
    public void uneditable()
    {

        name.setEnabled(false);
        gender.setEnabled(false);
        country.setEnabled(false);
        age.setEnabled(false);
        email.setEnabled(false);
        ProfileImage.setClickable(false);
    }

    public void editable(View view) {
        name.setEnabled(true);
        country.setEnabled(true);
        ProfileImage.setClickable(true);

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,homepage.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
