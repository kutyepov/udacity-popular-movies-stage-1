package com.example.stas.movies.model.repository;

import com.example.stas.movies.BuildConfig;
import com.example.stas.movies.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface TMDBService {
    String MOVIE_DB_URL = "https://api.themoviedb.org/3/";
    String IMAGE_DB_URL = "https://image.tmdb.org/t/p/";
    String API_KEY_PARAM = "api_key";
    String API_KEY = BuildConfig.API_KEY;

    @GET("movie/{sortBy}")
    Call<MovieResponse> getMovieList(
            @Path("sortBy") String sortBy,
            @Query(API_KEY_PARAM) String apiKey
    );

//    @GET("/repos/{user}/{reponame}")
//    Call<Movie> getMovieDetails(@Path("user") String user, @Path("reponame") String projectName);
}
