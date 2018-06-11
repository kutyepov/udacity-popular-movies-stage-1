package com.example.android.moviestage1;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Parcelable {
    private final static String TITLE_KEY = "title";
    private final static String RATING_KEY = "vote_average";
    private final static String RELEASE_DATE_KEY = "release_date";
    private final static String DESCRIPTION_KEY = "overview";
    private final static String IMAGE_PATH_KEY = "poster_path";

    private JSONObject mMovie;
    private String mTitle = null;
    private String mRating = null;
    private String mReleaseDate = null;
    private String mDescription = null;
    private String mPartialImagePath = null;
    private String mFullImagePath = null;

    Movie(JSONObject movie) {
        mMovie = movie;
        setTitle();
        setRating();
        setReleaseDate();
        setDescription();
        setPartialImagePath();
        setFullImagePath();
    }

    private void setTitle() {
        try {
            mTitle = mMovie.getString(TITLE_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setRating() {
        try {
            mRating = mMovie.getString(RATING_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setReleaseDate() {
        try {
            mReleaseDate = mMovie.getString(RELEASE_DATE_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setDescription() {
        try {
            mDescription = mMovie.getString(DESCRIPTION_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setPartialImagePath() {
        try {
            mPartialImagePath = mMovie.getString(IMAGE_PATH_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setFullImagePath() {
        mFullImagePath = NetworkUtils.getImageBaseURL() + this.getPartialImagePath();
    }

    public String getTitle() {
        return mTitle;
    }

    public String getRating() {
        return mRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPartialImagePath() {
        return mPartialImagePath;
    }

    public String getFullImagePath() {
        return mFullImagePath;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mRating);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mDescription);
        parcel.writeString(mPartialImagePath);
        parcel.writeString(mFullImagePath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private Movie (Parcel parcel) {
        mTitle = parcel.readString();
        mRating = parcel.readString();
        mReleaseDate = parcel.readString();
        mDescription = parcel.readString();
        mPartialImagePath = parcel.readString();
        mFullImagePath = parcel.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
