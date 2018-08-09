package com.example.stas.movies.view.MovieDetails.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.example.stas.movies.model.MovieTrailer;

public class MovieTrailerClickListener implements View.OnClickListener {
    private final MovieTrailer trailer;
    private final Context context;

    public MovieTrailerClickListener(Context context, MovieTrailer trailer) {
        this.trailer = trailer;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(context, MovieDetailsActivity.class);
//        intent.putExtra(this.context.getResources().getString(R.string.movie_extra), trailer);
//        this.context.startActivity(intent);
        Log.d("trailer has been clicked", "yo");
    }
}
