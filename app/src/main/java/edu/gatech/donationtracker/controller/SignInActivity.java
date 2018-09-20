package edu.gatech.donationtracker.controller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import edu.gatech.donationtracker.R;

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

                if(email.equals("") | password.equals("")){
                    Toast.makeText(SignInActivity.this,"You need to input your email and password to login.",Toast.LENGTH_SHORT).show();
                } // else, verify the Email and Password with existed information.
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

