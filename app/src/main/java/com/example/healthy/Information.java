package com.example.healthy;

import android.content.Context;
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

    Context context;
    ArrayList<infoHelper> infoHelpers;

    public Information(Context context, ArrayList<infoHelper> infoHelpers) {
        this.context = context;
        this.infoHelpers = infoHelpers;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.infomation_recycled,parent,false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {

        infoHelper infoHelper = new infoHelper();
        holder.title.setText(infoHelper.getTitle());
    }

    @Override
    public int getItemCount() {
        return infoHelpers.size();
    }

    class InfoViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.facts_title);
        }
    }
}
