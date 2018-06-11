package com.example.android.moviestage1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Movie[] mMovies;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        if (mMovies != null) {
            return mMovies.length;
        }
        return 0;
    }

    public Object getItem(int position) {
        if (mMovies != null) {
            return mMovies[position];
        }
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso
                .with(mContext)
                .load(mMovies[position].getFullImagePath())
                .into(imageView);
        return imageView;
    }

    public void setData(JSONArray movies) {
        mMovies = new Movie[movies.length()];
        try {
            for (int i = 0; i < movies.length(); i++) {
                mMovies[i] = new Movie((JSONObject) movies.get(i));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }
}