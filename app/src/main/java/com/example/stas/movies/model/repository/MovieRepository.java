package com.example.stas.movies.model.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.stas.movies.model.Movie;
import com.example.stas.movies.model.MovieResponse;

import java.util.List;

import com.example.stas.movies.model.MovieTrailer;
import com.example.stas.movies.model.MovieTrailerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private TMDBService tmdbService;
    private static MovieRepository movieRepository;
    private final MutableLiveData<List<Movie>> movieListData = new MutableLiveData<>();
    private final MutableLiveData<List<MovieTrailer>> movieTrailerListData = new MutableLiveData<>();

    private MovieRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TMDBService.MOVIE_DB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tmdbService = retrofit.create(TMDBService.class);
    }

    public synchronized static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    public LiveData<List<Movie>> getMovieList(String sortBy) {
        tmdbService
                .getMovieList(sortBy, TMDBService.API_KEY)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(
                            Call<MovieResponse> call,
                            Response<MovieResponse> response) {
                        movieListData.setValue(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        movieListData.setValue(null);
                    }
                });
        return movieListData;
    }

    public LiveData<List<MovieTrailer>> getMovieTrailerList(int id) {
        tmdbService
                .getMovieTrailerList(id, TMDBService.API_KEY)
                .enqueue(new Callback<MovieTrailerResponse>() {
                    @Override
                    public void onResponse(
                            Call<MovieTrailerResponse> call,
                            Response<MovieTrailerResponse> response) {
                        movieTrailerListData.setValue(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
                        movieTrailerListData.setValue(null);
                    }
                });
        return movieTrailerListData;
    }
}
