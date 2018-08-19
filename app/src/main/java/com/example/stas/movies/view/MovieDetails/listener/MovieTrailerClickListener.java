package com.example.stas.movies.view.MovieDetails.listener;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.example.stas.movies.R;
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
        String youtubeURL = this.context.getResources().getString(R.string.watch_youtube_url);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeURL + trailer.getSource()));
        this.context.startActivity(intent);
    }
}
