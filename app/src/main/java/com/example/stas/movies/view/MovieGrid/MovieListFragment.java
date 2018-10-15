package com.example.stas.movies.view.MovieGrid;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stas.movies.R;
import com.example.stas.movies.model.Movie.Movie;
import com.example.stas.movies.view.MovieGrid.adapter.ImageAdapter;
import com.example.stas.movies.viewmodel.MovieListViewModel;
import com.example.stas.movies.viewmodel.MovieListViewModelFactory;

import java.util.List;

public class MovieListFragment extends Fragment {
    public static final String TAG = "MovieListFragment";
    private ImageAdapter imageAdapter;
    private MovieListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageAdapter = new ImageAdapter(getActivity());
        final String sortBy = getResources().getString(R.string.popularity_key);
        final Application app = getActivity().getApplication();
        final MovieListViewModelFactory movieListViewModelFactory = new MovieListViewModelFactory(app, sortBy);
        viewModel = ViewModelProviders
                .of(getActivity(), movieListViewModelFactory)
                .get(MovieListViewModel.class);
        observeViewModel();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numOfColumns = (int) (dpWidth / 180);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numOfColumns));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(imageAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_list_fragment, container, false);
    }

    private void observeViewModel() {
        viewModel.getMovieListObservable().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null) {
                    imageAdapter.setMovies(movies);
                    imageAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void changeSortOrder(String sortBy) {
        viewModel.changeSort(sortBy);
    }
}
