package com.example.stas.movies.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import android.os.AsyncTask;
import android.util.Log;
import com.example.stas.movies.model.*;

import java.util.List;

import com.example.stas.movies.model.Movie.Movie;
import com.example.stas.movies.model.Movie.MovieDAO;
import com.example.stas.movies.model.Movie.MovieRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    public static String TAG = "MovieRepository";
    private static MovieDAO mMovieDAO;
    private TMDBService tmdbService;
    private static String mSortBy;
    private static MovieRepository movieRepository;
    private final static MutableLiveData<List<Movie>> movieListData = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> favoriteMovieListData = new MutableLiveData<>();
    private final MutableLiveData<List<MovieTrailer>> movieTrailerListData = new MutableLiveData<>();
    private final MutableLiveData<List<MovieReview>> movieReviewListData = new MutableLiveData<>();

    private MovieRepository(Application application) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(TMDBService.MOVIE_DB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tmdbService = retrofit.create(TMDBService.class);

        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDAO = db.movieDAO();
    }

    public synchronized static MovieRepository getInstance(Application application) {
        if (movieRepository == null) {
            movieRepository = new MovieRepository(application);
        }
        return movieRepository;
    }

    public LiveData<List<Movie>> getMovieList(String sortBy) {
        mSortBy = sortBy;
        if (mSortBy.equals("favorites")) {
            new getFavoriteMoviesAsyncTask(mMovieDAO, movieListData).execute();
        } else {
            tmdbService
                    .getMovieList(mSortBy, TMDBService.API_KEY)
                    .enqueue(new Callback<MovieResponse>() {
                        @Override
                        public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                            List<Movie> movies = response.body().getResults();
                            movieListData.setValue(movies);
                        }

                        @Override
                        public void onFailure(Call<MovieResponse> call, Throwable t) {
                            Log.d(MovieRepository.TAG, "failed to fetch movie from remote");
                            movieListData.setValue(null);
                        }
                    });
        }
        return movieListData;
    }
    public LiveData<List<Movie>> getFavoriteMovieList() {
        new getFavoriteMoviesAsyncTask(mMovieDAO, favoriteMovieListData).execute();
        return favoriteMovieListData;
    }

    public LiveData<List<MovieTrailer>> getMovieTrailerList(int id) {
        tmdbService
                .getMovieTrailerList(id, TMDBService.API_KEY)
                .enqueue(new Callback<MovieTrailerResponse>() {
                    @Override
                    public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {
                        movieTrailerListData.setValue(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
                        movieTrailerListData.setValue(null);
                    }
                });
        return movieTrailerListData;
    }

    public LiveData<List<MovieReview>> getMovieReviewList(int id) {
        tmdbService
                .getMovieReviewList(id, TMDBService.API_KEY)
                .enqueue(new Callback<MovieReviewResponse>() {
                    @Override
                    public void onResponse(Call<MovieReviewResponse> call, Response<MovieReviewResponse> response) {
                        movieReviewListData.setValue(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<MovieReviewResponse> call, Throwable t) {
                        movieTrailerListData.setValue(null);
                    }
                });
        return movieReviewListData;
    }

    public void addMovieToFavorites(Movie movie) {
        new insertMovieAsyncTask(mMovieDAO).execute(movie);
    }

    public void removeMovieFromFavorites(Movie movie) {
        new removeMovieAsyncTask(mMovieDAO).execute(movie);
    }


    private static class insertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDAO mAsyncMovieTaskDao;

        insertMovieAsyncTask(MovieDAO dao) {
            mAsyncMovieTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncMovieTaskDao.insert(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            refreshFavorites();
        }
    }

    private static class removeMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDAO mAsyncMovieTaskDao;

        removeMovieAsyncTask(MovieDAO dao) {
            mAsyncMovieTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncMovieTaskDao.remove(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            refreshFavorites();
        }
    }

    private static class getFavoriteMoviesAsyncTask extends AsyncTask<Void, Void, List<Movie>> {

        private final MutableLiveData<List<Movie>> mMutableLiveDataRef;
        private MovieDAO mAsyncMovieTaskDao;

        getFavoriteMoviesAsyncTask(MovieDAO dao, MutableLiveData<List<Movie>> mutableLiveDataRef) {
            mAsyncMovieTaskDao = dao;
            mMutableLiveDataRef = mutableLiveDataRef;
        }

        @Override
        protected List<Movie> doInBackground(final Void... params) {
            return mAsyncMovieTaskDao.getAllMovies();
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            mMutableLiveDataRef.setValue(movies);
        }
    }

    private static void refreshFavorites() {
        if (mSortBy.equals("favorites")) {
            new getFavoriteMoviesAsyncTask(mMovieDAO, movieListData).execute();
        }
    }
}
