package com.example.mira.drugikolokvij;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.app.Service.START_STICKY;

/**
 * Created by mira on 24.01.17..
 */

public class MyService extends Service {

    //za timer:
    int counter = 0;

    static final int UPDATE_INTERVAL = 1000;
    private Timer timer = new Timer();
    int notificationID= 1;

    // do tuda za timer


    @Override
    public void onCreate(){
        Log.d("SERVICE", "pozvao se onCreate");
    }

    
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // ovaj servis radi dok ga se ne zaustavi eksplicitno
        // dakle vraca sticky

        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        //za timer:
        doSomethingRepeatedly();


        return START_STICKY;
    }

    //za timer
    private void doSomethingRepeatedly() {
        timer.scheduleAtFixedRate( new TimerTask() {
            public void run() {
                Log.d("MyService", String.valueOf(++counter));
                if(counter == 5) {
                    onDestroy();
                }
            }
        }, 0, UPDATE_INTERVAL);

    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        displayNotification();

        //za timer
        if (timer != null){
            timer.cancel();

        }

        //Toast.makeText(getApplicationContext(), "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    protected void displayNotification()
    {
        //---PendingIntent to launch activity if the user selects
        // this notification---
        Log.d("MYSERVICE", "display notification from serivce!");
        Intent i = new Intent(this, NotificationView.class);

        i.putExtra("notificationID", notificationID);


        PendingIntent pendingIntent =
                PendingIntent.getActivity(getApplicationContext(), 0, i, 0);

        long[] vibrate = new long[] { 100, 250, 100, 500};

        NotificationManager nm = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);


        Notification notif = new Notification.Builder(this)
                .setTicker("Reminder: meeting starts in 5 minutes")
                .setContentTitle("Meeting with customer at 3pm...")
                .setContentText("this is the second row")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setContentIntent(pendingIntent)
                .setVibrate(vibrate)
                .build();


        nm.notify(notificationID, notif);
    }

}