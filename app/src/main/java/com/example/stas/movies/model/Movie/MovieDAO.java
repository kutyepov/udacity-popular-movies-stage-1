package com.example.stas.movies.model.Movie;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface MovieDAO {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Delete
    void remove(Movie movie);

    @Query("SELECT * from favorite_movie_table")
    List<Movie> getAllMovies();
}
