package com.example.androidapplesson7;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.analytics.FirebaseAnalytics;




public class ProfileActivity extends AppCompatActivity {




    private FirebaseAnalytics mFirebaseAnalytics;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        // Initialize Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);




        // Log an event
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "ProfileActivity");
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "ProfileActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);




        ImageView homeImageView = findViewById(R.id.homeImageView);
        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, OnboardingActivity.class);
                startActivity(intent);
            }
        });
    }
}
