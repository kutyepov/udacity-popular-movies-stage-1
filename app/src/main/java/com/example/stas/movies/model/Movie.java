package com.example.stas.movies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Parcelable {
    private final static String ID_KEY = "id";
    private final static String TITLE_KEY = "title";
    private final static String RATING_KEY = "vote_average";
    private final static String RELEASE_DATE_KEY = "release_date";
    private final static String DESCRIPTION_KEY = "overview";
    private final static String IMAGE_PATH_KEY = "poster_path";

    //    private JSONObject mMovie;
    @SerializedName(ID_KEY)
    private int mID;
    @SerializedName(TITLE_KEY)
    private String mTitle = null;
    @SerializedName(RATING_KEY)
    private String mRating = null;
    @SerializedName(RELEASE_DATE_KEY)
    private String mReleaseDate = null;
    @SerializedName(DESCRIPTION_KEY)
    private String mDescription = null;
    @SerializedName(IMAGE_PATH_KEY)
    private String mPartialImagePath = null;
//    private String mFullImagePath = null;

//    public Movie() { }

    public int getID() {
        return mID;
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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mID);
        parcel.writeString(mTitle);
        parcel.writeString(mRating);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mDescription);
        parcel.writeString(mPartialImagePath);
//        parcel.writeString(mFullImagePath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private Movie(Parcel parcel) {
        mID = parcel.readInt();
        mTitle = parcel.readString();
        mRating = parcel.readString();
        mReleaseDate = parcel.readString();
        mDescription = parcel.readString();
        mPartialImagePath = parcel.readString();
//        mFullImagePath = parcel.readString();
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
