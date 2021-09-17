package com.example.healthy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SymptomListAdapter extends RecyclerView.Adapter<SymptomListAdapter.InfoHolder> {

    ArrayList<SymptomListGetterSetter> symptomListGetterSetters;

    public SymptomListAdapter(ArrayList<SymptomListGetterSetter> symptomListGetterSetters) {
        this.symptomListGetterSetters = symptomListGetterSetters;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public SymptomListAdapter.InfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptom_list,parent,false);
        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomListAdapter.InfoHolder holder, int position) {
        SymptomListGetterSetter symptomListGetterSetter = symptomListGetterSetters.get(position);
        holder.symptom.setText(symptomListGetterSetter.getSymptom());
        holder.remedy.setText(symptomListGetterSetter.getRemedy());
        holder.day.setText(symptomListGetterSetter.getDay());
        holder.month.setText(symptomListGetterSetter.getMonth());
        holder.tvSymptom.setText(symptomListGetterSetter.getTvSymptom());
        holder.tvRemedy.setText(symptomListGetterSetter.getTvRemedy());

    }

    @Override
    public int getItemCount() {
        return symptomListGetterSetters.size();
    }


    public class InfoHolder extends RecyclerView.ViewHolder{
        TextView symptom,remedy,day,month,tvSymptom,tvRemedy;
        public InfoHolder(@NonNull View itemView) {
            super(itemView);
            symptom = itemView.findViewById(R.id.symptom);
            remedy = itemView.findViewById(R.id.remedy);
            day = itemView.findViewById(R.id.day);
            month = itemView.findViewById(R.id.month);
            tvSymptom = itemView.findViewById(R.id.tv_symptom);
            tvRemedy = itemView.findViewById(R.id.tv_remedy);
        }
    }
}
