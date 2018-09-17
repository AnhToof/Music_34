package com.framgia.music_34.screen.stream.load_more;

import com.framgia.music_34.data.model.Genres;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackRepository;
import com.framgia.music_34.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_34.utils.Constants;
import java.util.List;

public class ListTrackPresenter implements ListTrackContract.Presenter {

    private ListTrackContract.View mView;
    private TrackRepository mRepository;

    ListTrackPresenter(TrackRepository repository) {
        mRepository = repository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void setView(ListTrackContract.View view) {
        mView = view;
    }

    @Override
    public void getListTrack(final Genres genres, int limit) {
        mRepository.getRemoteListTrack(genres.getGenres(), Constants.KIND_TOP,
                Constants.LIMIT + limit, new OnFetchDataJsonListener<List<Track>>() {
                    @Override
                    public void onSuccess(List<Track> trackList) {
                        genres.setTrackList(trackList);
                        mView.onGetListTracksSuccess(genres);
                    }

                    @Override
                    public void onError(Exception e) {
                        mView.onError(e);
                    }
                });
    }
}
