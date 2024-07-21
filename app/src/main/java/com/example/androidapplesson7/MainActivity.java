package com.example.androidapplesson7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private RecyclerView rv;
    private VolunteerAdapter adapter;
    private static final int REQUEST_CODE_ADD_VOLUNTEERING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Initialize Firebase Crashlytics
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);

        // Set OnClickListener for Add Volunteering button
        Button btnAddVolunteer = findViewById(R.id.btnAddVolunteer);
        btnAddVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddVolunteerPage();
            }
        });

        // Set OnClickListener for Profile button
        ImageView profileImageView = findViewById(R.id.profileImageView);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfilePage();
            }
        });

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(layoutManager);

        adapter = new VolunteerAdapter();
        rv.setAdapter(adapter);
    }

    public void openAddVolunteerPage() {
        Intent intent = new Intent(this, AddVolunteeringActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_VOLUNTEERING);
    }

    public void openProfilePage() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_VOLUNTEERING && resultCode == Activity.RESULT_OK && data != null) {
            String location = data.getStringExtra("location");
            String date = data.getStringExtra("date");
            String activity = data.getStringExtra("activity");
            String details = data.getStringExtra("details");
            String imageUriString = data.getStringExtra("imageUri");

            if (imageUriString != null) {
                Uri imageUri = Uri.parse(imageUriString);

                // Log the data being received
                Log.d("MainActivity", "Received - Location: " + location + ", Date: " + date + ", Activity: " + activity + ", Details: " + details + ", ImageUri: " + imageUri.toString());

                // Create a new VolunteerOpp object and add it to the list
                VolunteerOpp newVolunteerOpp = new VolunteerOpp(imageUri, activity, date, location);
                adapter.addVolunteerOpp(newVolunteerOpp);
                rv.scrollToPosition(adapter.getItemCount() - 1);  // Scroll to the new item
            }
        }
    }
}
