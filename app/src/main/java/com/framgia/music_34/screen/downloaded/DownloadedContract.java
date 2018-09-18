package com.framgia.music_34.screen.downloaded;

import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.utils.BasePresenter;
import java.util.List;

public interface DownloadedContract {

    interface View {
        void onGetDataSuccess(List<Track> trackList);
    }

    interface Presenter extends BasePresenter<View> {
        void getData();
    }
}
