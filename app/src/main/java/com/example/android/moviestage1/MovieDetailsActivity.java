package com.example.android.moviestage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.databinding.DataBindingUtil;

import com.example.android.moviestage1.databinding.ActivityMovieDetailsBinding;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding mDetailBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        Movie movie = getIntent().getParcelableExtra(getResources().getString(R.string.movie_extra));
        mDetailBinding.primaryInfo.title.setText(movie.getTitle());
        mDetailBinding.primaryInfo.rating.setText(movie.getRating());
        Picasso
                .with(this)
                .load(movie.getFullImagePath())
                .into(mDetailBinding.primaryInfo.poster);
        mDetailBinding.secondaryInfo.releaseDate.setText(movie.getReleaseDate());
        mDetailBinding.secondaryInfo.description.setText(movie.getDescription());
    }
}
