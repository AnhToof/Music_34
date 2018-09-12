package com.framgia.music_34.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.framgia.music_34.data.model.Track;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GetDataLocal {
    private Context mContext;

    public GetDataLocal(Context context) {
        mContext = context;
    }

    public List<Track> getData() {
        List<Track> tracks = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String artist =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String duration =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                tracks.add(new Track.TrackBuilder().title(title)
                        .duration(Integer.parseInt(duration))
                        .streamUrl(url)
                        .artist(artist)
                        .build());
            } while (cursor.moveToNext());
            cursor.close();
        }
        Collections.sort(tracks, new Comparator<Track>() {
            @Override
            public int compare(Track track, Track t1) {
                return track.getTitle().compareTo(t1.getTitle());
            }
        });
        return tracks;
    }
}
