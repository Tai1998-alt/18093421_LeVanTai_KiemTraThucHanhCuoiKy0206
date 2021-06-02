package com.example.levantai_18093421;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginMain extends AppCompatActivity {

    EditText editEmail, editPassWord;
    Button btnSignIn;
    FirebaseAuth auth;
    TextView txtExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        editEmail = findViewById(R.id.editEmail);
        editPassWord = findViewById(R.id.editPassWord);
        btnSignIn = findViewById(R.id.btnSignIn);
        auth = FirebaseAuth.getInstance();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

    }
    private  void exit_click(){
        finish();
    }
    private void SignIn()
    {
        String email = String.valueOf(editEmail.getText());
        String password = String.valueOf(editPassWord.getText());
        if(email.isEmpty())
        {
            editEmail.setError("Not Empty");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editPassWord.setError("Not Empty");
            editPassWord.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editPassWord.setError("Need than character six");
            editPassWord.requestFocus();
            return;
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                                startActivity(new Intent(LoginMain.this, Manager.class));
                        } else {
                            new AlertDialog.Builder(LoginMain.this)
                                    .setTitle("Confirm")
                                    .setMessage("Failed")
                                    .setPositiveButton("YES",null).show();

                        }
                        }

                });
    }
}