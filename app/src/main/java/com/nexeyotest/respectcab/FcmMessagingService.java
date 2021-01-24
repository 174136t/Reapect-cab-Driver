package com.nexeyotest.respectcab;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nexeyo.respectcab.BuildConfig;
import com.nexeyo.respectcab.R;

import java.util.Map;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG;



public class FcmMessagingService extends FirebaseMessagingService {
    static String phoneNo;
    SharedPreferences sp;
    String title;
    String uid;
    String startAddress;
    String destinationAddress;
    String email;
    @Override


public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("msg", "onMessageReceived: " + remoteMessage.getData().get("message"));
            if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            title = remoteMessage.getData().get("title");
            uid = remoteMessage.getData().get("uid");
            phoneNo = remoteMessage.getData().get("phoneNo");
            startAddress = remoteMessage.getData().get("start");
            destinationAddress = remoteMessage.getData().get("destination");
            email = remoteMessage.getData().get("email");
                String newNumber = phoneNo.substring(Math.max(phoneNo.length() - 9, 0));
                newNumber ="0"+newNumber;
                Log.d(TAG, "Message data payload: " + newNumber);

                sp = getSharedPreferences("IDvalue", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("CustomerPhone", phoneNo);
            editor.putString("uid",uid);
            editor.putString("start",startAddress);
            editor.putString("destination",destinationAddress);
            editor.putString("email",email);
            editor.apply();

        }
        final Uri NOTIFICATION_SOUND_URI = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BuildConfig.APPLICATION_ID + "/" + R.raw.notify);
        String click_action = remoteMessage.getNotification().getClickAction();
        //Intent intent = new Intent(this, MainActivity.class);
//        Intent intent;
//        Log.d(TAG, "titleeeeeeeeeeeee: " + title);
//        if(title == "This ride has cancelled!!!"){
//             intent = new Intent(FcmMessagingService.this, CancelNotificationActivity.class);
//        }else{
//             intent = new Intent(click_action);
//        }
        Intent intent = new Intent(click_action);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = "Default";
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.drawable.ic_stat_ic_notification)
        .setContentTitle(remoteMessage.getNotification().getTitle())
                .setColor(Color.RED).setSound(NOTIFICATION_SOUND_URI)
        .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(pendingIntent);;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(channel);
        }
        manager.notify(0, builder.build());
        }
        }