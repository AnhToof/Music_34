package com.framgia.music_34.screen.downloaded;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.framgia.music_34.R;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.utils.Constants;
import com.framgia.music_34.utils.DateUtils;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

public class DownloadedAdapter extends RecyclerView.Adapter<DownloadedAdapter.ViewHolder> {

    private Context mContext;
    private List<Track> mTrackList;
    private OnItemRecyclerViewClickListener<Track> mListener;

    DownloadedAdapter(Context context) {
        mContext = context;
        mTrackList = new ArrayList<>();
    }

    public void setOnItemRecyclerViewTrackListener(
            OnItemRecyclerViewClickListener<Track> listener) {
        mListener = listener;
    }

    public void setTrackList(List<Track> trackList) {
        mTrackList.clear();
        mTrackList.addAll(trackList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_downloaded_track, viewGroup, false);
        return new ViewHolder(mContext, view, mTrackList, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindViewData(mTrackList.get(i));
    }

    @Override
    public int getItemCount() {
        return mTrackList != null ? mTrackList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context mContext;
        private List<Track> mTrackList;
        private OnItemRecyclerViewClickListener<Track> mListener;
        private ImageView mImageViewThumb;
        private ImageView mImageViewOption;
        private TextView mTextViewSongName;
        private TextView mTextViewArtist;
        private TextView mTextViewDuration;

        ViewHolder(Context context, @NonNull View itemView, List<Track> trackList,
                OnItemRecyclerViewClickListener<Track> listener) {
            super(itemView);
            mContext = context;
            mTrackList = trackList;
            mListener = listener;

            mImageViewThumb = itemView.findViewById(R.id.image_album);
            mImageViewOption = itemView.findViewById(R.id.image_more_option);
            mTextViewSongName = itemView.findViewById(R.id.text_song_name);
            mTextViewArtist = itemView.findViewById(R.id.text_artist);
            mTextViewDuration = itemView.findViewById(R.id.text_duration);
            itemView.setOnClickListener(this);
            mImageViewOption.setOnClickListener(this);
        }

        void bindViewData(Track track) {
            if (track.getArtworkUrl() != null) {
                Glide.with(mContext).load(track.getArtworkUrl()).into(mImageViewThumb);
            } else {
                mImageViewThumb.setImageResource(R.drawable.ic_album_cover);
            }
            mTextViewSongName.setText(track.getTitle());
            mTextViewArtist.setText(track.getArtist() == null ? mContext.getString(R.string.unknown)
                    : track.getArtist());
            mTextViewDuration.setText(
                    DateUtils.formatDate(track.getDuration(), Constants.FORMAT_MM_SS));
        }

        @Override
        public void onClick(View view) {
            // TODO: 9/18/2018 update later
        }
    }
}
