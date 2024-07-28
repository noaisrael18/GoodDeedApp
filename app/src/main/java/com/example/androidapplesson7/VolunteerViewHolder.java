package com.example.androidapplesson7;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;


public class VolunteerViewHolder extends RecyclerView.ViewHolder {
    public ImageView Image;
    public TextView Name;
    public TextView Details;
    public TextView Location;
    public TextView Date;


    // Constructor
    public VolunteerViewHolder(@NotNull View itemView) {
        super(itemView);
        Image = itemView.findViewById(R.id.image);
        Name = itemView.findViewById(R.id.name);
        //    Details = itemView.findViewById(R.id.details);
        Location = itemView.findViewById(R.id.location);
        Date = itemView.findViewById(R.id.date);
    }
}

