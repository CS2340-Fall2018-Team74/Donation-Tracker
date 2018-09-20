package edu.gatech.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private String app_name = "Donation Tracker";
    private String sign_up = "Sign up";
    private String sign_in = "Sign in";
    private Button button_sign_up;
    private Button button_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        button_sign_in = findViewById(R.id.button_signIn);
        button_sign_up = findViewById(R.id.button_signUp);
        //ADD LISTENERS
        button_sign_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
