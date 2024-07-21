package com.example.androidapplesson7;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerViewHolder> {
    List<VolunteerOpp> VolunteerOppList;

    public VolunteerAdapter() {
        super();
        VolunteerOppList = new ArrayList<>();
        // Initialize with existing data
        VolunteerOppList.add(new VolunteerOpp(Uri.parse("android.resource://com.example.androidapplesson7/drawable/image1"), "Strawberry Field Picking", "07/03/24 | 9:00 AM", "Kfar Hess"));
        VolunteerOppList.add(new VolunteerOpp(Uri.parse("android.resource://com.example.androidapplesson7/drawable/image2"), "Community Food Packaging", "18/03/24 | 12:00 PM", "Tel Aviv Expo"));
        VolunteerOppList.add(new VolunteerOpp(Uri.parse("android.resource://com.example.androidapplesson7/drawable/image3"), "Evacuated Kiryat Shmona Child Play", "28/03/24 | 6:00 PM", "Ritz-Carlton Herzliya"));
        VolunteerOppList.add(new VolunteerOpp(Uri.parse("android.resource://com.example.androidapplesson7/drawable/image4"), "Bomb Shelter Restoration", "07/03/24 | 4:00 PM", "Herzl 13, Rehovot"));
        VolunteerOppList.add(new VolunteerOpp(Uri.parse("android.resource://com.example.androidapplesson7/drawable/image5"), "Meal Packaging for Frontline Soldiers", "2/04/24 | 8:00 PM", "Pantry Packers, Jeru..."));
        VolunteerOppList.add(new VolunteerOpp(Uri.parse("android.resource://com.example.androidapplesson7/drawable/image6"), "Lemon & Avocado Picking", "11/04/24 | 12:00 PM", "Moshav Betzet"));
        VolunteerOppList.add(new VolunteerOpp(Uri.parse("android.resource://com.example.androidapplesson7/drawable/image7"), "Evacuated Family Dog Walking", "07/05/24 | 4:00 PM", "Herzliya Beach"));
    }

    @NonNull
    @Override
    public VolunteerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.volunteeropp, parent, false);
        VolunteerViewHolder viewHolder = new VolunteerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteerViewHolder holder, int position) {
        VolunteerOpp volunteerOpp = VolunteerOppList.get(position);
        holder.Image.setImageURI(volunteerOpp.ImageUri);
        holder.Name.setText(volunteerOpp.Name);
        holder.Date.setText(volunteerOpp.Date);
        holder.Location.setText(volunteerOpp.Location);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
            intent.putExtra("imageUri", volunteerOpp.ImageUri.toString());
            intent.putExtra("name", volunteerOpp.Name);
            intent.putExtra("date", volunteerOpp.Date);
            intent.putExtra("location", volunteerOpp.Location);
            intent.putExtra("details", "Detailed information about the event"); // Add actual details here
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return VolunteerOppList.size();
    }

    // Method to add a new VolunteerOpp
    public void addVolunteerOpp(VolunteerOpp newVolunteerOpp) {
        VolunteerOppList.add(newVolunteerOpp);
        notifyItemInserted(VolunteerOppList.size() - 1);
    }
}

