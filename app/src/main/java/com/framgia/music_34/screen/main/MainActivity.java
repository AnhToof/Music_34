package com.framgia.music_34.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.framgia.music_34.R;
import com.framgia.music_34.screen.downloaded.DownloadedFragment;
import com.framgia.music_34.screen.local.LocalMusicFragment;
import com.framgia.music_34.screen.stream.StreamMusicFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ImageView mImagePlayCollapse;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initVIewBottomSheet();
        setFragment(StreamMusicFragment.newInstance());
        mNavigationView.getMenu().getItem(0).setChecked(true);
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initVIewBottomSheet() {
        mImagePlayCollapse = findViewById(R.id.image_play_col);
        ConstraintLayout constraintLayoutBottomSheet = findViewById(R.id.constraint_bottom_sheet);

        BottomSheetBehavior bottomSheetBehavior =
                BottomSheetBehavior.from(constraintLayoutBottomSheet);
        bottomSheetBehavior.setPeekHeight(150);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        mImagePlayCollapse.setVisibility(View.GONE);
                        break;
                    }
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        mImagePlayCollapse.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_stream_music:
                setFragment(StreamMusicFragment.newInstance());
                break;
            case R.id.nav_local_music:
                setFragment(LocalMusicFragment.newInstance());
                break;
            case R.id.nav_downloaded:
                setFragment(DownloadedFragment.newInstance());
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_content, fragment);
            fragmentTransaction.commit();
        }
    }
}
