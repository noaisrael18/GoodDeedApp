package com.example.androidapplesson7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseFirestore db;
    private RecyclerView rv;
    private VolunteerAdapter adapter;
    private List<VolunteerOpp> volunteerOppList;
    private static final int REQUEST_CODE_ADD_VOLUNTEERING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this); // Initialize Firebase Analytics
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true); // Initialize Firebase Crashlytics
        db = FirebaseFirestore.getInstance(); // Initialize Firestore
        // Initialize volunteer opportunities list
        volunteerOppList = new ArrayList<>();

        // Log an event
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "MainActivity");
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

        // Set OnClickListener for Add Volunteering button
        Button btnAddVolunteer = findViewById(R.id.AddVolunteer);
        btnAddVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddVolunteerPage();
            }
        });

        // Set OnClickListener for Profile button
        ImageView profileImageView = findViewById(R.id.imageView);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfilePage();
            }
        });

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(layoutManager);

        // Initialize adapter with empty data
        adapter = new VolunteerAdapter(volunteerOppList);
        rv.setAdapter(adapter); // Ensure this line is present

        // Fetch data from Firestore
        fetchVolunteerOpportunities();
    }

    private void fetchVolunteerOpportunities() {
        db.collection("Created Events")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                            try {
                                VolunteerOpp volunteerOpp = document.toObject(VolunteerOpp.class);
                                if (volunteerOpp != null) {
                                    // Handle missing fields by providing default values
                                    if (volunteerOpp.getActivity() == null) {
                                        volunteerOpp.setActivity("No activity provided");
                                    }
                                    if (volunteerOpp.getDate() == null) {
                                        volunteerOpp.setDate("No date provided");
                                    }
                                    if (volunteerOpp.getLocation() == null) {
                                        volunteerOpp.setLocation("No location provided");
                                    }
                                    if (volunteerOpp.getImage() == null) {
                                        volunteerOpp.setImage("default_image_url"); // Provide a default image URL
                                    }
                                    if (volunteerOpp.getDetails() == null) {
                                        volunteerOpp.setDetails("No details provided");
                                    }

                                    volunteerOppList.add(volunteerOpp);
                                    Log.d("MainActivity", "Fetched: " + volunteerOpp.getActivity());
                                } else {
                                    // Log the issue for debugging
                                    Log.e("MainActivity", "Null VolunteerOpp fetched from Firestore");
                                }
                            } catch (Exception e) {
                                Log.e("MainActivity", "Error processing document: " + document.getId(), e);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        Log.d("MainActivity", "Total Opportunities: " + volunteerOppList.size());
                        Toast.makeText(MainActivity.this, "Data fetched successfully.", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.w("MainActivity", "Error getting documents.", task.getException());
                        Toast.makeText(MainActivity.this, "Error getting data from Firestore.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_VOLUNTEERING && resultCode == Activity.RESULT_OK) {
            // Handle the result from Add Volunteering activity
            if (data != null) {
                VolunteerOpp newVolunteerOpp = (VolunteerOpp) data.getSerializableExtra("newVolunteerOpp");
                volunteerOppList.add(newVolunteerOpp);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void openAddVolunteerPage() {
        Intent intent = new Intent(this, AddVolunteeringActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_VOLUNTEERING);
    }

    private void openProfilePage() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
