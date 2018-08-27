package com.example.stas.movies.model.repository;

import com.example.stas.movies.BuildConfig;
import com.example.stas.movies.model.MovieResponse;

import com.example.stas.movies.model.MovieReviewResponse;
import com.example.stas.movies.model.MovieTrailerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBService {
    String MOVIE_DB_URL = "https://api.themoviedb.org/3/";
    String IMAGE_DB_URL = "https://image.tmdb.org/t/p/w185/";
    String API_KEY_PARAM = "api_key";
    String API_KEY = BuildConfig.API_KEY;

    @GET("movie/{sortBy}")
    Call<MovieResponse> getMovieList(
            @Path("sortBy") String sortBy,
            @Query(API_KEY_PARAM) String apiKey
    );

    @GET("movie/{id}/trailers")
    Call<MovieTrailerResponse> getMovieTrailerList(
            @Path("id") int id,
            @Query(API_KEY_PARAM) String apiKey
    );

    @GET("movie/{id}/reviews")
    Call<MovieReviewResponse> getMovieReviewList(
            @Path("id") int id,
            @Query(API_KEY_PARAM) String apiKey
    );
}
