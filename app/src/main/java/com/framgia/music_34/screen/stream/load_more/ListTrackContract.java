package com.framgia.music_34.screen.stream.load_more;

import com.framgia.music_34.data.model.Genres;
import com.framgia.music_34.utils.BasePresenter;

public interface ListTrackContract {

    interface View {
        void onGetListTracksSuccess(Genres genres);

        void onError(Exception e);
    }

    interface Presenter extends BasePresenter<View> {
        void getListTrack(Genres genres, int limit);
    }
}
