package com.framgia.music_34.data.source;

import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.local.OnGetDataListener;
import com.framgia.music_34.data.source.remote.OnFetchDataJsonListener;
import java.util.List;

public interface TrackDataSource {

    interface LocalDataSource {
        void getData(OnGetDataListener<Track> listener);
    }

    interface RemoteDataSource {

        void getListTrack(String genres, String kind,
                OnFetchDataJsonListener<List<Track>> listener);

        void getTrack(String uri, OnFetchDataJsonListener<Track> listener);
    }
}
