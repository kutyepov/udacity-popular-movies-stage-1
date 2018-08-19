package com.example.stas.movies.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class MovieTrailer implements Parcelable {
    private final static String NAME_KEY = "name";
    private final static String SOURCE_KEY = "source";

    //    private JSONObject mMovie;
    @SerializedName(NAME_KEY)
    private String mName;
    @SerializedName(SOURCE_KEY)
    private String mSource;

    public String getName() {
        return mName;
    }

    public String getSource() {
        return mSource;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mSource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private MovieTrailer(Parcel parcel) {
        mName = parcel.readString();
        mSource = parcel.readString();
    }

    public static final Creator<MovieTrailer> CREATOR = new Creator<MovieTrailer>() {
        public MovieTrailer createFromParcel(Parcel parcel) {
            return new MovieTrailer(parcel);
        }

        public MovieTrailer[] newArray(int size) {
            return new MovieTrailer[size];
        }
    };

}
