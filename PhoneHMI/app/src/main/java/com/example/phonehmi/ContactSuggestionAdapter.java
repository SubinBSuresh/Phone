package com.example.phonehmi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ServicePackage.SuggestionModel;

public class ContactSuggestionAdapter extends RecyclerView.Adapter<ContactSuggestionAdapter.ViewHolder> {

    private final List<SuggestionModel> contactModelList;
    private final Context context;

    public ContactSuggestionAdapter(List<SuggestionModel> contactModelList, Context context) {
        this.contactModelList = contactModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public ContactSuggestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.contact_suggestion_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SuggestionModel suggestionModel = contactModelList.get(position);
        holder.tvSuggestedName.setText(suggestionModel.getContactName());
        holder.tvSuggestedNumber.setText(suggestionModel.getContactNumber());


    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvSuggestedName;
        TextView tvSuggestedNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSuggestedName = itemView.findViewById(R.id.tvContactSuggestionName);
            tvSuggestedNumber = itemView.findViewById(R.id.tvContactSuggestionNumber);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            TextView contactNumber = view.findViewById(R.id.tvContactSuggestionNumber);
            TextView contactName = view.findViewById(R.id.tvContactSuggestionName);
            DialerFragment.tvNumber.setText(contactNumber.getText().toString());
            DialerFragment.tvName.setText(contactName.getText().toString());

        }
    }
}
