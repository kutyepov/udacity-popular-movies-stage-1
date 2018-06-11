package com.example.android.moviestage1;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;

import java.net.URL;

public class MainActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<JSONArray> {

    private static final int MOVIES_LOADER_ID = 0;
    private ImageAdapter mImageAdapter;
    private GridView mGridview;
    private MenuItem mPopularMenuItem;
    private MenuItem mTopRatedMenuItem;
    private String mSortBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageAdapter = new ImageAdapter(this);
        mSortBy = getResources().getString(R.string.top_rated_key);

        mGridview = (GridView) findViewById(R.id.gridview);
        mGridview.setAdapter(mImageAdapter);

        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                intent.putExtra(getResources().getString(R.string.movie_extra), movie);
                startActivity(intent);
            }
        });


        LoaderManager.LoaderCallbacks<JSONArray> callback = MainActivity.this;
        getSupportLoaderManager().initLoader(MOVIES_LOADER_ID, null, callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        mPopularMenuItem = menu.findItem(R.id.show_popular);
        mTopRatedMenuItem = menu.findItem(R.id.show_top_rated);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_top_rated:
                mSortBy = getResources().getString(R.string.top_rated_key);
                mTopRatedMenuItem.setVisible(false);
                mPopularMenuItem.setVisible(true);
                getSupportLoaderManager().restartLoader(MOVIES_LOADER_ID, null, this);
                break;
            case R.id.show_popular:
                mSortBy = getResources().getString(R.string.popularity_key);
                mTopRatedMenuItem.setVisible(true);
                mPopularMenuItem.setVisible(false);
                getSupportLoaderManager().restartLoader(MOVIES_LOADER_ID, null, this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<JSONArray> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<JSONArray>(this) {
            JSONArray mMoviesData = null;

            @Override
            public void deliverResult(JSONArray data) {
                mMoviesData = data;
                super.deliverResult(data);
            }

            @Override
            protected void onStartLoading() {
                if (mMoviesData != null) {
                    deliverResult(mMoviesData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public JSONArray loadInBackground() {
                NetworkUtils networkUtils = new NetworkUtils(MainActivity.this);
                URL moviesUrl = networkUtils.buildURL(mSortBy);

                try {
                    return networkUtils.getDataFromHTTPResponse(networkUtils.fetchMovies(moviesUrl));
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }


    @Override
    public void onLoadFinished(Loader<JSONArray> loader, JSONArray data) {
        if (data == null) {
            Toast
                    .makeText(this, "oups, error downloading data", Toast.LENGTH_LONG)
                    .show();
        } else {
            mImageAdapter.setData(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<JSONArray> loader) {
    }
}
