package com.framgia.music_34.screen.local;

import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackRepository;
import com.framgia.music_34.data.source.local.OnGetDataListener;
import java.util.List;

public class LocalMusicPresenter implements LocalMusicContract.Presenter {

    private LocalMusicContract.View mView;
    private TrackRepository mRepository;

    LocalMusicPresenter(TrackRepository repository) {
        mRepository = repository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void setView(LocalMusicContract.View view) {
        mView = view;
    }

    @Override
    public void getData() {
        mRepository.getLocalListTrack(true, new OnGetDataListener<Track>() {
            @Override
            public void onSuccess(List<Track> list) {
                mView.onGetDataSuccess(list);
            }

            @Override
            public void onError(String error) {
            }
        });
    }
}
