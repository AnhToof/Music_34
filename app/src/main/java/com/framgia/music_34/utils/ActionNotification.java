package com.framgia.music_34.utils;

        import android.support.annotation.StringDef;

@StringDef({
        ActionNotification.ACTION_PLAY, ActionNotification.ACTION_PAUSE, ActionNotification.ACTION_NEXT,
        ActionNotification.ACTION_PREVIOUS
})
public @interface ActionNotification {
    String ACTION_PREVIOUS = "com.framgia.music_34.previous";
    String ACTION_NEXT = "com.framgia.music_34.next";
    String ACTION_PLAY = "com.framgia.music_34.play";
    String ACTION_PAUSE = "com.framgia.music_34.pause";
}
