package com.framgia.music_34.screen.stream.top;

import com.framgia.music_34.data.model.Genres;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackRepository;
import com.framgia.music_34.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_34.utils.Constants;
import com.framgia.music_34.utils.GenresMusic;
import java.util.ArrayList;
import java.util.List;

public class TopChartPresenter implements TopChartContract.Presenter {
    private TopChartContract.View mView;
    private TrackRepository mRepository;
    private List<String> mGenresPath;
    private List<String> mTitleGenres;

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

    private void addTitleGenres() {
        mTitleGenres = new ArrayList<>();
        mTitleGenres.add(Genres.GenresEntry.ALL_MUSIC);
        mTitleGenres.add(Genres.GenresEntry.ALL_AUDIO);
        mTitleGenres.add(Genres.GenresEntry.ALTERNATIVE_ROCK);
        mTitleGenres.add(Genres.GenresEntry.AMBIENT);
        mTitleGenres.add(Genres.GenresEntry.CLASSICAL);
        mTitleGenres.add(Genres.GenresEntry.COUNTRY);
    }

    private void addPathGenres() {
        mGenresPath = new ArrayList<>();
        mGenresPath.add(GenresMusic.ALL_MUSIC);
        mGenresPath.add(GenresMusic.ALL_AUDIO);
        mGenresPath.add(GenresMusic.ALTERNATIVE_ROCK);
        mGenresPath.add(GenresMusic.AMBIENT);
        mGenresPath.add(GenresMusic.CLASSICAL);
        mGenresPath.add(GenresMusic.COUNTRY);
    }

    @Override
    public void getListTrack() {
        addPathGenres();
        addTitleGenres();
        for (int i = 0; i < mTitleGenres.size(); i++) {
            final String title = mTitleGenres.get(i);
            final String genresParam = mGenresPath.get(i);
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
