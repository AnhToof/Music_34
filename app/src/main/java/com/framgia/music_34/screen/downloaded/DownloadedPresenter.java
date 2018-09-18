package com.framgia.music_34.screen.downloaded;

import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackRepository;
import com.framgia.music_34.data.source.local.OnGetDataListener;
import java.util.List;

public class DownloadedPresenter implements DownloadedContract.Presenter {

    private DownloadedContract.View mView;
    private TrackRepository mRepository;

    DownloadedPresenter(TrackRepository repository) {
        mRepository = repository;
    }

    @Override
    public void getData() {
        mRepository.getLocalListTrack(false, new OnGetDataListener<Track>() {
            @Override
            public void onSuccess(List<Track> list) {
                mView.onGetDataSuccess(list);
            }

            @Override
            public void onError(String error) {
            }
        });
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void setView(DownloadedContract.View view) {
        mView = view;
    }
}
