package com.example.phoneservice;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.core.app.ActivityCompat;

import ServicePackage.aidlInterface;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stubObject;
    }
    aidlInterface.Stub stubObject = new aidlInterface.Stub() {
        @Override
        public void callNumber(String phoneNumber) throws RemoteException {
            if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber)));

        }
    };
}