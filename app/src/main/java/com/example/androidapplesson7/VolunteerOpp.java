package com.example.androidapplesson7;

// This class is like a database entry for contacts - we can store all 8 avatars here and use it to populate card views
public class VolunteerOpp {
    public int Image;
    public String Name;
    public String Date;
    public String Location;

    // Constructor
    public VolunteerOpp(int a, String n, String d, String l){
        Image = a;
        Name = n;
        Date = d;
        Location = l;
    }
}
