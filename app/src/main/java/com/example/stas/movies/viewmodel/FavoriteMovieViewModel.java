package com.example.stas.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.example.stas.movies.model.Movie.Movie;
import com.example.stas.movies.model.repository.MovieRepository;

import java.util.List;

public class FavoriteMovieViewModel extends AndroidViewModel {

    private MovieRepository mRepository;
    private LiveData<List<Movie>> movieListObservable;

    public FavoriteMovieViewModel (Application application) {
        super(application);
        mRepository = MovieRepository.getInstance(application);
        movieListObservable = mRepository.getFavoriteMovieList();
    }

    public void addToFavorites(Movie movie) { mRepository.addMovieToFavorites(movie); }
    public void removeFromFavorites(Movie movie) { mRepository.removeMovieFromFavorites(movie); }
    public LiveData<List<Movie>> getAllFavorites() { return  movieListObservable; }
}
