package com.framgia.music_34.screen.stream.hotnew;

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
public class HotNewFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public static HotNewFragment newInstance() {
        return new HotNewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_new, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_genres);
    }
}
