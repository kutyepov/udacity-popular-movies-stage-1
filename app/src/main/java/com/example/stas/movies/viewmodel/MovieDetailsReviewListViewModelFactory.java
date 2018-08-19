package com.example.stas.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class MovieDetailsReviewListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private int mID;


    public MovieDetailsReviewListViewModelFactory(Application application, int id) {
        mApplication = application;
        mID = id;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MovieReviewListViewModel(mApplication, mID);
    }
}