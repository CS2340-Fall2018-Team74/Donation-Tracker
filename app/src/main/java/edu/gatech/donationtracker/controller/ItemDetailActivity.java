package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Model;

public class ItemDetailActivity extends AppCompatActivity {
    private ImageView detailImage;
    private TextView detailName;
    private TextView detailQuantity;
    private TextView detailCatetory;
    private Button delete;
    private Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        detailCatetory = findViewById(R.id.item_detail_category);
        detailName = findViewById(R.id.item_detail_name);
        detailQuantity = findViewById(R.id.item_detail_quantity);
        detailImage = findViewById(R.id.item_detail_image);
        delete = (Button) findViewById(R.id.item_detail_delete);
        edit = (Button) findViewById(R.id.item_detail_edit);


        detailCatetory.setText(Model.getInstance().getCurrentItem().getCategory());
        detailName.setText(Model.getInstance().getCurrentItem().getName());
        detailQuantity.setText(String.valueOf(Model.getInstance().getCurrentItem().getQuantity()));
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetailActivity.this, EditItemActivity.class);
                startActivity(intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().getCurrentLocation().removeData(Model.getInstance().getCurrentItem());
                Model.getInstance().deleteItemInDatabase(Model.getInstance().getCurrentItem());
                Intent intent = new Intent(ItemDetailActivity.this, ItemListActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
