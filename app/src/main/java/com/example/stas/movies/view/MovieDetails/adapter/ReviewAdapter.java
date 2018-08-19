package com.example.stas.movies.view.MovieDetails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.stas.movies.R;
import com.example.stas.movies.model.MovieReview;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<MovieReview> reviewList = new ArrayList<>();
    private Context mContext;

    public ReviewAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.review_item, viewGroup, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder ReviewViewHolder, int i) {
        MovieReview review = reviewList.get(i);
        ReviewViewHolder.bind(review);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public void setMovies(List<MovieReview> reviews) {
        reviewList.clear();
        reviewList.addAll(reviews);
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        private final TextView authorTV;
        private final TextView contentTV;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            authorTV = itemView.findViewById(R.id.author_tv);
            contentTV = itemView.findViewById(R.id.review_content_tv);
        }

        public void bind(MovieReview review) {
            authorTV.setText(review.getAuthor());
            contentTV.setText(review.getContent());
        }
    }
}
