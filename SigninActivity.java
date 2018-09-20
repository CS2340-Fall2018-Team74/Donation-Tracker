package edu.gatech.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        final EditText inputEmail = (EditText) findViewById(R.id.email) ;
        final EditText inputPassword = (EditText) findViewById(R.id.password) ;

        Button signin = (Button) findViewById(R.id.sign_in_button);
        Button cancel =(Button) findViewById(R.id.cancel_button);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if(email.equals("") | password.equals("")){
                    Toast.makeText(SigninActivity.this,"You need to input your email and password to login.",Toast.LENGTH_SHORT).show();
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
