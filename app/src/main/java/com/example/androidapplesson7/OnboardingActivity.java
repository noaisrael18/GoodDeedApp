package com.example.androidapplesson7;


import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import androidx.appcompat.app.AppCompatActivity;


public class OnboardingActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);


        // Initialize Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        // Log an event
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "OnboardingActivity");
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "OnboardingActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
}
