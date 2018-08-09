package com.example.stas.movies.view.MovieGrid;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stas.movies.R;
import com.example.stas.movies.model.Movie;
import com.example.stas.movies.view.MovieGrid.adapter.ImageAdapter;
import com.example.stas.movies.viewmodel.MovieListViewModel;

import java.util.List;

public class MovieListFragment extends Fragment {
    public static final String TAG = "MovieListFragment";
    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageAdapter = new ImageAdapter(getActivity());
        final MovieListViewModel viewModel =
                ViewModelProviders.of(this).get(MovieListViewModel.class);

        observeViewModel(viewModel);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(imageAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_list_fragment, container, false);
    }

    private void observeViewModel(MovieListViewModel viewModel) {
        // Update the list when the data changes
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
}
