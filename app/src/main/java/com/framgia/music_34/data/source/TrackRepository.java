package com.framgia.music_34.data.source;

import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.local.OnGetDataListener;
import com.framgia.music_34.data.source.remote.OnFetchDataJsonListener;
import java.util.List;

public class TrackRepository {

    private static TrackRepository sInstance;
    private TrackDataSource.RemoteDataSource mRemoteDataSource;
    private TrackDataSource.LocalDataSource mLocalDataSource;

    private TrackRepository(TrackDataSource.RemoteDataSource remoteDataSource,
            TrackDataSource.LocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static TrackRepository getsInstance(TrackDataSource.RemoteDataSource remoteDataSource,
            TrackDataSource.LocalDataSource localDataSource) {
        if (sInstance == null) {
            sInstance = new TrackRepository(remoteDataSource, localDataSource);
        }
        return sInstance;
    }

    public void getRemoteTrack(String uri, OnFetchDataJsonListener<Track> listener) {
        mRemoteDataSource.getTrack(uri, listener);
    }

    public void getRemoteListTrack(String genres, String kind,
            OnFetchDataJsonListener<List<Track>> listener) {
        mRemoteDataSource.getListTrack(genres, kind, listener);
    }

    public void getRemoteListTrackMore(String genres, String kind, String limit,
            OnFetchDataJsonListener<List<Track>> listOnFetchDataJsonListener) {
        mRemoteDataSource.getListTrackMore(genres, kind, limit, listOnFetchDataJsonListener);
    }

    public void getLocalListTrack(OnGetDataListener<Track> listener) {
        mLocalDataSource.getData(listener);
    }
}
