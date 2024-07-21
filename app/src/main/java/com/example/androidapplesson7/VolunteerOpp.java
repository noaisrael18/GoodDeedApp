package com.example.androidapplesson7;


import android.net.Uri;


public class VolunteerOpp {
    public Uri ImageUri;
    public String Name;
    public String Date;
    public String Location;


    // Constructor
    public VolunteerOpp(Uri imageUri, String name, String date, String location){
        ImageUri = imageUri;
        Name = name;
        Date = date;
        Location = location;
    }
}