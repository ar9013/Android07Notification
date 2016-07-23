package com.javaclass.anima.android07notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View notie1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notie1 = findViewById(R.id.notice1);
        notie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendNotification();
            }
        });
    }

    private void SendNotification(){
        Intent intent = new Intent(this, NoticePage.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NoticePage.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pending = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("重要的通知");
        builder.setAutoCancel(true);
        builder.setContentInfo("Info");
        builder.setContentText("text");
        builder.setContentTitle("title");
        builder.setWhen(System.currentTimeMillis() + 1000);
        builder.setContentIntent(pending);

        Notification notification = builder.getNotification(); // API Level 11+
//		Notification notification = builder.build(); // API Level 16+

        NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mgr.notify(0, notification);

    }
}
