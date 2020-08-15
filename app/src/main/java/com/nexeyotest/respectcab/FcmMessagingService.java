package com.nexeyotest.respectcab;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nexeyo.respectcab.R;

import androidx.core.app.NotificationCompat;

public class FcmMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        String click_action = remoteMessage.getNotification().getClickAction();
        Intent intent = new Intent(click_action);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("New Ride Available!!");
        notificationBuilder.setContentText("Do you want this?");
        notificationBuilder.setSmallIcon(R.drawable.ic_stat_ic_notification);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.addAction(R.drawable.ic_confirm,"Accept",pendingIntent) ;
        notificationBuilder.addAction(R.drawable.amu_bubble_mask,"Cancel",pendingIntent) ;
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }
}
