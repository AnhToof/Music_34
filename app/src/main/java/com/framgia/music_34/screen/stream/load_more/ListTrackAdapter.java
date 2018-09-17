package com.framgia.music_34.screen.stream.load_more;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.framgia.music_34.R;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

public class ListTrackAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int DEFAULT_SIZE = 0;
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_LOADING = 1;
    private List<Track> mTrackList;
    private Context mContext;
    private OnItemRecyclerViewClickListener<Track> mListener;
    private boolean mIsLoading;

    ListTrackAdapter(Context context) {
        mContext = context;
        mTrackList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return mTrackList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void addItem(Track track) {
        mTrackList.add(track);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mTrackList.remove(position);
        notifyDataSetChanged();
    }

    public void setOnItemRecyclerViewClickListener(
            OnItemRecyclerViewClickListener<Track> listener) {
        mListener = listener;
    }

    public void setTrackList(List<Track> trackList) {
        mTrackList.clear();
        mTrackList.addAll(trackList);
        notifyDataSetChanged();
    }

    public void setLoaded(boolean isLoading) {
        mIsLoading = isLoading;
    }

    public boolean getLoaded() {
        return mIsLoading;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        if (position == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_stream_tracks_horizontal, viewGroup, false);
            return new ViewHolder(mContext, view, mTrackList, mListener);
        } else if (position == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_loading, viewGroup, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).bindViewData(mTrackList.get(position));
        }

        if (viewHolder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewHolder;
            loadingViewHolder.mProgressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mTrackList != null ? mTrackList.size() : DEFAULT_SIZE;
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

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar mProgressBar;

        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
