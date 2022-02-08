package com.example.phonehmi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ServicePackage.RecentsModel;


public class RecentFragment extends Fragment {


    private RecyclerView rv_call_logs;
    private RecentsAdapter recentsAdapter;
    public String str_number, str_contact_name, str_date;
    List<RecentsModel> recentsModels ;
    private static final int PERMISSIONS_REQUEST_CODE = 999;
    public Cursor cursor;

    String[] appPermissions = {
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_PHONE_STATE

    };
    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        rv_call_logs = view.findViewById(R.id.activity_main_rv);
        rv_call_logs.setHasFixedSize(true);
        rv_call_logs.setLayoutManager(new LinearLayoutManager(getContext()));




      /*  if (CheckAndRequestPermission()) {
            try {
//              cursor = MainActivity.getAidl().fetchCallLogs();
                cursor = fetchCallLogs();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }*/

        try {
            recentsModels = MainActivity.getAidl().fetchCallLogs();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        RecentsAdapter recentsAdapter=new RecentsAdapter(getContext(),recentsModels);

        rv_call_logs.setAdapter(recentsAdapter);

        recentsAdapter.notifyDataSetChanged();
        return view;

    }


    /*public boolean CheckAndRequestPermission() {
        //checking which permissions are granted
        List<String> listPermissionNeeded = new ArrayList<>();
        for (String item : appPermissions) {
            if (ContextCompat.checkSelfPermission(getContext(), item) != PackageManager.PERMISSION_GRANTED)
                listPermissionNeeded.add(item);
        }

        //Ask for non-granted permissions
        if (!listPermissionNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]),
                    PERMISSIONS_REQUEST_CODE);
            return false;
        }
        //App has all permissions. Proceed ahead
        return true;
    }*/


    // Working code
   /* public Cursor fetchCallLogs() throws RemoteException {
        // reading all data in descending order according to DATE
        String sortOrder = android.provider.CallLog.Calls.DATE + " DESC";

        @SuppressLint("Recycle") Cursor cursor = getContext().getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                sortOrder);
        return cursor;
    }*/


}