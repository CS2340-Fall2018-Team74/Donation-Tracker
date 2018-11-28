package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Admin;
import edu.gatech.donationtracker.model.LocationEmployee;
import edu.gatech.donationtracker.model.Manager;
import edu.gatech.donationtracker.model.Model;
import edu.gatech.donationtracker.model.User;

public class SignUpActivity extends AppCompatActivity  {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private Spinner accountTypeSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameField = findViewById(R.id.name_SU);
        emailField = findViewById(R.id.email_SU);
        passwordField = findViewById(R.id.password_SU);
        confirmPasswordField = findViewById(R.id.confirm_password_SU);
        accountTypeSpinner = findViewById(R.id.spinner_SU);

        FirebaseAuth fa = FirebaseAuth.getInstance();

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, User.legalType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        Button buttonSignUp = findViewById(R.id.button_reset_pass);
        Button buttonCancel = findViewById(R.id.button_cancel_SU);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean passwordCheck = false;
                boolean emailNotRepeated = true;
                int type = accountTypeSpinner.getSelectedItemPosition();
                String name = nameField.getText().toString();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String confirmPassword =confirmPasswordField.getText().toString();
                if (email.equals("") || password.equals("")|| name.equals("")|| confirmPassword.equals("")) {
                    Toast.makeText(SignUpActivity.this, "You need to enter your basic account information.",
                            Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "The two passwords you entered don't match.",
                            Toast.LENGTH_SHORT).show();
                } else if (email.contains("@") && email.contains(".")) {passwordCheck = true;}

                for (User u : Model.getInstance().getAccounts()){
                    if (u.getEmail().equals(email)){
                        emailNotRepeated = false;
                    }
                }
                if(!emailNotRepeated && passwordCheck) {
                    Toast.makeText(SignUpActivity.this, "The e-mail has already been signed up",
                            Toast.LENGTH_SHORT).show();
                }
                if(passwordCheck && emailNotRepeated) {
                    User newAccount;
                    switch (type) {
                        case 1:
                            //new LE would have their default location as locations[0]
                            newAccount = new LocationEmployee(email, name, password,
                                    Model.getInstance().getLocations().get(0));
                            break;
                        case 2:
                            newAccount = new Manager(email, name, password);
                            break;
                        case 3:
                            newAccount = new Admin(email, name, password);
                            break;
                        default:
                            newAccount = new User(email, name, password);
                    }
                    Model.getInstance().addAccount(newAccount);
                    Model.getInstance().pushAccountToDatabase(newAccount);
                    Model.getInstance().setCurrentUser(newAccount);
                    fa.createUserWithEmailAndPassword(email, password);
                    Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
