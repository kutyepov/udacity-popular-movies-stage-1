package com.example.stas.movies.view.MovieGrid.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.example.stas.movies.R;
import com.example.stas.movies.model.Movie.Movie;
import com.example.stas.movies.view.MovieDetails.MovieDetailsActivity;

public class PosterClickListener implements View.OnClickListener {
    private final Movie movie;
    private final Context context;

    public PosterClickListener(Context context, Movie movie) {
        this.movie = movie;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(this.context.getResources().getString(R.string.movie_extra), movie);
        this.context.startActivity(intent);
    }
}
