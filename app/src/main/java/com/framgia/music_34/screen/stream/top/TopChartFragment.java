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
import com.framgia.music_34.screen.stream.load_more.ListTrackDialogFragment;
import com.framgia.music_34.screen.stream.top.adapter.TopChartGenresAdapter;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopChartFragment extends Fragment
        implements TopChartContract.View, OnItemRecyclerViewClickListener {

    private TopChartGenresAdapter mAdapter;

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
        RecyclerView recyclerView = view.findViewById(R.id.recycler_genres);
        recyclerView.setHasFixedSize(true);
        mAdapter = new TopChartGenresAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemRecyclerViewClickListener(this);
        mAdapter.setListenerTrack(this);
    }

    private void initData() {
        TrackRepository repository =
                TrackRepository.getsInstance(TrackRemoteDataSource.getsInstance(),
                        TrackLocalDataSource.getsInstance(new GetDataLocal(getContext())));
        TopChartContract.Presenter presenter = new TopChartPresenter(repository);
        presenter.setView(this);
        presenter.getListTrack();
    }

    @Override
    public void onFetchAllListGenresSuccess(Genres genres) {
        mAdapter.addGenres(genres);
    }

    @Override
    public void onFetchTrackSuccess(Track track) {

    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClickListener(Object item, int position) {
        if (item instanceof Track) {
            Toast.makeText(getContext(), ((Track) item).getTitle(), Toast.LENGTH_LONG).show();
        }
        if (item instanceof Genres) {
            if (getFragmentManager() != null) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                ListTrackDialogFragment mDialog = ListTrackDialogFragment.newInstance((Genres) item);
                mDialog.show(fragmentTransaction, ListTrackDialogFragment.TAG);
            }
        }
    }
}
