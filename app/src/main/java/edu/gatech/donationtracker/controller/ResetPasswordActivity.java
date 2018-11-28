package edu.gatech.donationtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Model;
import edu.gatech.donationtracker.model.User;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Button button = findViewById(R.id.button_reset_pass);
        EditText emailField = findViewById(R.id.reset_pass_SU);
        FirebaseAuth fa = FirebaseAuth.getInstance();

        button.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            if (!email.contains("@") || !email.contains(".")) {
                Toast.makeText(ResetPasswordActivity.this, "Invalid email",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            fa.sendPasswordResetEmail(email);
            Toast.makeText(ResetPasswordActivity.this, "password reset email sent",
                    Toast.LENGTH_SHORT).show();
        });
    }
}
