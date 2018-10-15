package com.example.stas.movies.view.MovieDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stas.movies.R;
import com.example.stas.movies.model.Movie.Movie;
import com.example.stas.movies.model.repository.TMDBService;
import com.squareup.picasso.Picasso;

public class MovieInfoFragment extends Fragment {
    public static final String TAG = "MovieInfoFragment";
    private Movie movie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = getActivity().getIntent().getParcelableExtra(getResources().getString(R.string.movie_extra));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleTextView = view.findViewById(R.id.title);
        TextView ratingTextView = view.findViewById(R.id.rating);
        TextView descriptionTextView = view.findViewById(R.id.description);
        TextView releaseDateTextView = view.findViewById(R.id.release_date);
        ImageView posterImageView = view.findViewById(R.id.poster);
        titleTextView.setText(movie.getTitle());
        ratingTextView.setText(movie.getRating());

        Picasso
                .with(getContext())
                .load(TMDBService.IMAGE_DB_URL + movie.getPartialImagePath())
                .into(posterImageView);

        releaseDateTextView.setText(movie.getReleaseDate());
        descriptionTextView.setText(movie.getDescription());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_info, container, false);
    }
}
