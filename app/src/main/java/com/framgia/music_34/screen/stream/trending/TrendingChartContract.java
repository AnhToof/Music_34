package com.framgia.music_34.screen.stream.trending;

import com.framgia.music_34.data.model.Genres;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.utils.BasePresenter;

public interface TrendingChartContract {

    interface View {
        void onFetchAllListGenresSuccess(Genres genres);

        void onFetchTrackSuccess(Track track);

        void onError(Exception e);
    }

    interface Presenter extends BasePresenter<View> {
        void getListTrack();

        void getRemoteTrack(String uri);
    }
}
