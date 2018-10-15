package com.example.stas.movies.view.MovieDetails;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stas.movies.R;
import com.example.stas.movies.model.Movie.Movie;
import com.example.stas.movies.model.MovieReview;
import com.example.stas.movies.view.MovieDetails.adapter.ReviewAdapter;
import com.example.stas.movies.viewmodel.MovieDetailsReviewListViewModelFactory;
import com.example.stas.movies.viewmodel.MovieReviewListViewModel;

import java.util.List;

public class MovieReviewFragment extends Fragment {
    public static final String TAG = "MovieReviewFragment";
    private ReviewAdapter reviewAdapter;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewAdapter = new ReviewAdapter(getActivity());
        Movie movie = getActivity().getIntent().getParcelableExtra(getResources().getString(R.string.movie_extra));

        final Application app = getActivity().getApplication();
        final MovieDetailsReviewListViewModelFactory reviewListViewModel = new MovieDetailsReviewListViewModelFactory(app, movie.getID());
        final MovieReviewListViewModel viewModel = ViewModelProviders
                .of(getActivity(), reviewListViewModel)
                .get(MovieReviewListViewModel.class);

        observeViewModel(viewModel);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.movie_review_rv);
        recyclerView.setAdapter(reviewAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_review, container, false);
    }

    private void observeViewModel(MovieReviewListViewModel viewModel) {
        viewModel.getMovieReviewListObservable().observe(this, new Observer<List<MovieReview>>() {
            @Override
            public void onChanged(@Nullable List<MovieReview> review) {
                if (review != null) {
                    reviewAdapter.setMovies(review);
                    reviewAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
