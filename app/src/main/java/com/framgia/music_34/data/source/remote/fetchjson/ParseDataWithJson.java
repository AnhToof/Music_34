package com.framgia.music_34.data.source.remote.fetchjson;

import com.framgia.music_34.data.model.Track;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseDataWithJson {

    private static final int TIME_OUT = 15000;
    private static final String METHOD_GET = "GET";

    public String getJsonFromUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(TIME_OUT);
        httpURLConnection.setReadTimeout(TIME_OUT);
        httpURLConnection.setRequestMethod(METHOD_GET);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        httpURLConnection.disconnect();
        return stringBuilder.toString();
    }

    public List<Track> parseJsonToData(JSONObject jsonObject) {
        List<Track> trackList = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(Track.TrackEntry.TRACK);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject trackJson = jsonArray.getJSONObject(i);
                Track track = parseJsonToObject(trackJson);
                trackList.add(track);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trackList;
    }

    public Track parseJsonToObject(JSONObject jsonObject) {
        Track track = new Track();
        try {
            track = new Track.TrackBuilder().artworkUrl(
                    jsonObject.getString(Track.TrackEntry.ARTWORK_URL))
                    .downloadable(jsonObject.getBoolean(Track.TrackEntry.DOWNLOADABLE))
                    .downloadUrl(jsonObject.getString(Track.TrackEntry.DOWNLOAD_URL))
                    .duration(jsonObject.getInt(Track.TrackEntry.DURATION))
                    .id(jsonObject.getInt(Track.TrackEntry.ID))
                    .title(jsonObject.getString(Track.TrackEntry.TITLE))
                    .uri(jsonObject.getString(Track.TrackEntry.URI))
                    .streamUrl(jsonObject.getString(Track.TrackEntry.STREAM_URL))
                    .build();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return track;
    }
}
