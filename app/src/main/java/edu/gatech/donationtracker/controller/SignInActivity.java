package edu.gatech.donationtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.User;


public class SignInActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);

        Button signin = (Button) findViewById(R.id.button_signIn);
        Button cancel = (Button) findViewById(R.id.button_cancel);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isFound = false;
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(SignInActivity.this, "You need to input your email and password to login.", Toast.LENGTH_SHORT).show();
                } else {
                    for (User e : SignUpActivity.accounts) {
                        if (e.getEmail().equals(email)) {
                            isFound = true;
                            if (e.getIsLocked()) {
                                Toast.makeText(SignInActivity.this, "Your account is locked", Toast.LENGTH_SHORT).show();
                                break;
                            } else if (!e.getPassword().equals(password)) {
                                e.counterIncrement();
                                if (e.getCounter() >= 3) {
                                    Toast.makeText(SignInActivity.this, "Your account is locked", Toast.LENGTH_SHORT).show();
                                    e.setIsLocked(true);
                                    break;
                                }
                                Toast.makeText(SignInActivity.this, "Wrong password, please enter again", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                            startActivityForResult(intent, 0);
                        }
                    }
                    if (!isFound) {
                        Toast.makeText(SignInActivity.this, "Email or password is invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
