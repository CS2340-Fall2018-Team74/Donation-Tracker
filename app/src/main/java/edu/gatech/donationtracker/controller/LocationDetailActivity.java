package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Model;

public class LocationDetailActivity extends AppCompatActivity {
    private TextView detailName;
    private TextView detailStreetAddress;
    private TextView detailCity;
    private TextView detailState;
    private TextView detailZip;
    private TextView detailType;
    private TextView detailPhone;
    private TextView detailWebsite;
    private Button  editThisLocation;
    private Button  viewInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail);
        setValues();
    }

    private void setValues() {
        Model model = Model.getInstance();
        detailName = (TextView) findViewById(R.id.location_detail_name);
        detailStreetAddress = (TextView) findViewById(R.id.location_detail_street_address);
        detailCity = (TextView) findViewById(R.id.location_detail_city);
        detailState = (TextView) findViewById(R.id.location_detail_state);
        detailZip = (TextView) findViewById(R.id.location_detail_zip);
        detailType = (TextView) findViewById(R.id.location_detail_type);
        detailPhone = (TextView) findViewById(R.id.location_detail_phone);
        detailWebsite = (TextView) findViewById(R.id.location_detail_website);
        editThisLocation = (Button) findViewById(R.id.location_detail_edit);
        viewInventory = (Button) findViewById(R.id.location_detail_inventory);
        detailName.setText(model.getCurrentLocation().getName());
        detailStreetAddress.setText(model.getCurrentLocation().getStreetAddress());
        detailCity.setText(model.getCurrentLocation().getCity());
        detailState.setText(model.getCurrentLocation().getState());
        detailZip.setText(model.getCurrentLocation().getZip());
        detailType.setText(model.getCurrentLocation().getType());
        detailPhone.setText(model.getCurrentLocation().getPhone());
        detailWebsite.setText(model.getCurrentLocation().getWebsite());

        viewInventory = (Button) findViewById(R.id.location_detail_inventory);
        viewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationDetailActivity.this, ItemListActivity.class);
                startActivity(intent);
            }
        });
    }
}
