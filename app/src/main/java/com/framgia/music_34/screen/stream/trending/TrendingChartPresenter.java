package com.framgia.music_34.screen.stream.trending;

import com.framgia.music_34.data.model.Genres;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackRepository;
import com.framgia.music_34.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_34.screen.main.MainActivity;
import com.framgia.music_34.utils.Constants;
import java.util.List;

public class TrendingChartPresenter implements TrendingChartContract.Presenter {
    private TrendingChartContract.View mView;
    private TrackRepository mRepository;

    TrendingChartPresenter(TrackRepository repository) {
        mRepository = repository;
    }

    @Override
    public void getListTrack() {
        List<String> titleGenres = MainActivity.getTitleGenres();
        List<String> genresPath = MainActivity.getPathGenres();
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

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void setView(TrendingChartContract.View view) {
        mView = view;
    }
}
