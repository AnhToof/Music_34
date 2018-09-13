package com.framgia.music_34.screen.stream.tracks_dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.framgia.music_34.R;
import com.framgia.music_34.data.model.Genres;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;

public class TracksFullScreenDialog extends DialogFragment
        implements View.OnClickListener, OnItemRecyclerViewClickListener<Track> {
    public static final String TAG = TracksFullScreenDialog.class.getName();
    private static final String ARGUMENT_GENRES = "GET_GENRES";
    private Genres mGenres;
    private TracksFullScreenDialogAdapter mAdapter;

    public static TracksFullScreenDialog newInstance(Genres genres) {
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_GENRES, genres);
        TracksFullScreenDialog fragment = new TracksFullScreenDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getParcelable(ARGUMENT_GENRES) != null) {
            mGenres = bundle.getParcelable(ARGUMENT_GENRES);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_full_screen_tracks, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_genres_tracks);
        recyclerView.setHasFixedSize(true);
        mAdapter = new TracksFullScreenDialogAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setTrackList(mGenres.getTrackList());
        mAdapter.setOnItemRecyclerViewClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogProperties(getDialog());
    }

    private void setDialogProperties(Dialog dialogProperties) {
        if (dialogProperties != null) {
            dialogProperties.getWindow()
                    .setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
            dialogProperties.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }

    @Override
    public void onItemClickListener(Track item, int position) {
    }
}
