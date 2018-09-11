package com.framgia.music_34.data.source.remote;

import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackDataSource;
import com.framgia.music_34.data.source.remote.fetchjson.GetJsonFromUrl;
import com.framgia.music_34.data.source.remote.fetchjson.ParseDataWithJson;
import com.framgia.music_34.utils.Constants;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class TrackRemoteDataSource implements TrackDataSource.RemoteDataSource {

    private static TrackRemoteDataSource sInstance;

    public static TrackRemoteDataSource getsInstance() {
        if (sInstance == null) {
            sInstance = new TrackRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getListTrack(String genres, String kind,
            final OnFetchDataJsonListener<List<Track>> listener) {
        String url = Constants.BASE_URL + kind + Constants.CLIENT_ID + Constants.GENRES + genres;
        new GetJsonFromUrl(new OnFetchDataTemp() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    List<Track> trackList = new ParseDataWithJson().parseJsonToData(jsonObject);
                    listener.onSuccess(trackList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {
                listener.onError(e);
            }
        }).execute(url);
    }

    @Override
    public void getTrack(String uri, final OnFetchDataJsonListener<Track> listener) {
        new GetJsonFromUrl(new OnFetchDataTemp() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    listener.onSuccess(new ParseDataWithJson().parseJsonToObject(jsonObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {
                listener.onError(e);
            }
        }).execute(uri + Constants.CLIENT_ID);
    }
}
