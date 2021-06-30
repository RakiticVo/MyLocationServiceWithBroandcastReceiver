package com.example.mylocationservicewithbroandcastreceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;

public class LocationService extends BroadcastReceiver {

    public static final String ACTION_PROCESS_UPDATE = "com.example.mylocationservice.UPDATE_LOCATION";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent!=null){
            final String action = intent.getAction();
            if (ACTION_PROCESS_UPDATE.equals(action)){
                LocationResult result = LocationResult.extractResult(intent);
                if (result!=null){
                    Location location = result.getLastLocation();
                    String locationString = new StringBuilder(""+location.getLatitude())
                            .append("--")
                            .append(location.getLongitude())
                            .toString();
                    try {
                        MainActivity.getInstance().showLocation(locationString);
                    }catch (Exception e){
                        Toast.makeText(context, locationString, Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "onReceive: " + e.getLocalizedMessage());
                    }
                }
            }
        }
    }
}