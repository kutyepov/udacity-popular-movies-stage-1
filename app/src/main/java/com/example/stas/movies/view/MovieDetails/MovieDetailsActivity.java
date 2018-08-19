package com.example.stas.movies.view.MovieDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.stas.movies.R;
import com.example.stas.movies.view.MovieGrid.MovieListFragment;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        MovieInfoFragment movieInfoFragment = new MovieInfoFragment();
        MovieTrailerFragment movieTrailerFragment = new MovieTrailerFragment();
        MovieReviewFragment movieReviewFragment = new MovieReviewFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.movie_trailer, movieTrailerFragment, MovieListFragment.TAG)
                .add(R.id.movie_review, movieReviewFragment, MovieListFragment.TAG)
                .add(R.id.movie_primary_info, movieInfoFragment, MovieListFragment.TAG)
                .commit();
    }

}