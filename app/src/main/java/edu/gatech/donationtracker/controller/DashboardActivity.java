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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Model;


public class DashboardActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Set up location list spinner
        final Spinner searchLocationSpinner = findViewById(R.id.search_location_spinner);
        List<String> locationList = Model.getInstance().getLocationsAsString();
        locationList.add(0, "All Location");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locationList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchLocationSpinner.setAdapter(adapter);

        //Set up search
        ImageButton searchButton = findViewById(R.id.search_item_button);
        final EditText searchField = findViewById(R.id.search_item);
        final CheckBox categoryChecker = findViewById(R.id.search_item_category);
        final CheckBox nameChecker = findViewById(R.id.search_item_name);

        Button buttonSettings = findViewById(R.id.button_settings_DB);

        Button location = findViewById(R.id.location);

        //display current user type
        TextView accountType = (TextView) findViewById(R.id.dashboard_account_type);
        accountType.setText(Model.getInstance().getCurrentUserTypeAsString());

        //jump to item list layout
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!categoryChecker.isChecked() && !nameChecker.isChecked() ) {
                    Toast.makeText(DashboardActivity.this, "Please select the type to search!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (searchField.getText().length() == 0) {
                    Toast.makeText(DashboardActivity.this, "Please enter keyword!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (searchLocationSpinner.getSelectedItemPosition() == 0) {
                    //search all locations
                    if (categoryChecker.isChecked() && nameChecker.isChecked()) {
                        //both checked
                        Model.getInstance().filterBoth(Model.getInstance().getAllItems(), searchField.getText().toString());
                    } else if (categoryChecker.isChecked()) {
                        //only category is checked
                        Model.getInstance().filterCategory(Model.getInstance().getAllItems(), searchField.getText().toString());
                    } else {
                        //only name is checked
                        Model.getInstance().filterName(Model.getInstance().getAllItems(), searchField.getText().toString());
                    }
                    if (Model.getInstance().getFilteredItems().size() == 0) {
                        Toast.makeText(DashboardActivity.this, "No Item Found!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Model.getInstance().setCurrentLocation(null);
                    Intent intent = new Intent(DashboardActivity.this, ItemListActivity.class);
                    startActivity(intent);
                } else {
                    //search a location
                    if (categoryChecker.isChecked() && nameChecker.isChecked()) {
                        //both checked
                        Model.getInstance().filterBoth(Model.getInstance().getLocations().
                                get(searchLocationSpinner.getSelectedItemPosition() - 1).getInventory(), searchField.getText().toString());
                    } else if (categoryChecker.isChecked()) {
                        //only category is checked
                        Model.getInstance().filterCategory(Model.getInstance().getLocations().
                                get(searchLocationSpinner.getSelectedItemPosition() - 1).getInventory(), searchField.getText().toString());
                    } else {
                        //only name is checked
                        Model.getInstance().filterName(Model.getInstance().getLocations().
                                get(searchLocationSpinner.getSelectedItemPosition() - 1).getInventory(), searchField.getText().toString());
                    }
                    if (Model.getInstance().getFilteredItems().size() == 0) {
                        Toast.makeText(DashboardActivity.this, "No Item Found!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Model.getInstance().setCurrentLocation(null);
                    Intent intent = new Intent(DashboardActivity.this, ItemListActivity.class);
                    startActivity(intent);
                }
            }
        });

        //Jump to Logout layout when setting clicked.
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, SignOutActivity.class);
                startActivity(intent);
            }
        });

        //Jump to location list layout when location is clicked
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, LocationListActivity.class);
                startActivity(intent);
            }
        });
    }
}
