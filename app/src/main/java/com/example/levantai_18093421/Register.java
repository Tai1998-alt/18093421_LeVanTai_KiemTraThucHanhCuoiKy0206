package com.example.levantai_18093421;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{

    EditText editName, editEmail, editPasswordNew, editPassword;
    Button btnRegister;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editPasswordNew = findViewById(R.id.editPasswordNew);
        btnRegister = findViewById(R.id.btnRegister);
        auth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                RegisterUser();
                break;
        }
    }
    public void RegisterUser(){
        String email = String.valueOf(editEmail.getText());
        String password = String.valueOf(editPassword.getText());
        String passwordNew = String.valueOf(editPasswordNew.getText());
        String name = String.valueOf(editName.getText());
        if(email.isEmpty()){
            editEmail.setError("Not Empty!");
            return;
        }
        if(name.isEmpty()){
            editName.setError("Not Empty!");
            return;
        }
        if(password.isEmpty()){
            editPassword.setError("Not Empty!");
            return;
        }
        if(passwordNew.isEmpty()){
            editPasswordNew.setError("Not Empty!");
            return;
        }
        if(password.equalsIgnoreCase(passwordNew)){
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(name,email);
                                FirebaseDatabase.getInstance().getReference("User")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            startActivity(new Intent(Register.this, LoginMain.class));
                                        } else {
                                            new AlertDialog.Builder(Register.this)
                                                    .setTitle("Thong Bao")
                                                    .setMessage("Luu user khong thanh cong")
                                                    .setPositiveButton("YES",null).show();
                                        }
                                    }
                                });

                            } else {
                                new AlertDialog.Builder(Register.this)
                                        .setTitle("Thong Bao")
                                        .setMessage("sai")
                                        .setPositiveButton("YES",null).show();
                            }
                        }
                    });
        }
    }
}