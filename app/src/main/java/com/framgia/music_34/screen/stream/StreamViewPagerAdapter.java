package com.framgia.music_34.screen.stream;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.framgia.music_34.screen.stream.hotnew.HotNewFragment;
import com.framgia.music_34.screen.stream.top.TopFragment;
import com.framgia.music_34.utils.Constants;

public class StreamViewPagerAdapter extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 2;

    StreamViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Constants.TypeMenuIndex.MENU_INDEX_HOT_NEW:
                return HotNewFragment.newInstance();
            case Constants.TypeMenuIndex.MENU_INDEX_TOP:
                return TopFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case Constants.TypeMenuIndex.MENU_INDEX_HOT_NEW:
                return Constants.TypeMenu.MENU_HOT_NEW;
            case Constants.TypeMenuIndex.MENU_INDEX_TOP:
                return Constants.TypeMenu.MENU_TOP;
        }
        return "";
    }
}
