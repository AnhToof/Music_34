package com.framgia.music_34.screen.stream.trending;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_34.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingChartFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public static TrendingChartFragment newInstance() {
        return new TrendingChartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_genres);
    }
}
