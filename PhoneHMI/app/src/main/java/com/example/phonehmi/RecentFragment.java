package com.example.phonehmi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ServicePackage.RecentsModel;


public class RecentFragment extends Fragment {

    private ArrayList<RecentsModel> callLogModelArrayList;
    private RecyclerView rv_call_logs;
    private RecentsAdapter recentsAdapter;
    public String str_number,str_contact_name, str_date;

    private static final int PERMISSIONS_REQUEST_CODE = 999;
    Cursor cursor;

    String[] appPermissions = {
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_PHONE_STATE

    };
    private int flag = 0;




    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_recent, container, false);






        rv_call_logs = view.findViewById(R.id.activity_main_rv);
        rv_call_logs.setHasFixedSize(true);
        rv_call_logs.setLayoutManager(new LinearLayoutManager(getContext()));
        callLogModelArrayList = new ArrayList<>();
        recentsAdapter = new RecentsAdapter(getContext(), callLogModelArrayList);
        rv_call_logs.setAdapter(recentsAdapter);

        if (CheckAndRequestPermission()){
            cursor= MainActivity.getAidl().FetchCallLogs();
        }


        while (cursor.moveToNext()) {

            str_number=cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));

            str_contact_name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));

            str_contact_name=str_contact_name==null||str_contact_name.equals("") ? str_number:str_contact_name;

            str_date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
            SimpleDateFormat dateFormatter = new SimpleDateFormat(
                    "dd MMM yyyy hh:mm a");
            str_date = dateFormatter.format(new Date(Long.parseLong(str_date)));


            RecentsModel callLogItem=new RecentsModel(str_contact_name,str_date);

            callLogModelArrayList.add(callLogItem);
        }

        recentsAdapter.notifyDataSetChanged();



        return view;

    }


    public boolean CheckAndRequestPermission() {
        //checking which permissions are granted
        List<String> listPermissionNeeded = new ArrayList<>();
        for (String item: appPermissions){
            if(ContextCompat.checkSelfPermission(getContext(), item)!= PackageManager.PERMISSION_GRANTED)
                listPermissionNeeded.add(item);
        }

        //Ask for non-granted permissions
        if (!listPermissionNeeded.isEmpty()){
            ActivityCompat.requestPermissions(getActivity(), listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]),
                    PERMISSIONS_REQUEST_CODE);
            return false;
        }
        //App has all permissions. Proceed ahead
        return true;
    }



}