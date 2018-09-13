package com.framgia.music_34.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Track implements Parcelable {

    private int mId;
    private String mTitle;
    private String mUri;
    private String mArtworkUrl;
    private int mDuration;
    private boolean mDownloadable;
    private String mDownloadUrl;
    private String mStreamUrl;
    private String mArtist;

    public Track() {
    }

    Track(TrackBuilder trackBuilder) {
        mId = trackBuilder.mId;
        mTitle = trackBuilder.mTitle;
        mUri = trackBuilder.mUri;
        mArtworkUrl = trackBuilder.mArtworkUrl;
        mDuration = trackBuilder.mDuration;
        mDownloadable = trackBuilder.mDownloadable;
        mDownloadUrl = trackBuilder.mDownloadUrl;
        mStreamUrl = trackBuilder.mStreamUrl;
        mArtist = trackBuilder.mArtist;
    }

    protected Track(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mUri = in.readString();
        mArtworkUrl = in.readString();
        mDuration = in.readInt();
        mDownloadable = in.readByte() != 0;
        mDownloadUrl = in.readString();
        mStreamUrl = in.readString();
        mArtist = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        mArtworkUrl = artworkUrl;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public boolean isDownloadable() {
        return mDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        mDownloadable = downloadable;
    }

    public String getDownloadUrl() {
        return mDownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        mDownloadUrl = downloadUrl;
    }

    public String getStreamUrl() {
        return mStreamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        mStreamUrl = streamUrl;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mUri);
        parcel.writeString(mArtworkUrl);
        parcel.writeInt(mDuration);
        parcel.writeByte((byte) (mDownloadable ? 1 : 0));
        parcel.writeString(mDownloadUrl);
        parcel.writeString(mStreamUrl);
        parcel.writeString(mArtist);
    }

    public static class TrackBuilder {
        private int mId;
        private String mTitle;
        private String mUri;
        private String mArtworkUrl;
        private int mDuration;
        private boolean mDownloadable;
        private String mDownloadUrl;
        private String mStreamUrl;
        private String mArtist;

        public TrackBuilder() {
        }

        public TrackBuilder id(int id) {
            mId = id;
            return this;
        }

        public TrackBuilder title(String title) {
            mTitle = title;
            return this;
        }

        public TrackBuilder uri(String uri) {
            mUri = uri;
            return this;
        }

        public TrackBuilder artworkUrl(String artworkUrl) {
            mArtworkUrl = artworkUrl;
            return this;
        }

        public TrackBuilder duration(int duration) {
            mDuration = duration;
            return this;
        }

        public TrackBuilder downloadable(boolean downloadable) {
            mDownloadable = downloadable;
            return this;
        }

        public TrackBuilder downloadUrl(String downloadUrl) {
            mDownloadUrl = downloadUrl;
            return this;
        }

        public TrackBuilder streamUrl(String streamUrl) {
            mStreamUrl = streamUrl;
            return this;
        }

        public TrackBuilder artist(String artist) {
            mArtist = artist;
            return this;
        }

        public Track build() {
            return new Track(this);
        }
    }

    public final class TrackEntry {
        public static final String COLLECTION = "collection";
        public static final String TRACK = "track";
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String URI = "uri";
        public static final String ARTWORK_URL = "artwork_url";
        public static final String DURATION = "duration";
        public static final String DOWNLOADABLE = "downloadable";
        public static final String DOWNLOAD_URL = "download_url";
        public static final String STREAM_URL = "stream_url";
    }
}
