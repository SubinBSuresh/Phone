package com.example.phonehmi;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.ContactModel;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private static List<ServicePackage.ContactModel> contactList;

    ContactModel contactModel;
    private Context context;


             //   }
          //  });
      }


    public ContactAdapter(List<ContactModel> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(contactList.get(position).getName());
        contactModel = contactList.get(position);


        //subine ithu correct anonn areelatto
       /* try {

            if (MainActivity.getAidl().checkContactPresentInFavoritesTable(int id));
            {
                holder.arbutton.setBackgroundResource(R.drawable.ic_baseline_unstar_24);
            }
            else {
                holder.arbutton.setBackgroundResource(R.drawable.ic_baseline_star_24);
            }
        } catch (Exception e) {
            Log.e("%%%%%%%%%%%%", "broken");
        }
    }
*/
    }

        @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ToggleButton arbutton;

            //arbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                //@Override
              //  public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
/*
                    if (ischecked) {
                        arbutton.setBackgroundResource(R.drawable.ic_baseline_star_24);
                        try {
                            MainActivity.getAidl().checkContactPresentInFavoritesTable(contactList.get(getAdapterPosition()));
                            MainActivity.getAidl().addContactToFavorites(contactList.get(getAdapterPosition()));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }else {
                      arbutton.setBackgroundResource(R.drawable.ic_baseline_unstar_24);
                        try {
                            MainActivity.getAidl().removeContactFromFavorites.(contactList.get(getAdapterPosition()));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
            };*/

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           name = itemView.findViewById(R.id.contact_name);
           arbutton = itemView.findViewById(R.id.btn_add_favorite);

            //sheriyano enn doubt aanu

            //arbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                //@Override
              //  public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
/*
                    if (ischecked) {
                        arbutton.setBackgroundResource(R.drawable.ic_baseline_star_24);
                        try {
                            MainActivity.getAidl().checkContactPresentInFavoritesTable(contactList.get(getAdapterPosition()));
                            MainActivity.getAidl().addContactToFavorites(contactList.get(getAdapterPosition()));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }else {
                      arbutton.setBackgroundResource(R.drawable.ic_baseline_unstar_24);
                        try {
                            MainActivity.getAidl().removeContactFromFavorites.(contactList.get(getAdapterPosition()));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
            };*/

}
             //   }
          //  });
      }


        @Override
        public void onClick(View view) {
            TextView name  = view.findViewById(R.id.contact_name);
            Toast.makeText(view.getContext(),name.getText().toString(),Toast.LENGTH_SHORT).show();
            Log.e("*******************", name.getText().toString());
        }
    }

}
