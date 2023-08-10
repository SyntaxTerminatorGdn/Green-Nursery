package com.example.syntaxternminators;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView goToSignUpTextView, forgotPasswordTextView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.emailid);
        passwordEditText = findViewById(R.id.passid);
        loginButton = findViewById(R.id.loginbtnid);
        goToSignUpTextView = findViewById(R.id.gotosignup);
        forgotPasswordTextView = findViewById(R.id.resetid);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        goToSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle password reset here
            }
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login successful
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();

                            // Check if the user is an admin (using a specific email and password)
                            if (email.equals("admin1@aptechgdn.net") && password.equals("admin1")) {
                                // Redirect to the admin page (assuming your admin activity is named "AdminActivity")
                                Intent adminIntent = new Intent(Login.this, AdminActivity.class);
                                startActivity(adminIntent);
                            } else {
                                // Redirect to the homepage (assuming your homepage activity is named "HomeActivity")
                                Intent homeIntent = new Intent(Login.this, HomeActivity.class);
                                startActivity(homeIntent);
                            }
                            finish(); // Close the login activity to prevent going back
                        } else {
                            // Login failed
                            Toast.makeText(Login.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
