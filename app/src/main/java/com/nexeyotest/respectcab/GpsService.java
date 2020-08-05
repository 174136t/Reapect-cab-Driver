package com.nexeyotest.respectcab;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;

public class GpsService extends Service {
    public static final String BROADCAST_ACTION = "com.example.uber.updateUI";
    public LocationManager locationManager;
    public MyLocationListener listener;
    static Double speed = 0.0;


    Intent intent;
    private final Handler UIhandler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onStart(Intent intent, int startId) {

        UIhandler.removeCallbacks(sendUpdatesToUI);
        UIhandler.postDelayed(sendUpdatesToUI, 0);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, listener);

        Location location = null;
        speed = 18.0;

    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {

            intent.putExtra("speed", speed + "");

            sendBroadcast(intent);
            UIhandler.postDelayed(this, 0);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        UIhandler.removeCallbacks(sendUpdatesToUI);
        super.onDestroy();
        locationManager.removeUpdates(listener);
    }

//    private double roundToNDigits(double value, int nDigits) {
//        return Math.round(value * (10 ^ nDigits)) / (double) (10 ^ nDigits);
//    }

    public class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {

        }

        double degreesToRadians(double degrees) {
            return degrees * Math.PI / 180;
        }


        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}