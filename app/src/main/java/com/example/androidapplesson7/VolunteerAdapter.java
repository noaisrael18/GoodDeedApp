package com.example.androidapplesson7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// Specify that we want to use the ViewHolder we created:
public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerViewHolder>{
    List<VolunteerOpp> VolunteerOppList;

    public VolunteerAdapter() {
        super();
        VolunteerOppList = new ArrayList<>();
        VolunteerOppList.add(new VolunteerOpp(R.drawable.image1, "Strawberry Field Picking", "07/03/24 | 9:00 AM", "Kfar Hess"));
        VolunteerOppList.add(new VolunteerOpp(R.drawable.image2, "Community Food Packaging", "18/03/24 | 12:00 PM", "Tel Aviv Expo"));
        VolunteerOppList.add(new VolunteerOpp(R.drawable.image3, "Evacuated Kiryat Shmona Child Play", "28/03/24 | 6:00 PM", "Ritz-Carlton Herzliya"));
        VolunteerOppList.add(new VolunteerOpp(R.drawable.image4, "Bomb Shelter Restoration", "07/03/24 | 4:00 PM", "Herzl 13, Rehovot"));
        VolunteerOppList.add(new VolunteerOpp(R.drawable.image5, "Meal Packaging for Frontline Soldiers", "2/04/24 | 8:00 PM", "Pantry Packers, Jeru..."));
        VolunteerOppList.add(new VolunteerOpp(R.drawable.image6, "Lemon & Avocado Picking", "11/04/24 | 12:00 PM", "Moshav Betzet"));
        VolunteerOppList.add(new VolunteerOpp(R.drawable.image7, "Evacuated Family Dog Walking", "07/05/24 | 4:00 PM", "Herzliya Beach"));
    }


    @NonNull
    @Override
    public VolunteerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.volunteeropp,parent,false);
        VolunteerViewHolder viewHolder = new VolunteerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteerViewHolder holder, int position) {
        VolunteerOpp VolunteerOpp = VolunteerOppList.get(position);
        holder.Image.setImageResource(VolunteerOpp.Image);
        holder.Name.setText(VolunteerOpp.Name);
        holder.Date.setText(VolunteerOpp.Date);
        holder.Location.setText(VolunteerOpp.Location);
    }

    @Override
    public int getItemCount() {
        // item count is returned based on how many items we have in the list
        return VolunteerOppList.size();
    }
}
