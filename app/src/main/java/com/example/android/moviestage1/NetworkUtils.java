package com.example.android.moviestage1;

import android.content.Context;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {
    private static final String MOVIE_DB_URL = "https://api.themoviedb.org/3/movie";
    private static final String IMAGE_DB_URL = "https://image.tmdb.org/t/p/";
    private static final String API_KEY_PARAM = "api_key";
    private static final String API_KEY = BuildConfig.API_KEY;
    private static Context mContext;

    NetworkUtils(Context context) {
        mContext = context;
    }

    public static String getImageBaseURL() {
        return IMAGE_DB_URL +
                mContext.getResources().getString(R.string.image_size_prefix) +
                mContext.getResources().getInteger(R.integer.image_size);
    }

    public static URL buildURL(String sortBy) {
        Uri uri = Uri.parse(MOVIE_DB_URL).buildUpon()
                .appendPath(sortBy)
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String fetchMovies(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) {
                return scanner.next();
            }
            return null;
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public static JSONArray getDataFromHTTPResponse(String HTTPResponse) throws JSONException {
        JSONObject data = new JSONObject(HTTPResponse);
        return data.getJSONArray("results");
    }
}
