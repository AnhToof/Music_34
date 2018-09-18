package com.framgia.music_34.screen.stream.top;

import com.framgia.music_34.data.model.Genres;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackRepository;
import com.framgia.music_34.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_34.screen.main.MainActivity;
import com.framgia.music_34.utils.Constants;
import com.framgia.music_34.utils.GenresMusic;
import java.util.ArrayList;
import java.util.List;

public class TopChartPresenter implements TopChartContract.Presenter {
    private TopChartContract.View mView;
    private TrackRepository mRepository;

    TopChartPresenter(TrackRepository repository) {
        mRepository = repository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void setView(TopChartContract.View view) {
        mView = view;
    }

    @Override
    public void getListTrack() {
        List<String> genresPath = MainActivity.getPathGenres();
        List<String> titleGenres = MainActivity.getTitleGenres();
        for (int i = 0; i < titleGenres.size(); i++) {
            final String title = titleGenres.get(i);
            final String genresParam = genresPath.get(i);
            mRepository.getRemoteListTrack(genresParam, Constants.KIND_TOP, "",
                    new OnFetchDataJsonListener<List<Track>>() {
                        @Override
                        public void onSuccess(List<Track> data) {
                            mView.onFetchAllListGenresSuccess(new Genres(title, data, genresParam));
                        }

                        @Override
                        public void onError(Exception e) {
                            mView.onError(e);
                        }
                    });
        }
    }

    @Override
    public void getRemoteTrack(String uri) {
        mRepository.getRemoteTrack(uri, new OnFetchDataJsonListener<Track>() {
            @Override
            public void onSuccess(Track data) {
                mView.onFetchTrackSuccess(data);
            }

            @Override
            public void onError(Exception e) {
                mView.onError(e);
            }
        });
    }
}
