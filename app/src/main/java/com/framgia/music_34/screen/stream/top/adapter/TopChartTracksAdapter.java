package com.framgia.music_34.screen.stream.top.adapter;

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
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

public class TopChartTracksAdapter extends RecyclerView.Adapter<TopChartTracksAdapter.ViewHolder> {

    private List<Track> mTrackList;
    private Context mContext;
    private OnItemRecyclerViewClickListener<Track> mListener;

    TopChartTracksAdapter(Context context) {
        mContext = context;
        mTrackList = new ArrayList<>();
    }

    public void setOnItemRecyclerViewTrackListener(
            OnItemRecyclerViewClickListener<Track> listener) {
        mListener = listener;
    }

    public void setTrackList(List<Track> list) {
        List<Track> trackList = new ArrayList<>(list);
        mTrackList.clear();
        mTrackList.addAll(trackList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_stream_tracks_horizontal, viewGroup, false);
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
        private ImageView mImageViewThumb;
        private TextView mTextViewSongName;
        private TextView mTextViewArtist;
        private List<Track> mTrackList;
        private OnItemRecyclerViewClickListener<Track> mListener;

        ViewHolder(Context context, @NonNull View itemView, List<Track> trackList,
                OnItemRecyclerViewClickListener<Track> listener) {
            super(itemView);
            mContext = context;
            mTrackList = trackList;
            mListener = listener;

            mImageViewThumb = itemView.findViewById(R.id.image_thumb);
            mTextViewSongName = itemView.findViewById(R.id.text_song_name);
            mTextViewArtist = itemView.findViewById(R.id.text_artist);
            itemView.setOnClickListener(this);
        }

        void bindViewData(Track track) {
            mTextViewArtist.setText(track.getArtist());
            mTextViewSongName.setText(track.getTitle());
            getAlbumImage(track);
        }

        private void getAlbumImage(Track track) {
            if (track.getArtworkUrl() != null) {
                Glide.with(mContext).load(track.getArtworkUrl()).into(mImageViewThumb);
            } else {
                mImageViewThumb.setImageResource(R.drawable.ic_album_cover);
            }
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClickListener(mTrackList.get(getAdapterPosition()),
                        getAdapterPosition());
            }
        }
    }
}
