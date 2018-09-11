package com.framgia.music_34.data;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import com.framgia.music_34.R;
import com.framgia.music_34.screen.main.MainActivity;
import com.framgia.music_34.utils.Constants;

public class NotificationGenerator {

    private static final String CHANNEL_ID = "MUSIC_CHANNEL_ID";

    public static void openMusicNotification(Context context) {
        RemoteViews expandedView =
                new RemoteViews(context.getPackageName(), R.layout.notification_action);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notifyIntent = new Intent(context, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_logo);
        builder.setCustomBigContentView(expandedView);

        setListeners(expandedView, context);
    }

    private static void setListeners(RemoteViews expandedView, Context context) {
        Intent previousIntent = new Intent(Constants.TypeAction.ACTION_PREVIOUS);
        Intent nextIntent = new Intent(Constants.TypeAction.ACTION_NEXT);
        Intent playIntent = new Intent(Constants.TypeAction.ACTION_PLAY);
        Intent pauseIntent = new Intent(Constants.TypeAction.ACTION_PAUSE);

        PendingIntent previousPendingIntent = PendingIntent.getBroadcast(context, 0, previousIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        expandedView.setOnClickPendingIntent(R.id.image_previous, previousPendingIntent);

        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(context, 0, nextIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        expandedView.setOnClickPendingIntent(R.id.image_previous, nextPendingIntent);

        PendingIntent playPendingIntent = PendingIntent.getBroadcast(context, 0, playIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        expandedView.setOnClickPendingIntent(R.id.image_previous, playPendingIntent);

        PendingIntent pausePendingIntent = PendingIntent.getBroadcast(context, 0, pauseIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        expandedView.setOnClickPendingIntent(R.id.image_previous, pausePendingIntent);
    }
}
