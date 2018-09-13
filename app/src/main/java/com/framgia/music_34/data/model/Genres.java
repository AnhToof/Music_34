package com.framgia.music_34.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Genres implements Parcelable {
    private String mTitle;
    private String mGenres;
    private List<Track> mTrackList;

    public Genres(){
    }

    public Genres(String title, List<Track> trackList, String genres) {
        mTitle = title;
        mTrackList = trackList;
        mGenres = genres;
    }

    private Genres(Parcel in) {
        mTitle = in.readString();
        mGenres = in.readString();
        mTrackList = in.createTypedArrayList(Track.CREATOR);
    }

    public static final Creator<Genres> CREATOR = new Creator<Genres>() {
        @Override
        public Genres createFromParcel(Parcel in) {
            return new Genres(in);
        }

        @Override
        public Genres[] newArray(int size) {
            return new Genres[size];
        }
    };

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

    public String getGenres() {
        return mGenres;
    }

    public void setGenres(String genres) {
        mGenres = genres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mGenres);
        parcel.writeTypedList(mTrackList);
    }

    public static class GenresEntry {
        public static final String ALL_MUSIC = "All Music";
        public static final String ALL_AUDIO = "All Audio";
        public static final String ALTERNATIVE_ROCK = "Alternative Rock";
        public static final String AMBIENT = "Ambient";
        public static final String CLASSICAL = "Classical";
        public static final String COUNTRY = "Country";
    }
}
