package com.example.stas.movies.model.Movie;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "favorite_movie_table")
public class Movie implements Parcelable {
    private final static String ID_KEY = "id";
    private final static String TITLE_KEY = "title";
    private final static String RATING_KEY = "vote_average";
    private final static String RELEASE_DATE_KEY = "release_date";
    private final static String DESCRIPTION_KEY = "overview";
    private final static String IMAGE_PATH_KEY = "poster_path";

    Movie() {}

    @PrimaryKey
    @ColumnInfo(name = ID_KEY)
    @SerializedName(ID_KEY)
    private int mID;

    @ColumnInfo(name = TITLE_KEY)
    @SerializedName(TITLE_KEY)
    private String mTitle;

    @ColumnInfo(name = RATING_KEY)
    @SerializedName(RATING_KEY)
    private String mRating;

    @ColumnInfo(name = RELEASE_DATE_KEY)
    @SerializedName(RELEASE_DATE_KEY)
    private String mReleaseDate;

    @ColumnInfo(name = DESCRIPTION_KEY)
    @SerializedName(DESCRIPTION_KEY)
    private String mDescription;

    @ColumnInfo(name = IMAGE_PATH_KEY)
    @SerializedName(IMAGE_PATH_KEY)
    private String mPartialImagePath;

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

    public void setID(int id) {
        this.mID = id;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setRating(String rating) {
        this.mRating = rating;
    }

    public void setReleaseDate(String releaseDate) {
        this.mReleaseDate = releaseDate;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setPartialImagePath(String partialImagePath) {
        this.mPartialImagePath = partialImagePath;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mID);
        parcel.writeString(mTitle);
        parcel.writeString(mRating);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mDescription);
        parcel.writeString(mPartialImagePath);
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
