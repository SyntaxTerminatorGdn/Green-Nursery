package com.example.syntaxternminators;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText fullNameEditText, emailEditText, addressEditText, ageEditText, passwordEditText;
    private RadioGroup genderRadioGroup;
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
        genderRadioGroup = findViewById(R.id.genderid);

        registerButton = findViewById(R.id.loginbtnid);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration successful
                            Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();

                            // Get the selected gender from the radio buttons
                            String gender = "";
                            int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                            if (selectedRadioButtonId == R.id.maleid) {
                                gender = "Male";
                            } else if (selectedRadioButtonId == R.id.femaleid) {
                                gender = "Female";
                            }

                            // Save additional user info in Realtime Database
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
                            String userId = currentUser.getUid();
                            User user = new User(userId, fullNameEditText.getText().toString(), addressEditText.getText().toString(), ageEditText.getText().toString(), gender);
                            usersRef.child(userId).setValue(user);
                        } else {
                            // Registration failed
                            Toast.makeText(Register.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // ... rest of your code

}





