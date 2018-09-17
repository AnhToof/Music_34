package com.framgia.music_34.screen.stream.load_more;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;
import com.framgia.music_34.R;
import com.framgia.music_34.data.model.Genres;
import com.framgia.music_34.data.model.Track;
import com.framgia.music_34.data.source.TrackRepository;
import com.framgia.music_34.data.source.local.GetDataLocal;
import com.framgia.music_34.data.source.local.TrackLocalDataSource;
import com.framgia.music_34.data.source.remote.TrackRemoteDataSource;
import com.framgia.music_34.utils.OnItemRecyclerViewClickListener;

public class ListTrackDialogFragment extends DialogFragment
        implements ListTrackContract.View, View.OnClickListener,
        OnItemRecyclerViewClickListener<Track> {

    public static final String TAG = ListTrackDialogFragment.class.getName();
    private static final int INCREASE_NUMBER = 10;
    private static final int VISIBLE_THRESHOLD = 10;
    private static final int DEFAULT_SIZE = 0;
    private static final int MAX_LOAD = 50;
    private static final String ARGUMENT_GENRES = "GET_GENRES";

    private Genres mGenres;
    private ListTrackAdapter mAdapter;
    private int mOldSize;
    private GridLayoutManager mGridLayoutManager;
    private ListTrackContract.Presenter mPresenter;

    public static ListTrackDialogFragment newInstance(Genres genres) {
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_GENRES, genres);
        ListTrackDialogFragment fragment = new ListTrackDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getParcelable(ARGUMENT_GENRES) != null) {
            mGenres = bundle.getParcelable(ARGUMENT_GENRES);
            if (mGenres != null) {
                mOldSize = mGenres.getTrackList().size();
            }
        }
    }

    private void setWindowLayoutDialog() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            if (dialog.getWindow() != null) {
                dialog.getWindow()
                        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        setWindowLayoutDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        View view = inflater.inflate(R.layout.dialog_fragment_load_more, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(this);
        toolbar.setTitle(mGenres.getTitle());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_genres_tracks);
        recyclerView.setHasFixedSize(true);
        mAdapter = new ListTrackAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setTrackList(mGenres.getTrackList());
        mAdapter.setOnItemRecyclerViewClickListener(this);
        mGridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(mScrollListener);
    }

    private void initData() {
        TrackRepository repository =
                TrackRepository.getsInstance(TrackRemoteDataSource.getsInstance(),
                        TrackLocalDataSource.getsInstance(new GetDataLocal(getContext())));
        mPresenter = new ListTrackPresenter(repository);
        mPresenter.setView(this);
    }

    private final RecyclerView.OnScrollListener mScrollListener =
            new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > DEFAULT_SIZE) {
                        if (mGridLayoutManager != null) {
                            int totalItemCount = mGridLayoutManager.getItemCount();
                            int lastVisibleItem = mGridLayoutManager.findLastVisibleItemPosition();
                            if (!mAdapter.getLoaded() && totalItemCount <= (lastVisibleItem
                                    + VISIBLE_THRESHOLD)) {
                                if (mGenres.getTrackList().size() <= MAX_LOAD) {
                                    mAdapter.addItem(null);
                                    mAdapter.setLoaded(true);
                                    mPresenter.getListTrack(mGenres,
                                            mGenres.getTrackList().size() + INCREASE_NUMBER);
                                }
                            }
                        }
                    }
                }
            };

    @Override
    public void onGetListTracksSuccess(Genres genres) {
        mAdapter.setLoaded(false);
        mGenres = genres;
        mAdapter.removeItem(mOldSize - 1);
        mAdapter.setTrackList(genres.getTrackList());
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClickListener(Track item, int position) {
        Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
