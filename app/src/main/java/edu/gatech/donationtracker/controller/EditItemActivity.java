package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Item;
import edu.gatech.donationtracker.model.Model;

public class EditItemActivity extends AppCompatActivity {

    private EditText categoryField;
    private EditText nameField;
    private EditText quantityField;
    private Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        categoryField = (EditText) findViewById(R.id.edit_item_category_SU);
        nameField = (EditText) findViewById(R.id.edit_item_name_SU);
        quantityField = (EditText) findViewById(R.id.edit_item_quantity_SU);
        ok = (Button) findViewById(R.id.edit_item_ok);

        final Item currentItem = Model.getInstance().getCurrentItem();
        if (currentItem != null) {
            categoryField.setText(currentItem.getCategory());
            nameField.setText(currentItem.getName());
            quantityField.setText(String.valueOf(currentItem.getQuantity()));
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = categoryField.getText().toString();
                String name = nameField.getText().toString();
                int quantity = Integer.parseInt(quantityField.getText().toString());
                //adding new item
                if (currentItem == null) {
                    String id = String.valueOf(++(Model.getInstance().getCurrentLocation().itemId));
                    Item newItem = new Item(name, id, category, quantity);
                    Model.getInstance().getCurrentLocation().addData(newItem);
                    Model.getInstance().setCurrentItem(newItem);
                    Model.getInstance().pushNewItemToDatabase(newItem);
                //edit current item
                } else {
                    currentItem.setCategory(category);
                    currentItem.setName(name);
                    currentItem.setQuantity(quantity);
                    Model.getInstance().pushEditedItemToDatabase(currentItem);
                }
                Intent intent = new Intent(EditItemActivity.this, ItemListActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }
}
