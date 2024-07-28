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


import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;


public class AddVolunteeringActivity extends AppCompatActivity {


    private static final String TAG = "AddVolunteeringActivity";
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private ImageButton btnUploadImage;
    private Uri currentImageUri;
    private ActivityResultLauncher<Uri> takePictureLauncher;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_volunteering);


        // Initialize Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        // Initialize Firestore
        db = FirebaseFirestore.getInstance();


        // Log an event
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "AddVolunteeringActivity");
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "AddVolunteeringActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);


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
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            } else {
                openCamera();
            }
        });


        btnAddVolunteering.setOnClickListener(v -> {
            String location = editTextLocation.getText().toString();
            String date = editTextDate.getText().toString();
            String activity = editTextActivity.getText().toString();
            String details = editTextDetails.getText().toString();


            if (location.isEmpty() || date.isEmpty() || activity.isEmpty() || details.isEmpty() || currentImageUri == null) {
                Toast.makeText(this, "Please fill all fields and upload an image.", Toast.LENGTH_SHORT).show();
            } else {
                VolunteerOpp newVolunteerOpp = new VolunteerOpp(activity, date, location, currentImageUri.toString(), details);
                addVolunteerOppToFirestore(newVolunteerOpp);


                Intent resultIntent = new Intent();
                resultIntent.putExtra("newVolunteerOpp", newVolunteerOpp);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }


    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        currentImageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        takePictureLauncher.launch(currentImageUri);
    }


    private void addVolunteerOppToFirestore(VolunteerOpp volunteerOpp) {
        db.collection("Created Events")
                .add(volunteerOpp)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error adding document", e);
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
