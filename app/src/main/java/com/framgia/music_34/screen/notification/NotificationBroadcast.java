package com.framgia.music_34.screen.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.framgia.music_34.utils.Constants;

public class NotificationBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Constants.TypeAction.ACTION_PREVIOUS:

                break;
            case Constants.TypeAction.ACTION_NEXT:

                break;
            case Constants.TypeAction.ACTION_PLAY:

                break;
            case Constants.TypeAction.ACTION_PAUSE:

                break;
        }
    }
}
