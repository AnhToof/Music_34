package com.framgia.music_34.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import com.framgia.music_34.data.model.Track;
import java.util.ArrayList;
import java.util.List;

public class GetDataLocal {
    private static final String PATH_DOWNLOADED = "SMusic";
    private Context mContext;

    public GetDataLocal(Context context) {
        mContext = context;
    }

    public List<Track> getData(boolean isGeneral) {
        List<Track> tracks = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String path = Environment.getExternalStorageDirectory().getPath();
        String selection;
        if (isGeneral) {
            selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        } else {
            selection = MediaStore.Audio.Media.IS_MUSIC
                    + " != 0 AND "
                    + MediaStore.Audio.Media.DATA
                    + " LIKE '"
                    + path
                    + "/"
                    + PATH_DOWNLOADED
                    + "/%'";
        }
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = mContext.getContentResolver().query(uri, null, selection, null, sortOrder);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String artist =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                long albumId =
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                String artworkUrl = getArtworkPath(albumId);
                String duration =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                tracks.add(new Track.TrackBuilder().title(title)
                        .duration(Integer.parseInt(duration))
                        .streamUrl(url)
                        .artworkUrl(artworkUrl)
                        .artist(artist)
                        .build());
            } while (cursor.moveToNext());
            cursor.close();
        }
        return tracks;
    }

    private String getArtworkPath(long albumId) {
        Cursor albumCursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Audio.Albums.ALBUM_ART },
                        MediaStore.Audio.Albums._ID + " = ?",
                        new String[] { Long.toString(albumId) }, null);
        boolean queryResult = false;
        String result = null;
        if (albumCursor != null) {
            queryResult = albumCursor.moveToFirst();

            if (queryResult) {
                result = albumCursor.getString(0);
            }
            albumCursor.close();
        }
        return result;
    }
}
