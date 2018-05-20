package com.minhphongvn.volock.volock;

/**
 * Created by tisun375 on 2/26/2018.
 */

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.view.KeyEvent;
import android.widget.Toast;

public class LockService extends Service {

    BroadcastReceiver receiver;
    Activity activity;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onCreate() {
           KeyguardManager.KeyguardLock key;
        KeyguardManager km = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);

        //This is deprecated, but it is a simple way to disable the lockscreen in code
        key = km.newKeyguardLock("IN");

        key.disableKeyguard();

        //Start listening for the Screen On, Screen Off, and Boot completed actions
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);

        //Set up a receiver to listen for the Intents in this Service
        receiver = new LockReceiver();
        registerReceiver(receiver, filter);

        super.onCreate();

        Toast.makeText(this, "Đã bật Fake ID", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onDestroy() {
        Toast.makeText(this, "Đã Tắt Fake ID", Toast.LENGTH_LONG).show();
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}

