package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Model;

public class WelcomeActivity extends AppCompatActivity {

    //public static FirebaseFirestore db;
    private String app_name = "Donation Tracker";
    private String sign_up = "Sign up";
    private String sign_in = "Sign in";
//    private ArrayList<Location> mLocations = new ArrayList<>();
//    private DocumentSnapshot mLastQueriedDocument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Model.getInstance();
        //FirebaseFirestore.getInstance();
        Button buttonSignIn = findViewById(R.id.button_signIn_Wel);
        Button buttonSignUp = findViewById(R.id.button_signUp_Wel);
        //ADD LISTENERS
        //CollectionReference collectionReference = db.collection("user");


        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, LocationListActivity.class);
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
