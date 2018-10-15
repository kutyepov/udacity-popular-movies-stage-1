package com.example.stas.movies.view.MovieDetails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.stas.movies.R;
import com.example.stas.movies.model.MovieTrailer;
import com.example.stas.movies.view.MovieDetails.listener.MovieTrailerClickListener;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ButtonViewHolder> {
    private List<MovieTrailer> trailerList = new ArrayList<>();
    private Context mContext;

    public TrailerAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_item, viewGroup, false);
        return new ButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder buttonViewHolder, int i) {
        MovieTrailer trailer = trailerList.get(i);
        buttonViewHolder.bind(trailer);
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    public void setMovies(List<MovieTrailer> trailers) {
        trailerList.clear();
        trailerList.addAll(trailers);
    }

    class ButtonViewHolder extends RecyclerView.ViewHolder {

        private final Button button;

        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.play_trailer_btn);
        }

        public void bind(MovieTrailer trailer) {
            button.setOnClickListener(new MovieTrailerClickListener(mContext, trailer));
        }
    }
}
