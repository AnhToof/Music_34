package com.framgia.music_34.screen.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.framgia.music_34.utils.ActionNotification;

public class NotificationBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case ActionNotification.ACTION_PREVIOUS:

                break;
            case ActionNotification.ACTION_NEXT:

                break;
            case ActionNotification.ACTION_PLAY:

                break;
            case ActionNotification.ACTION_PAUSE:

                break;
        }
    }
}
