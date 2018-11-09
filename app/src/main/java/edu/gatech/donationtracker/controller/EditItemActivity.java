package edu.gatech.donationtracker.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Item;
import edu.gatech.donationtracker.model.Model;

public class EditItemActivity extends AppCompatActivity {

    private EditText categoryField;
    private EditText nameField;
    private EditText quantityField;
    private EditText urlField;
    private Button update_image;
    private Button ok;
    ProgressDialog progressDialog;
    UploadTask uploadTask;
    public static FirebaseStorage storage;
    StorageReference storageRef, imageRef;
    Uri uriImage;
    private static final int Selected = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        categoryField = findViewById(R.id.edit_item_category_SU);
        nameField = findViewById(R.id.edit_item_name_SU);
        quantityField = findViewById(R.id.edit_item_quantity_SU);
        urlField = findViewById(R.id.edit_item_uri);
        update_image = findViewById(R.id.edit_item_update);
        ok = findViewById(R.id.edit_item_ok);

        final Item currentItem = Model.getInstance().getCurrentItem();
        if (currentItem != null) {
            urlField.setText(currentItem.getUrl());
            categoryField.setText(currentItem.getCategory());
            nameField.setText(currentItem.getName());
            quantityField.setText(String.valueOf(currentItem.getQuantity()));
        }


        update_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseImage();
            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = categoryField.getText().toString();
                String name = nameField.getText().toString();
                String imageUrl = urlField.getText().toString();
                int quantity = Integer.parseInt(quantityField.getText().toString());
                //adding new item
                if (currentItem == null) {
                    String id = String.valueOf(++(Model.getInstance().getCurrentLocation().itemId));
                    Item newItem = new Item(imageUrl, name, id, category, quantity, Model.getInstance().getCurrentLocation());
                    Model.getInstance().getCurrentLocation().addData(newItem);
                    Model.getInstance().setCurrentItem(newItem);
                    Model.getInstance().pushNewItemToDatabase(newItem);
                    //edit current item
                } else {
                    currentItem.setUrl(imageUrl);
                    currentItem.setCategory(category);
                    currentItem.setName(name);
                    currentItem.setQuantity(quantity);
                    Model.getInstance().pushEditedItemToDatabase(currentItem);
                }
                Intent intent = new Intent(EditItemActivity.this, ItemDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Pops up choose image screen
     */
    public void ChooseImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Selected);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case Selected:
                if (resultCode == RESULT_OK) {
                    uriImage = imageReturnedIntent.getData();
                    uploadPhoto();
                }
        }
    }

    /**
     * Upload the chosen image to database
     */
    public void uploadPhoto() {

        String postItemImageID = Model.getInstance().getCurrentItem().getId();
        String postLocationID = Model.getInstance().getCurrentLocation().getKey();

        imageRef = storageRef.child("locations" + postLocationID
                + "/items" + postItemImageID + "/myphotos");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Uploading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        progressDialog.setCancelable(false);

        uploadTask = imageRef.putFile(uriImage);

        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                progressDialog.incrementProgressBy((int) progress);

            }
        });

        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();

                }

                // Continue with the task to get the download URL
                return imageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    urlField.setText(downloadUri.toString());
                    progressDialog.dismiss();

                } else {
                    // Handle failures
                    // ...
                }
            }
        });
  }
}
