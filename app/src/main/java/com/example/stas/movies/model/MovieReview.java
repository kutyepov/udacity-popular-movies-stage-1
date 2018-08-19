package com.example.stas.movies.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class MovieReview implements Parcelable {
    private final static String ID_KEY = "id";
    private final static String AUTHOR_KEY = "author";
    private final static String CONTENT_KEY = "content";
    private final static String URL_KEY = "url";

    @SerializedName(ID_KEY)
    private String mID;
    @SerializedName(AUTHOR_KEY)
    private String mAuthor;
    @SerializedName(CONTENT_KEY)
    private String mContent;
    @SerializedName(URL_KEY)
    private String mURL;

    public String getID() {
        return mID;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getContent() {
        return mContent;
    }

    public String getURL() {
        return mURL;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mID);
        parcel.writeString(mAuthor);
        parcel.writeString(mContent);
        parcel.writeString(mURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private MovieReview(Parcel parcel) {
        mID = parcel.readString();
        mAuthor = parcel.readString();
        mContent = parcel.readString();
        mURL = parcel.readString();
    }

    public static final Creator<MovieReview> CREATOR = new Creator<MovieReview>() {
        public MovieReview createFromParcel(Parcel parcel) {
            return new MovieReview(parcel);
        }

        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };

}
