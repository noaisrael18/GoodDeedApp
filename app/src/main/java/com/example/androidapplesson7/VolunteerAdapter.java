package com.example.androidapplesson7;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder> {

    private List<VolunteerOpp> volunteerOppList;

    public VolunteerAdapter(List<VolunteerOpp> volunteerOppList) {
        this.volunteerOppList = volunteerOppList;
    }

    @NonNull
    @Override
    public VolunteerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.volunteeropp, parent, false);
        return new VolunteerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteerViewHolder holder, int position) {
        VolunteerOpp volunteerOpp = volunteerOppList.get(position);
        if (volunteerOpp != null) {
            holder.name.setText(volunteerOpp.getActivity());
            holder.date.setText(volunteerOpp.getDate());
            holder.location.setText(volunteerOpp.getLocation());
            Picasso.get().load(volunteerOpp.getImage()).into(holder.image);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("image", volunteerOpp.getImage());
                intent.putExtra("name", volunteerOpp.getActivity());
                intent.putExtra("date", volunteerOpp.getDate());
                intent.putExtra("location", volunteerOpp.getLocation());
                intent.putExtra("details", volunteerOpp.getDetails());
                holder.itemView.getContext().startActivity(intent);
            });
        } else {
            // Log the issue for debugging
            Log.e("VolunteerAdapter", "Null VolunteerOpp at position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return volunteerOppList.size();
    }

    public static class VolunteerViewHolder extends RecyclerView.ViewHolder {
        public TextView name, date, location;
        public ImageView image;

        public VolunteerViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            date = view.findViewById(R.id.date);
            location = view.findViewById(R.id.location);
            image = view.findViewById(R.id.image);
        }
    }
}
