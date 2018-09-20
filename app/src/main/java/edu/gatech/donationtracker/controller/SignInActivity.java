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
import edu.gatech.donationtracker.model.UserAccount;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        final EditText inputEmail = (EditText) findViewById(R.id.email) ;
        final EditText inputPassword = (EditText) findViewById(R.id.password) ;

        Button signin = (Button) findViewById(R.id.button_signIn);
        Button cancel =(Button) findViewById(R.id.button_cancel);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                ArrayList<UserAccount> accounts = new ArrayList<>();
                accounts.add(new UserAccount("admin","pass","email"));
                if(email.equals("") || password.equals("")){
                    Toast.makeText(SignInActivity.this,"You need to input your email and password to login.",Toast.LENGTH_SHORT).show();
                } else {
                    for (UserAccount e : accounts) {
                        if (e.getEmail().equals(email) && e.getPassword().equals(password)) {
                            Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                            startActivityForResult(intent, 0);
                        }
                    }
                    Toast.makeText(SignInActivity.this,"Email or password is in valid",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(0);
            }
        });
    }
}

