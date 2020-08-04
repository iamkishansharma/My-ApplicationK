package com.heycode.myapplicationk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText name, email, password;
    Button signUpButton;
    TextView signin_txt;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        signUpButton = findViewById(R.id.signup_btn);
        signin_txt = findViewById(R.id.signin_txt);
        mFirebaseAuth = FirebaseAuth.getInstance();

        signin_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SigninActivity.class));
                finish();
            }
        });

    }

    public void createUser(View view) {
        String name1 = name.getText().toString();
        String email1 = email.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        if(TextUtils.isEmpty(email1)){
            email.setError("Required field");
        }if(TextUtils.isEmpty(email1) || !Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            email.setError("Required field");
        }
        if(TextUtils.isEmpty(password1)){
            password.setError("Required field");
        }
        if(password1.length()>=6){
            mFirebaseAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mFirebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "Verify Your Email..", Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(MainActivity.this, "ERROR !!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(MainActivity.this, "ERROR !!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
