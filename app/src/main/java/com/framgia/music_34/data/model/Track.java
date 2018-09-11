package com.framgia.music_34.data.model;

public class Track {

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

        public TrackBuilder(String title, String url, int duration, String artist) {
            mTitle = title;
            mDuration = duration;
            mArtist = artist;
            mStreamUrl = url;
        }

        public TrackBuilder(int id, String title, String uri, String artworkUrl, int duration,
                boolean downloadable, String downloadUrl, String streamUrl) {
            mId = id;
            mTitle = title;
            mUri = uri;
            mArtworkUrl = artworkUrl;
            mDuration = duration;
            mDownloadable = downloadable;
            mDownloadUrl = downloadUrl;
            mStreamUrl = streamUrl;
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
        public static final String TRACK = "collection";
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
