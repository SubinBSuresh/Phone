package com.example.phonehmi;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ServicePackage.ContactModel;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private static List<ServicePackage.ContactModel> contactList;
    private final Context context;
    ContactModel contactModel;


    public ContactAdapter(List<ContactModel> contactList, Context context) {
       this.contactList = contactList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(contactList.get(position).getName());
        contactModel = contactList.get(position);


        //subine ithu correct anonn areelatto
        try {

            if (!MainActivity.getAidl().checkContactPresentInFavoritesTable(contactModel.getId())) {
                holder.arbutton.setBackgroundResource(R.drawable.ic_baseline_star_unstar_24);
            } else {
                holder.arbutton.setBackgroundResource(R.drawable.ic_baseline_star_24);
            }
        } catch (Exception e) {
            Log.e("%%%%%%%%%%%%", "broken");
        }
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageButton arbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contact_name);
            arbutton = itemView.findViewById(R.id.btn_add_favorite);

            arbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                      view.setOnClickListener(this);
                    contactModel = contactList.get(getAdapterPosition());
                    Toast.makeText(view.getContext(), "toastworking",Toast.LENGTH_SHORT).show();

                    try {
                        if (!MainActivity.getAidl().checkContactPresentInFavoritesTable(contactModel.getId())) {
                            MainActivity.getAidl().addContactToFavorites(contactModel.getId());
                            arbutton.setBackgroundResource(R.drawable.ic_baseline_star_24);
                            arbutton.setSelected(true);
                            notifyDataSetChanged();
                        } else {
                            MainActivity.getAidl().removeContactFromFavorites(contactModel.getId());
                            arbutton.setBackgroundResource(R.drawable.ic_baseline_star_unstar_24);
                            arbutton.setSelected(false);
                            notifyDataSetChanged();
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }


                }
            });
        }


        @Override
        public void onClick(View view) {
            TextView name = view.findViewById(R.id.contact_name);
            Toast.makeText(view.getContext(), name.getText().toString(), Toast.LENGTH_SHORT).show();
            Log.e("*******************", name.getText().toString());
        }
    }
}




