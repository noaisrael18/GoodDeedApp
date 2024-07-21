package com.example.androidapplesson7;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView imageView = findViewById(R.id.detailsImageView);
        TextView nameTextView = findViewById(R.id.detailsNameTextView);
        TextView dateTextView = findViewById(R.id.detailsDateTextView);
        TextView locationTextView = findViewById(R.id.detailsLocationTextView);
        TextView detailsTextView = findViewById(R.id.detailsTextView);

        Intent intent = getIntent();
        String imageUriString = intent.getStringExtra("imageUri");
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        String location = intent.getStringExtra("location");
        String details = intent.getStringExtra("details");

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            Picasso.get().load(imageUri).into(imageView); // Use Picasso to load the image
        }

        nameTextView.setText(name);
        dateTextView.setText(date);
        locationTextView.setText(location);
        detailsTextView.setText(details);
    }
}
