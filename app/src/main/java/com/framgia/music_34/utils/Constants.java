package com.framgia.music_34.utils;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import com.framgia.music_34.BuildConfig;

public class Constants {

    public static final String BASE_URL = "https://api-v2.soundcloud.com/charts?";
    public static final String KIND_TOP = "top/";
    public static final String KIND_TRENDING = "trending";
    public static final String GENRES = "&genres=";
    public static final String CLIENT_ID = "?client_id=" + BuildConfig.API_KEY;

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
