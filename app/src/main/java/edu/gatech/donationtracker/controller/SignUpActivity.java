package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.User;

public class SignUpActivity extends AppCompatActivity  {

    private TextView idField;
    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private Spinner accountTypeSpinner;
    public static ArrayList<User> accounts = new ArrayList<>();
    private String _accountType = "NA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        idField = findViewById(R.id.id_field);
        nameField = (EditText)findViewById(R.id.name_SU);
        emailField = (EditText)findViewById(R.id.email_SU);
        passwordField = (EditText)findViewById(R.id.password_SU);
        confirmPasswordField =(EditText) findViewById(R.id.confirm_password_SU);
        accountTypeSpinner = findViewById(R.id.spinner_SU);

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.legalType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        Button buttonSignUp = findViewById(R.id.button_signUp_SU);
        Button buttonCancel = findViewById(R.id.button_cancel_SU);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean passwordCheck = false;
                boolean emailNotRepeated = true;
                String name = nameField.getText().toString();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String confirmPassword =confirmPasswordField.getText().toString();
                if (email.equals("") || password.equals("")|| name.equals("")|| confirmPassword.equals("")) {
                    Toast.makeText(SignUpActivity.this, "You need to enter your basic account information.", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "The two passwords you entered don't match.", Toast.LENGTH_SHORT).show();
                } else {passwordCheck = true;}

                for (User u : accounts){
                    if (u.getEmail().equals(email)){
                        emailNotRepeated = false;
                    }
                }
                if(!emailNotRepeated && passwordCheck) {
                    Toast.makeText(SignUpActivity.this, "The e-mail has already been signed up", Toast.LENGTH_SHORT).show();
                }
                if(passwordCheck && emailNotRepeated) {
                    accounts.add(new User(name, password, email));
                    Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
