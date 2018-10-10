package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import edu.gatech.donationtracker.R;

public class WelcomeActivity extends AppCompatActivity {
    private String app_name = "Donation Tracker";
    private String sign_up = "Sign up";
    private String sign_in = "Sign in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button buttonSignIn = (Button)findViewById(R.id.button_signIn_Wel);
        Button buttonSignUp = (Button)findViewById(R.id.button_signUp_Wel);
        //ADD LISTENERS


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
                Intent intent1 = new Intent(WelcomeActivity.this, SignUpActivity.class);
                startActivity(intent1);
            }
        });
    }
}
