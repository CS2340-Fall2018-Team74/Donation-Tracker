package edu.gatech.donationtracker.controller;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Model;
import edu.gatech.donationtracker.model.User;


public class SignInActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        inputEmail = findViewById(R.id.email_SI);
        inputPassword = findViewById(R.id.password_SI);

        FirebaseAuth fa = FirebaseAuth.getInstance();

        Button buttonSignIn = findViewById(R.id.button_signIn_SI);
        Button buttonCancel = findViewById(R.id.button_cancel_SI);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                boolean isFound = false;
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(SignInActivity.this, "You need to input your email and password to login.", Toast.LENGTH_SHORT).show();
                } else {
                    for (User e : Model.getInstance().getAccounts()) {
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
                            Model.getInstance().setCurrentUser(e);
                            Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                            startActivityForResult(intent, 0);
                        }
                    }
                    if (!isFound) {
                        Toast.makeText(SignInActivity.this, "Email or password is invalid", Toast.LENGTH_SHORT).show();
                    }
                }
                */
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(SignInActivity.this, "You need to input your email and password to login.", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(SignInActivity.this, "password length should >= 6", Toast.LENGTH_SHORT).show();
                } else {
                    for (User e : Model.getInstance().getAccounts()) {
                        if (e.getEmail().equals(email)) {
                            if (e.getIsLocked()) {
                                Toast.makeText(SignInActivity.this, "Your account is locked", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            fa.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Model.getInstance().setCurrentUser(e);
                                        e.setPassword(password);
                                        Model.getInstance().pushEditAccountToDatabase(e);
                                        Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                                        startActivityForResult(intent, 0);
                                    } else {
                                        e.counterIncrement();
                                        if (e.getCounter() >= 3) {
                                            Toast.makeText(SignInActivity.this, "Your account is locked", Toast.LENGTH_SHORT).show();
                                            e.setIsLocked(true);
                                            Model.getInstance().pushEditAccountToDatabase(e);
                                        } else {
                                            Toast.makeText(SignInActivity.this, "Wrong password, please enter again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                        }
                    }

                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
