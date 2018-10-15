package com.example.stas.movies.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.stas.movies.R;
import com.example.stas.movies.view.MovieGrid.MovieListFragment;

public class MainActivity extends AppCompatActivity {

    private MenuItem mPopularMenuItem;
    private MenuItem mTopRatedMenuItem;
    private MenuItem mFavoriteMenuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            MovieListFragment fragment = new MovieListFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, MovieListFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        mPopularMenuItem = menu.findItem(R.id.show_popular);
        mTopRatedMenuItem = menu.findItem(R.id.show_top_rated);
        mFavoriteMenuItem = menu.findItem(R.id.show_favorites);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String sortBy = null;
        switch (item.getItemId()) {
            case R.id.show_top_rated:
                sortBy = getResources().getString(R.string.top_rated_key);
                mTopRatedMenuItem.setVisible(false);
                mPopularMenuItem.setVisible(true);
                mFavoriteMenuItem.setVisible(true);
                break;
            case R.id.show_popular:
                sortBy = getResources().getString(R.string.popularity_key);
                mTopRatedMenuItem.setVisible(true);
                mPopularMenuItem.setVisible(false);
                mFavoriteMenuItem.setVisible(true);
                break;
            case R.id.show_favorites:
                sortBy = "favorites";
                mFavoriteMenuItem.setVisible(false);
                mTopRatedMenuItem.setVisible(true);
                mPopularMenuItem.setVisible(true);
                break;
        }
        if (sortBy != null) {
            MovieListFragment movieListFragment = (MovieListFragment) getSupportFragmentManager().findFragmentByTag("MovieListFragment");
            movieListFragment.changeSortOrder(sortBy);
        }
        return super.onOptionsItemSelected(item);
    }
}
