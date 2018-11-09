package edu.gatech.donationtracker.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Model;

public class ItemDetailActivity extends AppCompatActivity {

    private ImageView detailImage;
    private TextView detailName;
    private TextView detailQuantity;
    private TextView detailCategory;
    private Button delete;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        detailCategory = findViewById(R.id.item_detail_category);
        detailName = findViewById(R.id.item_detail_name);
        detailQuantity = findViewById(R.id.item_detail_quantity);
        detailImage = findViewById(R.id.item_detail_image);
        delete = findViewById(R.id.item_detail_delete);
        edit = findViewById(R.id.item_detail_edit);

        if (Model.getInstance().getCurrentUserType() < 1) {
            edit.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);
        }

        Picasso.get().load(Model.getInstance().getCurrentItem().getUri()).into(detailImage);
        Log.d("image", Model.getInstance().getCurrentItem().getUri());

        detailCategory.setText(Model.getInstance().getCurrentItem().getCategory());
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
                if (Model.getInstance().getCurrentLocation() == null) {
                    //delete item when searching
                    Model.getInstance().getFilteredItems().remove(Model.getInstance().getCurrentItem());
                }
                Model.getInstance().getCurrentItem().getLocation().removeData(Model.getInstance().getCurrentItem());
                Model.getInstance().deleteItemInDatabase(Model.getInstance().getCurrentItem());
                Intent intent = new Intent(ItemDetailActivity.this, ItemListActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
