package com.example.stas.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.stas.movies.model.Movie;
import com.example.stas.movies.model.repository.MovieRepository;


import java.util.List;

public class MovieListViewModel extends AndroidViewModel {
    private final LiveData<List<Movie>> movieListObservable;
    private String mSortBy = "popular";

    public MovieListViewModel(Application application) {
        super(application);
        movieListObservable = MovieRepository.getInstance().getMovieList(getSort());
    }

    public void setSort(String sortBy) {
        this.mSortBy = sortBy;
    }

    public String getSort() {
        return this.mSortBy;
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<Movie>> getMovieListObservable() {
        return movieListObservable;
    }
}
