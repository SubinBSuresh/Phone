package com.example.phonehmi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ServicePackage.RecentsModel;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.MyViewHolder> {

    Context context;
    ArrayList<RecentsModel> callLogModelArrayList;

    public RecentsAdapter(Context context, ArrayList<RecentsModel> callLogModelArrayList) {
        this.context = context;
        this.callLogModelArrayList = callLogModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.layout_call_log,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RecentsModel currentLog=callLogModelArrayList.get(position);
        holder.tv_ph_name.setText(currentLog.getPhnNumber());
        holder.tv_date.setText(currentLog.getCallDate());


    }

    @Override
    public int getItemCount() {
        return callLogModelArrayList == null ? 0 : callLogModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tv_ph_name,tv_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.log_card_view);
            tv_ph_name=itemView.findViewById(R.id.phone_no);
            tv_date=itemView.findViewById(R.id.date);
        }
    }
}


