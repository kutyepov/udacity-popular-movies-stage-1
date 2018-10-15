package com.example.stas.movies.view.MovieGrid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.stas.movies.R;
import com.example.stas.movies.model.Movie.Movie;
import com.example.stas.movies.model.repository.TMDBService;
import com.example.stas.movies.view.MovieGrid.listener.PosterClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<Movie> movieList = new ArrayList<>();
    private Context mContext;

    public ImageAdapter (Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        Movie movie = movieList.get(i);
        imageViewHolder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovies(List<Movie> movies) {
        movieList.clear();
        movieList.addAll(movies);
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_poster);
        }

        public void bind(Movie movie) {
            imageView.setOnClickListener(new PosterClickListener(mContext, movie));
            Picasso
                    .with(mContext)
                    .load(TMDBService.IMAGE_DB_URL + movie.getPartialImagePath())
                    .into(imageView);
        }
    }
}
