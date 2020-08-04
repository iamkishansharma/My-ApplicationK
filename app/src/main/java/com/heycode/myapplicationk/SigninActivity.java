package com.heycode.myapplicationk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {
    EditText email, password;
    Button signInButton;
    TextView signup_txt, forget_txt;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email = findViewById(R.id.signin_email);
        password = findViewById(R.id.signin_password);
        signInButton = findViewById(R.id.signin_btn);
        signup_txt = findViewById(R.id.signup_txt);
        forget_txt = findViewById(R.id.forget_txt);
        mFirebaseAuth = FirebaseAuth.getInstance();

        //Login Screen skip if already logged in
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if(user!=null){
            finish();
            startActivity(new Intent(SigninActivity.this, HomeScreenActivity.class));
        }

        //Goto registration screen if not have ACCOUNT
        signup_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity.this, MainActivity.class));
                finish();
            }
        });

        forget_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.toString().length() !=0){
                    mFirebaseAuth.sendPasswordResetEmail(email.toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SigninActivity.this, "Password Reset Email Sent !", Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(SigninActivity.this, "Task Failed !", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }else {
                    Toast.makeText(SigninActivity.this, "Provide your Email..", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void signInUser(View view) {
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

        //TODO:: Login here
        if(password1.length()>=6){
            mFirebaseAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        if(mFirebaseAuth.getCurrentUser().isEmailVerified()){
                            startActivity(new Intent(SigninActivity.this, HomeScreenActivity.class));
                            finish();
                        }else {
                            Toast.makeText(SigninActivity.this, "Please Verify your self", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }else {
            Toast.makeText(SigninActivity.this, "Error !!!", Toast.LENGTH_SHORT).show();
        }
    }
}
