package com.example.phonehmi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ServicePackage.ContactModel;

public class ContactSuggestionAdapter extends RecyclerView.Adapter<ContactSuggestionAdapter.ViewHolder> {

    private final List<ContactModel> contactModelList;
    private final Context context;

    public ContactSuggestionAdapter(List<ContactModel> contactList, Context context){
        this.contactModelList = contactList;
        this.context = context;
    }


    @NonNull
    @Override
    public ContactSuggestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.contact_suggestion_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView tvSuggestedName;
        TextView tvSuggestedNumber;
        TextView tvContactID;

        tvSuggestedName = holder.tvSuggestedName;
        tvSuggestedNumber = holder.tvSuggestedNumber;
        tvContactID = holder.tvContactID;

        tvSuggestedName.setText(contactModelList.get(position).getName());
        tvContactID.setText(contactModelList.get(position).getId());
        tvSuggestedNumber.setText(contactModelList.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvSuggestedName;
        TextView tvSuggestedNumber;
        TextView tvContactID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSuggestedName = itemView.findViewById(R.id.tvContactSuggestionName);
            tvSuggestedNumber = itemView.findViewById(R.id.tvContactSuggestionNumber);
            tvContactID = itemView.findViewById(R.id.tvContactId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
