package com.framgia.music_34.data.source.local;

import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackDataSource;

public class TrackLocalDataSource implements TrackDataSource.LocalDataSource {

    private static TrackLocalDataSource sInstance;
    private GetDataLocal mGetDataLocal;

    public static TrackLocalDataSource getsInstance(GetDataLocal getDataLocal) {
        if (sInstance == null) {
            sInstance = new TrackLocalDataSource(getDataLocal);
        }
        return sInstance;
    }

    private TrackLocalDataSource(GetDataLocal getDataLocal) {
        mGetDataLocal = getDataLocal;
    }

    @Override
    public void getData(boolean isGeneral, OnGetDataListener<Track> listener) {
        listener.onSuccess(mGetDataLocal.getData(isGeneral));
    }
}
