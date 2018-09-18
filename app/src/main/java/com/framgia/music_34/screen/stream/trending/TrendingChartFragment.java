package com.framgia.music_34.screen.stream.trending;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.framgia.music_34.screen.stream.adapter.ChartGenresAdapter;
import com.framgia.music_34.screen.stream.load_more.ListTrackDialogFragment;
import com.framgia.music_34.utils.Navigator;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingChartFragment extends Fragment
        implements TrendingChartContract.View, OnItemRecyclerViewClickListener {

    private ChartGenresAdapter mAdapter;
    private Navigator mNavigator;

    public static TrendingChartFragment newInstance() {
        return new TrendingChartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mNavigator = new Navigator();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_genres);
        recyclerView.setHasFixedSize(true);
        mAdapter = new ChartGenresAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemRecyclerViewClickListener(this);
        mAdapter.setListenerTrack(this);
    }

    private void initData() {
        TrackRepository repository =
                TrackRepository.getsInstance(TrackRemoteDataSource.getsInstance(),
                        TrackLocalDataSource.getsInstance(new GetDataLocal(getContext())));
        TrendingChartContract.Presenter presenter = new TrendingChartPresenter(repository);
        presenter.setView(this);
        presenter.getListTrack();
    }

    @Override
    public void onFetchAllListGenresSuccess(Genres genres) {
        mAdapter.addGenres(genres);
    }

    @Override
    public void onFetchTrackSuccess(Track track) {
        // TODO: 9/18/2018 Update later
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClickListener(Object item, int position) {
        if (item instanceof Track) {
            // TODO: 9/18/2018 Update later
        }
        if (item instanceof Genres) {
            if (getFragmentManager() != null) {
                mNavigator.showListTrackDialog(getFragmentManager(),
                        ListTrackDialogFragment.newInstance((Genres) item));
            }
        }
    }
}
