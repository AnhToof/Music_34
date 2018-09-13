package com.framgia.music_34.screen.stream.top;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.music_34.R;
import com.framgia.music_34.data.model.Genres;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackRepository;
import com.framgia.music_34.data.source.local.GetDataLocal;
import com.framgia.music_34.data.source.local.TrackLocalDataSource;
import com.framgia.music_34.data.source.remote.TrackRemoteDataSource;
import com.framgia.music_34.screen.stream.top.adapter.TopChartGenresAdapter;
import com.framgia.music_34.screen.stream.tracks_dialog.TracksFullScreenDialog;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListenerSub;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopChartFragment extends Fragment
        implements TopChartContract.View, OnItemRecyclerViewClickListener<Genres>,
        OnItemRecyclerViewClickListenerSub<Track> {

    public static final String TAG = TopChartFragment.class.getName();
    private RecyclerView mRecyclerView;
    private TopChartGenresAdapter mAdapter;
    private TrackRepository mRepository;
    private TopChartContract.Presenter mPresenter;
    private TracksFullScreenDialog mDialog;

    public static TopChartFragment newInstance() {
        return new TopChartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_chart, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_genres);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new TopChartGenresAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemRecyclerViewClickListener(this);
        mAdapter.setOnItemRecyclerViewClickListenerSub(this);
    }

    private void initData() {
        mRepository = TrackRepository.getsInstance(TrackRemoteDataSource.getsInstance(),
                TrackLocalDataSource.getsInstance(new GetDataLocal(getContext())));
        mPresenter = new TopChartPresenter(mRepository);
        mPresenter.setView(this);
        mPresenter.getRemoteListTrack();
    }

    @Override
    public void onItemClickListener(Genres item, int position) {
        mPresenter.getRemoteListTrackFull(item);
    }

    @Override
    public void onItemClickListenerSub(Track item, int position) {
        Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFetchAllListGenresSuccess(Genres genres) {
        mAdapter.addGenres(genres);
    }

    @Override
    public void onFetchListGenresSuccess(Genres genres) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        mDialog = TracksFullScreenDialog.newInstance(genres);
        mDialog.show(fragmentTransaction, TracksFullScreenDialog.TAG);
    }

    @Override
    public void onFetchTrackSuccess(Track track) {

    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }
}
