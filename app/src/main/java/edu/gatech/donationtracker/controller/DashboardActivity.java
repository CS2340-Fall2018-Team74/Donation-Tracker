package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Model;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button buttonSettings = findViewById(R.id.button_settings_DB);
        Button location = findViewById(R.id.location);
        TextView accountType = (TextView) findViewById(R.id.dashboard_account_type);

        //display current user type
        accountType.setText(Model.getInstance().getCurrentUserTypeAsString());

        //Jump to Logout layout when button clicked.
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, SignOutActivity.class);
                startActivity(intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, LocationListActivity.class);
                startActivity(intent);
            }
        });
    }
}
