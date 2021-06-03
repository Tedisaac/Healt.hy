package com.example.healthy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomViewHolder> {

    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.user_message);
        }
    }

    List<RespnseMessage> respnseMessages;

    public MessageAdapter(List<RespnseMessage> respnseMessages) {
        this.respnseMessages = respnseMessages;
    }

    @Override
    public int getItemViewType(int position) {
        if (respnseMessages.get(position).isUser){
            return R.layout.user_bubble;
        }
        return R.layout.responder_bubble;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.CustomViewHolder holder, int position) {

        holder.textView.setText(respnseMessages.get(position).getTextMessage());
    }

    @Override
    public int getItemCount() {
        return respnseMessages.size();
    }
}
