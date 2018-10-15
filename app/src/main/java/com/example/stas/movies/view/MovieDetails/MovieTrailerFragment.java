package com.example.stas.movies.view.MovieDetails;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stas.movies.R;
import com.example.stas.movies.model.Movie.Movie;
import com.example.stas.movies.model.MovieTrailer;
import com.example.stas.movies.view.MovieDetails.adapter.TrailerAdapter;
import com.example.stas.movies.viewmodel.MovieTrailerListViewModel;
import com.example.stas.movies.viewmodel.MovieDetailsTrailerListViewModelFactory;

import java.util.List;

public class MovieTrailerFragment extends Fragment {
    public static final String TAG = "MovieTrailerFragment";
    private TrailerAdapter trailerAdapter;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trailerAdapter = new TrailerAdapter(getActivity());
        Movie movie = getActivity().getIntent().getParcelableExtra(getResources().getString(R.string.movie_extra));

        final Application app = getActivity().getApplication();
        final MovieDetailsTrailerListViewModelFactory trailerListViewModel = new MovieDetailsTrailerListViewModelFactory(app, movie.getID());
        final MovieTrailerListViewModel viewModel = ViewModelProviders
                .of(getActivity(), trailerListViewModel)
                .get(MovieTrailerListViewModel.class);

        observeViewModel(viewModel);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.movie_trailer_rv);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(trailerAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_trailer, container, false);
    }

    private void observeViewModel(MovieTrailerListViewModel viewModel) {
        viewModel.getMovieTrailerListObservable().observe(this, new Observer<List<MovieTrailer>>() {
            @Override
            public void onChanged(@Nullable List<MovieTrailer> trailers) {
                if (trailers != null) {
                    trailerAdapter.setMovies(trailers);
                    trailerAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
