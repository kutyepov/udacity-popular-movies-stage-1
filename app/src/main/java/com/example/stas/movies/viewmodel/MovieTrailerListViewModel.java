package com.example.stas.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.example.stas.movies.model.Movie;
import com.example.stas.movies.model.MovieTrailer;
import com.example.stas.movies.model.repository.MovieRepository;

import java.util.List;

public class MovieTrailerListViewModel extends AndroidViewModel {
    private final LiveData<List<MovieTrailer>> movieTrailerListObservable;
    private int id;

    public MovieTrailerListViewModel(Application application, int id) {
        super(application);
        movieTrailerListObservable = MovieRepository.getInstance().getMovieTrailerList(id);
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<MovieTrailer>> getMovieTrailerListObservable() {
        return movieTrailerListObservable;
    }
}
