package com.framgia.music_34.data.model;

import java.util.List;

public class Genres {
    private String mTitle;
    private List<Track> mTrackList;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<Track> getTrackList() {
        return mTrackList;
    }

    public void setTrackList(List<Track> trackList) {
        mTrackList = trackList;
    }
}
