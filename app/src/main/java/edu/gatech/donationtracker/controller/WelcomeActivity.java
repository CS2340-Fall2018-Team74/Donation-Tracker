package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Model;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //get data from database
        Model.getInstance();
        Button buttonSignIn = findViewById(R.id.button_signIn_Wel);
        Button buttonSignUp = findViewById(R.id.button_signUp_Wel);
        Button buttonVisitor = findViewById(R.id.button_visitor_wel);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        buttonVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
