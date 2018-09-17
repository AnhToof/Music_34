package com.framgia.music_34.screen.stream.top.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.framgia.music_34.R;
import com.framgia.music_34.data.model.Genres;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

public class TopChartGenresAdapter extends RecyclerView.Adapter<TopChartGenresAdapter.ViewHolder> {

    private static final int MAX_ITEMS_SHOWED = 10;

    private Context mContext;
    private List<Genres> mGenresList;
    private OnItemRecyclerViewClickListener<Genres> mListenerGenres;
    private OnItemRecyclerViewClickListener<Track> mListenerTrack;

    public TopChartGenresAdapter(Context context) {
        mContext = context;
        mGenresList = new ArrayList<>();
    }

    public void setOnItemRecyclerViewClickListener(
            OnItemRecyclerViewClickListener<Genres> listener) {
        mListenerGenres = listener;
    }

    public void setListenerTrack(OnItemRecyclerViewClickListener<Track> listenerTrack) {
        mListenerTrack = listenerTrack;
    }

    public void addGenres(Genres genres) {
        mGenresList.add(genres);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_stream_genres, viewGroup, false);
        return new ViewHolder(mContext, view, mGenresList, mListenerGenres, mListenerTrack);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindViewData(mGenresList.get(i));
    }

    @Override
    public int getItemCount() {
        return mGenresList != null ? mGenresList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, OnItemRecyclerViewClickListener {

        private Context mContext;
        private List<Genres> mGenresList;
        private ConstraintLayout mConstraintLayoutMore;
        private TextView mTextViewGenres;
        private RecyclerView mRecyclerView;
        private OnItemRecyclerViewClickListener<Genres> mListenerGenres;
        private OnItemRecyclerViewClickListener<Track> mListenerTrack;

        ViewHolder(Context context, @NonNull View itemView, List<Genres> genresList,
                OnItemRecyclerViewClickListener<Genres> listenerGenres,
                OnItemRecyclerViewClickListener<Track> listenerTrack) {
            super(itemView);
            mContext = context;
            mGenresList = genresList;
            mListenerGenres = listenerGenres;
            mListenerTrack = listenerTrack;

            mConstraintLayoutMore = itemView.findViewById(R.id.constraint_more);
            mTextViewGenres = itemView.findViewById(R.id.text_genres);
            mRecyclerView = itemView.findViewById(R.id.recycler_genres_tracks);
            mConstraintLayoutMore.setOnClickListener(this);
        }

        private List<Track> setLimitTrack(Genres genres) {
            List<Track> trackList = new ArrayList<>();
            if (genres.getTrackList().size() < MAX_ITEMS_SHOWED) {
                trackList.addAll(genres.getTrackList());
            } else {
                for (int i = 0; i < MAX_ITEMS_SHOWED; i++) {
                    trackList.add(i, genres.getTrackList().get(i));
                }
            }
            return trackList;
        }

        void bindViewData(Genres genres) {
            mTextViewGenres.setText(genres.getTitle());
            TopChartTracksAdapter topChartTracksAdapter = new TopChartTracksAdapter(mContext);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(topChartTracksAdapter);
            topChartTracksAdapter.setTrackList(setLimitTrack(genres));
            topChartTracksAdapter.setOnItemRecyclerViewTrackListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListenerGenres != null) {
                mListenerGenres.onItemClickListener(mGenresList.get(getAdapterPosition()),
                        getAdapterPosition());
            }
        }

        @Override
        public void onItemClickListener(Object item, int position) {
            if (mListenerTrack != null) {
                mListenerTrack.onItemClickListener((Track) item, position);
            }
        }
    }
}
