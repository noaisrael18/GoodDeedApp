package com.example.androidapplesson7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;

// Inherits AppCompatActivity Class
public class MainActivity extends AppCompatActivity {

    // onCreate function is added to be called everytime new page is opened
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // defines which layout from resources to show on screen
        setContentView(R.layout.activity_main);

        //Change text in button
        Button btn = findViewById(R.id.button);
        btn.setText("Add Volunteering");

        RecyclerView rv = findViewById(R.id.rv);
        // Size of recycler view can be changed on run time:
        rv.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        rv.setLayoutManager(layoutManager);

        VolunteerAdapter adapter = new VolunteerAdapter();
        rv.setAdapter(adapter);
    }

}