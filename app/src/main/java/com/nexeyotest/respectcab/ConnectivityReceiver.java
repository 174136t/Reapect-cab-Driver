package com.nexeyotest.respectcab;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityReceiver
        extends BroadcastReceiver {

    public static ConnectivityReceiverListener connectivityReceiverListener;

    public ConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();




    }
//public static boolean isConnected() {
//    ConnectivityManager cm = (ConnectivityManager)MyApplication.getInstance()
//            .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//    if (activeNetwork != null && activeNetwork.isConnected()) {
//        try {
//            URL url = new URL("http://www.google.com/");
//            HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
//            urlc.setRequestProperty("User-Agent", "test");
//            urlc.setRequestProperty("Connection", "close");
//            urlc.setConnectTimeout(1000); // mTimeout is in seconds
//            urlc.connect();
//            if (urlc.getResponseCode() == 200) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (IOException e) {
//            Log.i("warning", "Error checking internet connection", e);
//            return false;
//        }
//    }
//
//    return false;
//
//}




    public interface ConnectivityReceiverListener {
       // void onLocationChanged(Location location);

        //void onLocationChanged(Location location);

        //void onLocationChange(Location location);

        void onNetworkConnectionChanged(boolean isConnected);
    }
}
