package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Location;
import edu.gatech.donationtracker.model.Model;
import edu.gatech.donationtracker.model.User;

public class DashboardActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        final Spinner searchLocationSpinner = findViewById(R.id.search_location_spinner);

        ArrayList<String> locationList = Model.getInstance().getLocationsAsString();
        locationList.add(0, "All Location");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locationList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchLocationSpinner.setAdapter(adapter);
        ImageButton searchButton = findViewById(R.id.search_item_button);
        EditText searchField = findViewById(R.id.search_item);
        final CheckBox categoryChecker = findViewById(R.id.search_item_category);
        final CheckBox nameChecker = findViewById(R.id.search_item_name);
        Button buttonSettings = findViewById(R.id.button_settings_DB);
        Button location = findViewById(R.id.location);
        TextView accountType = (TextView) findViewById(R.id.dashboard_account_type);

        //display current user type
        accountType.setText(Model.getInstance().getCurrentUserTypeAsString());
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!categoryChecker.isChecked() && !nameChecker.isChecked() ) {
                    Toast.makeText(DashboardActivity.this, "Please select the type to search", Toast.LENGTH_SHORT).show();
                }
                //search all locations
                if (searchLocationSpinner.getSelectedItemPosition() == 0) {
                    if (categoryChecker.isChecked() && nameChecker.isChecked()) {
                        Model.getInstance().getAllItems();
                    }
                }

            }
        });

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
