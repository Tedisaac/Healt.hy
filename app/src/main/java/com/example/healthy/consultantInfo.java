package com.example.healthy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class consultantInfo extends RecyclerView.Adapter<consultantInfo.InfoViewHolder> {
    ArrayList<consultantHelper> consultantHelpers;
    Context context;

    public consultantInfo(ArrayList<consultantHelper> consultantHelpers, Context context) {
        this.consultantHelpers = consultantHelpers;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public consultantInfo.InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.consultant_list,parent,false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull consultantInfo.InfoViewHolder holder, int position) {

        consultantHelper consultantHelper = consultantHelpers.get(position);
        holder.con_image1.setImageResource(consultantHelper.getConsultImage());
        holder.con_title.setText(consultantHelper.getConsultTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext().getApplicationContext(),ConversationActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return consultantHelpers.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder{
        ImageView con_image1;
        TextView con_title;
        //RelativeLayout con_layout;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);

            con_image1 = itemView.findViewById(R.id.consultant_image);
            con_title = itemView.findViewById(R.id.consultant_title);
            //con_layout = itemView.findViewById(R.id.consultant_background_color);
        }
    }
}
