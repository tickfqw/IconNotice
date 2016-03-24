package com.example.tick.iconnotice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private NotificationManager myNotiManager;
    private Spinner mySpinner;
    private ArrayAdapter<String> myAdapter;
    private static final String[] status={"在线","离开","忙碌中","马上回来","离线"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        myNotiManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mySpinner=(Spinner)findViewById(R.id.mySpinner);
        myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,status);
        myAdapter.setDropDownViewResource(R.layout.myspinner_dropdown);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3){
                if(status[arg2].equals("在线")){
                    setNotiType(R.drawable.msn,"在线");
                }
                else if(status[arg2].equals("离开")){
                    setNotiType(R.drawable.away,"离开");
                }
                else if(status[arg2].equals("忙碌中")){
                    setNotiType(R.drawable.busy,"忙碌中");
                }
                else if(status[arg2].equals("马上回来")){
                    setNotiType(R.drawable.min,"马上回来");
                }
                else {
                    setNotiType(R.drawable.offine,"离线");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    private void setNotiType(int iconId, String text) {
        Intent notifyIntent=new Intent(this,MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent appIntent=PendingIntent.getActivity(MainActivity.this,0,notifyIntent,0);

        Notification myNoti=new Notification.Builder(MainActivity.this)
                .setSmallIcon(iconId)
                .setTicker(text)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(appIntent)
                .setContentTitle("MSN登录状态")
                .build();
      /* myNoti.icon=iconId;
       myNoti.tickerText=text;
        myNoti.defaults=Notification.DEFAULT_SOUND;
        myNoti.setLatestEventInfo(MainActivity.this, "MSN登录状态", text, appIntent);*/

        myNotiManager.notify(0,myNoti);
    }
}
