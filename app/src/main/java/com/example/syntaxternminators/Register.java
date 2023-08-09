package com.example.syntaxternminators;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText fullNameEditText, emailEditText, addressEditText, ageEditText, passwordEditText;
    private RadioButton maleRadioButton, femaleRadioButton;
    private Button registerButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        fullNameEditText = findViewById(R.id.nameid);
        emailEditText = findViewById(R.id.emailid);
        addressEditText = findViewById(R.id.addressid);
        ageEditText = findViewById(R.id.ageid);
        passwordEditText = findViewById(R.id.passid);
        maleRadioButton = findViewById(R.id.maleid);
        femaleRadioButton = findViewById(R.id.femaleid);
        registerButton = findViewById(R.id.loginbtnid);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration successful
                            Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            // You can save additional user information to Firestore here if needed.
                        } else {
                            // Registration failed
                            Toast.makeText(Register.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

