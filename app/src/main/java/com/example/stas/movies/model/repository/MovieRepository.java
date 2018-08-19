package com.example.stas.movies.model.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.stas.movies.model.*;

import java.util.List;

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
    private final MutableLiveData<List<MovieReview>> movieReviewListData = new MutableLiveData<>();

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

    public LiveData<List<MovieReview>> getMovieReviewList(int id) {
        tmdbService
                .getMovieReviewList(id, TMDBService.API_KEY)
                .enqueue(new Callback<MovieReviewResponse>() {
                    @Override
                    public void onResponse(
                            Call<MovieReviewResponse> call,
                            Response<MovieReviewResponse> response) {
                        movieReviewListData.setValue(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<MovieReviewResponse> call, Throwable t) {
                        movieTrailerListData.setValue(null);
                    }
                });
        return movieReviewListData;
    }
}
