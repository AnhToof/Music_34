package com.framgia.music_34.utils;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.framgia.music_34.screen.stream.load_more.ListTrackDialogFragment;

public class Navigator {
    public void showListTrackDialog(FragmentManager fragmentManager,
            DialogFragment dialogFragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        dialogFragment.show(fragmentTransaction, ListTrackDialogFragment.TAG);
    }
}
