package com.example.stas.movies.view.MovieDetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.stas.movies.R;
import com.example.stas.movies.model.Movie.Movie;
import com.example.stas.movies.view.MovieGrid.MovieListFragment;
import com.example.stas.movies.viewmodel.FavoriteMovieViewModel;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    private MenuItem mAddToFavoritesMenuItem;
    private MenuItem mRemoveFromFavoritesMenuItem;
    private FavoriteMovieViewModel viewModel;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        MovieInfoFragment movieInfoFragment = new MovieInfoFragment();
        MovieTrailerFragment movieTrailerFragment = new MovieTrailerFragment();
        MovieReviewFragment movieReviewFragment = new MovieReviewFragment();

        viewModel = ViewModelProviders.of(this).get(FavoriteMovieViewModel.class);
        movie = getIntent().getParcelableExtra(getResources().getString(R.string.movie_extra));

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.movie_trailer, movieTrailerFragment, MovieListFragment.TAG)
                .add(R.id.movie_review, movieReviewFragment, MovieListFragment.TAG)
                .add(R.id.movie_primary_info, movieInfoFragment, MovieListFragment.TAG)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_activity_menu, menu);
        mAddToFavoritesMenuItem = menu.findItem(R.id.add_to_favorites);
        mRemoveFromFavoritesMenuItem = menu.findItem(R.id.remove_from_favorites);
        observeViewModel();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_to_favorites:
                mRemoveFromFavoritesMenuItem.setVisible(true);
                mAddToFavoritesMenuItem.setVisible(false);
                viewModel.addToFavorites(movie);
                break;
            case R.id.remove_from_favorites:
                mAddToFavoritesMenuItem.setVisible(true);
                mRemoveFromFavoritesMenuItem.setVisible(false);
                viewModel.removeFromFavorites(movie);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void observeViewModel() {
        viewModel.getAllFavorites().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null) {
                    boolean isFavorite = false;
                    for (Movie _movie : movies) {
                        if (_movie.getID() == movie.getID()) {
                            isFavorite = true;
                            break;
                        }
                    }
                    if (isFavorite) {
                        mRemoveFromFavoritesMenuItem.setVisible(true);
                        mAddToFavoritesMenuItem.setVisible(false);
                    } else {
                        mAddToFavoritesMenuItem.setVisible(true);
                        mRemoveFromFavoritesMenuItem.setVisible(false);
                    }
                }
            }
        });
    }

}
