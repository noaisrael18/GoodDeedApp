package com.example.androidapplesson7;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {


    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        // Initialize Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        // Log an event
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "DetailsActivity");
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "DetailsActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);


        ImageView imageView = findViewById(R.id.detailsImageView);
        TextView nameTextView = findViewById(R.id.detailsNameTextView);
        TextView dateTextView = findViewById(R.id.detailsDateTextView);
        TextView locationTextView = findViewById(R.id.detailsLocationTextView);
        TextView detailsTextView = findViewById(R.id.detailsTextView);


        Intent intent = getIntent();
        String imageString = intent.getStringExtra("image");
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        String location = intent.getStringExtra("location");
        String details = intent.getStringExtra("details");


        if (imageString != null) {
            Uri imageUri = Uri.parse(imageString);
            Picasso.get().load(imageUri).into(imageView); // Use Picasso to load the image
        }


        nameTextView.setText(name);
        dateTextView.setText(date);
        locationTextView.setText(location);
        detailsTextView.setText(details);
    }
}
