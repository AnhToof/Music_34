package com.framgia.music_34.screen.local;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_34.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalMusicFragment extends Fragment {

    private ConstraintLayout mConstraintShuffleAll;
    private RecyclerView mRecyclerView;

    public static LocalMusicFragment newInstance() {
        return new LocalMusicFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_music, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mConstraintShuffleAll = view.findViewById(R.id.constraint_shuffle_all);
        mRecyclerView = view.findViewById(R.id.recycler_local_tracks);
    }
}
