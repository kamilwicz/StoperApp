package com.example.stoper;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import static com.example.stoper.App.CHANNEL_ID;



public class ExampleService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        
        int timeMill =intent.getIntExtra("timeMillExtra",00);
        int timeSec = intent.getIntExtra("timeSecExtra",00);
        int timeMin = intent.getIntExtra("timeMinExtra",00);
        int timeHour = intent.getIntExtra("timeHourExtra",00);

        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Czas")
                .setContentText(String.format("%02d:%02d:%02d.%02d",timeHour,timeMin, timeSec, timeMill))
                .setSmallIcon(R.drawable.ic_timer)
//              .setContentIntent(pendingIntent)
                .setVibrate(new long[]{ 0 })
                .build();



        startForeground(1, notification);


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}