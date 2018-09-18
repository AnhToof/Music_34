package com.framgia.music_34.screen.local;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.music_34.R;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackRepository;
import com.framgia.music_34.data.source.local.GetDataLocal;
import com.framgia.music_34.data.source.local.TrackLocalDataSource;
import com.framgia.music_34.data.source.remote.TrackRemoteDataSource;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalMusicFragment extends Fragment
        implements LocalMusicContract.View, View.OnClickListener,
        OnItemRecyclerViewClickListener<Track> {
    public static final int MY_REQUEST_CODE_READ_STORAGE = 123;
    private static final int SDK_INT_REQUIRED = 23;
    private LocalMusicAdapter mAdapter;

    public static LocalMusicFragment newInstance() {
        return new LocalMusicFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_music, container, false);
        initView(view);
        checkPermission();
        return view;
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= SDK_INT_REQUIRED) {
            if (getActivity() != null) {
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                            MY_REQUEST_CODE_READ_STORAGE);
                }
            }else {
                initData();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_REQUEST_CODE_READ_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), R.string.permission_granted, Toast.LENGTH_LONG)
                            .show();
                    initData();
                } else {
                    Toast.makeText(getContext(), R.string.permission_denied, Toast.LENGTH_LONG)
                            .show();
                }
                return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initView(View view) {
        ConstraintLayout constraintShuffleAll = view.findViewById(R.id.constraint_shuffle_all);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_local_tracks);
        recyclerView.setHasFixedSize(true);
        mAdapter = new LocalMusicAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemRecyclerViewTrackListener(this);
        constraintShuffleAll.setOnClickListener(this);
    }

    private void initData() {
        TrackRepository repository =
                TrackRepository.getsInstance(TrackRemoteDataSource.getsInstance(),
                        TrackLocalDataSource.getsInstance(new GetDataLocal(getContext())));
        LocalMusicContract.Presenter presenter = new LocalMusicPresenter(repository);
        presenter.setView(this);
        presenter.getData();
    }

    @Override
    public void onGetDataSuccess(List<Track> trackList) {
        mAdapter.setTrackList(trackList);
    }

    @Override
    public void onClick(View view) {
        // TODO: 9/18/2018 Update later
    }

    @Override
    public void onItemClickListener(Track item, int position) {
        // TODO: 9/18/2018 Update later
    }
}
