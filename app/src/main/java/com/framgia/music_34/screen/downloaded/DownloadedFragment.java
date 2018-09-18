package com.framgia.music_34.screen.downloaded;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_34.R;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackRepository;
import com.framgia.music_34.data.source.local.GetDataLocal;
import com.framgia.music_34.data.source.local.TrackLocalDataSource;
import com.framgia.music_34.data.source.remote.TrackRemoteDataSource;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadedFragment extends Fragment
        implements DownloadedContract.View, View.OnClickListener,
        OnItemRecyclerViewClickListener<Track> {

    private DownloadedAdapter mAdapter;

    public static DownloadedFragment newInstance() {
        return new DownloadedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_downloaded, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_downloaded);
        mAdapter = new DownloadedAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        TrackRepository repository =
                TrackRepository.getsInstance(TrackRemoteDataSource.getsInstance(),
                        TrackLocalDataSource.getsInstance(new GetDataLocal(getContext())));
        DownloadedContract.Presenter presenter = new DownloadedPresenter(repository);
        presenter.setView(this);
        presenter.getData();
    }

    @Override
    public void onClick(View view) {
        // TODO: 9/18/2018 update later 
    }

    @Override
    public void onGetDataSuccess(List<Track> trackList) {
        mAdapter.setTrackList(trackList);
    }

    @Override
    public void onItemClickListener(Track item, int position) {
        // TODO: 9/18/2018 update later 
    }
}
