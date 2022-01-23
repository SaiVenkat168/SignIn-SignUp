package com.example.loginactivity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginactivity.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Regestration");
        progressDialog.setMessage("Creating your Account");
        database=FirebaseDatabase.getInstance();

        binding.createaccountbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword(binding.registrationemail.getText().toString(),binding.registrationpassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {

                                    String id=task.getResult().getUser().getUid();
                                    Users users=new Users(binding.registrationusername.getText().toString(),binding.registrationemail.getText().toString(),id,binding.registrationpassword.getText().toString());
                                    database.getReference().child("Users").child(id).setValue(users);
                                    progressDialog.dismiss();
                                }
                                else
                                    Toast.makeText(SignUpActivity.this, "Failed to create your account", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });

        if(auth.getCurrentUser()==null)
            startActivity(new Intent(SignUpActivity.this,MainActivity.class));



    }
}