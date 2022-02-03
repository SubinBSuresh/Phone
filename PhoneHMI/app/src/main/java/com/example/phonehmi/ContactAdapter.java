package com.example.phonehmi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    ArrayList<ContactModel> ContactHolder;
    private Context context;

    public ContactAdapter(ArrayList<ContactModel> ContactHolder, Context context) {
        this.ContactHolder = ContactHolder;
        this.context = context;
    }

    //public FavAdapter(ArrayList<FavModel> favData) {
    //    this.favData = favData;
    // }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(ContactHolder.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return ContactHolder.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ToggleButton arbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contact_name);
            arbutton= itemView.findViewById(R.id.btn_addOrRemove_favorite);
            arbutton.setOnClickListener(this);

            arbutton.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.drawable.star_big_off));

            name.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
             int position = this.getAdapterPosition();
           ContactHolder.remove(position);
           notifyItemRemoved(position);
        }
    }
}


