package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.LocationEmployee;
import edu.gatech.donationtracker.model.Model;

public class SignOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);

        TextView nameField = (TextView) findViewById(R.id.name_text_view);
        TextView locationField = (TextView) findViewById(R.id.location_text_view);

        nameField.setText("Username: " + Model.getInstance().getCurrentUser().getName());
        //if user type if LE and has location, we show their location, else we show "No location assigned"
        if (Model.getInstance().getCurrentUserType() == 1 && ((LocationEmployee) Model.getInstance().getCurrentUser()).HasLocation()) {
            locationField.setText("Locations: " + ((LocationEmployee) Model.getInstance().getCurrentUser()).getlocation().toString());
        } else {
            locationField.setText("Locations: No location assigned");
        }

        //Jump to sign in layout when button clicked.
        final Button buttonSignOut = findViewById(R.id.button_signOut_SO);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignOutActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
