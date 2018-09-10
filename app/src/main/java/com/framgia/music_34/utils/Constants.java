package com.framgia.music_34.utils;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

public class Constants {

    @IntDef({ TypeMenuIndex.MENU_INDEX_HOT_NEW, TypeMenuIndex.MENU_INDEX_TOP })
    public @interface TypeMenuIndex {
        int MENU_INDEX_HOT_NEW = 0;
        int MENU_INDEX_TOP = 1;
    }

    @StringDef({ TypeMenu.MENU_HOT_NEW, TypeMenu.MENU_TOP })
    public @interface TypeMenu {
        String MENU_HOT_NEW = "HOT&NEW";
        String MENU_TOP = "TOP";
    }
}
