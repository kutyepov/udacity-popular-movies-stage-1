package com.example.stas.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.example.stas.movies.model.MovieReview;
import com.example.stas.movies.model.repository.MovieRepository;

import java.util.List;

public class MovieReviewListViewModel extends AndroidViewModel {
    private final LiveData<List<MovieReview>> movieTrailerListObservable;

    public MovieReviewListViewModel(Application application, int id) {
        super(application);
        movieTrailerListObservable = MovieRepository.getInstance(application).getMovieReviewList(id);
    }

    public LiveData<List<MovieReview>> getMovieReviewListObservable() {
        return movieTrailerListObservable;
    }
}
