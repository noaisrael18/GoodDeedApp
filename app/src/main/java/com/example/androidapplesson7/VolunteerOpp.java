package com.example.androidapplesson7;


import java.io.Serializable;


public class VolunteerOpp implements Serializable {
    private String activity;
    private String date;
    private String location;
    private String image;
    private String details;


    // Default constructor is required for Firestore to deserialize
    public VolunteerOpp() {
    }


    public VolunteerOpp(String activity, String date, String location, String image, String details) {
        this.activity = activity;
        this.date = date;
        this.location = location;
        this.image = image;
        this.details = details;
    }


    // Getters and setters
    public String getActivity() {
        return activity;
    }


    public void setActivity(String activity) {
        this.activity = activity;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public String getDetails() {
        return details;
    }


    public void setDetails(String details) {
        this.details = details;
    }
}
