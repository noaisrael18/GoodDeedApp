package com.example.androidapplesson7;


import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class AddVolunteeringActivity extends AppCompatActivity {


    private static final String TAG = "AddVolunteeringActivity";
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private ImageButton btnUploadImage;
    private Uri currentImageUri;
    private ActivityResultLauncher<Uri> takePictureLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_volunteering);


        btnUploadImage = findViewById(R.id.btnUploadImage);
        EditText editTextLocation = findViewById(R.id.editTextLocation);
        EditText editTextDate = findViewById(R.id.editTextDate);
        EditText editTextActivity = findViewById(R.id.editTextActivity);
        EditText editTextDetails = findViewById(R.id.editTextDetails);
        Button btnAddVolunteering = findViewById(R.id.btnAddVolunteering);


        // Initialize the ActivityResultLauncher
        takePictureLauncher = registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> {
            if (result) {
                btnUploadImage.setImageURI(currentImageUri);
            }
        });


        btnUploadImage.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                        REQUEST_CAMERA_PERMISSION);
            } else {
                captureImage();
            }
        });


        btnAddVolunteering.setOnClickListener(v -> {
            String location = editTextLocation.getText().toString();
            String date = editTextDate.getText().toString();
            String activity = editTextActivity.getText().toString();
            String details = editTextDetails.getText().toString();


            if (currentImageUri != null) {
                // Log the data being passed back
                Log.d(TAG, "Location: " + location + ", Date: " + date + ", Activity: " + activity + ", Details: " + details + ", ImageUri: " + currentImageUri.toString());


                // Create an intent to pass the data back to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("location", location);
                resultIntent.putExtra("date", date);
                resultIntent.putExtra("activity", activity);
                resultIntent.putExtra("details", details);
                resultIntent.putExtra("imageUri", currentImageUri.toString());


                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(this, "Image not captured", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private Uri createImageUri() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }


    private void captureImage() {
        currentImageUri = createImageUri();
        if (currentImageUri != null) {
            takePictureLauncher.launch(currentImageUri);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                captureImage();
            } else {
                Toast.makeText(this, "Camera permission is required to use the camera", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



