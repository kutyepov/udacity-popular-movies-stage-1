package com.example.stas.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.example.stas.movies.model.Movie.Movie;
import com.example.stas.movies.model.repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends AndroidViewModel {
    private final MovieRepository movieRepository;
    private LiveData<List<Movie>> movieListObservable;

    public MovieListViewModel(Application application, String sortBy) {
        super(application);
        movieRepository = MovieRepository.getInstance(application);
        movieListObservable = movieRepository.getMovieList(sortBy);
    }

    public LiveData<List<Movie>> getMovieListObservable() {
        return movieListObservable;
    }

    public void changeSort(String sortBy) {
        movieListObservable = movieRepository.getMovieList(sortBy);
    }
}
