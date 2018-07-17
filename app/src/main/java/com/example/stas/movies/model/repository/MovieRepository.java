package com.example.stas.movies.model.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.stas.movies.model.Movie;
import com.example.stas.movies.model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private TMDBService tmdbService;
    private static MovieRepository projectRepository;
    private final MutableLiveData<List<Movie>> data = new MutableLiveData<>();

    private MovieRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TMDBService.MOVIE_DB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tmdbService = retrofit.create(TMDBService.class);
    }

    public synchronized static MovieRepository getInstance() {
        if (projectRepository == null) {
            projectRepository = new MovieRepository();
        }
        return projectRepository;
    }

    public LiveData<List<Movie>> getMovieList(String sortBy) {
        tmdbService
                .getMovieList(sortBy, TMDBService.API_KEY)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        data.setValue(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

//    public LiveData<Movie> getProjectDetails(String userID, String projectName) {
//        final MutableLiveData<Movie> data = new MutableLiveData<>();
//
//        tmdbService.getProjectDetails(userID, projectName).enqueue(new Callback<Movie>() {
//            @Override
//            public void onResponse(Call<Movie> call, Response<Movie> response) {
//                simulateDelay();
//                data.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Movie> call, Throwable t) {
//                // TODO better error handling in part #2 ...
//                data.setValue(null);
//            }
//        });
//
//        return data;
//    }
}
