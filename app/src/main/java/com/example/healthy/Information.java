package com.example.healthy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Information extends RecyclerView.Adapter<Information.InfoViewHolder> {
    ArrayList<infoHelper> infohelpers;

    public Information(ArrayList<infoHelper> infohelpers) {
        this.infohelpers = infohelpers;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.infomation_recycled, parent, false);
        return new InfoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {

        infoHelper infoHelper  = infohelpers.get(position);
        holder.image.setImageResource(infoHelper.getImage());
        holder.title.setText(infoHelper.getTitle());
        holder.relativeLayout.setBackground(infoHelper.getColour());

    }

    @Override
    public int getItemCount() {
        return infohelpers.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder  {


        ImageView image;
        TextView title;
        RelativeLayout relativeLayout;


        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            //hooks

            image = itemView.findViewById(R.id.phone_image);
            title = itemView.findViewById(R.id.phone_title);
            relativeLayout = itemView.findViewById(R.id.background_color);

        }

    }
}
