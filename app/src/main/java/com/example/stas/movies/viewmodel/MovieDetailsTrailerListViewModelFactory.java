package com.example.stas.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class MovieDetailsTrailerListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private int mID;


    public MovieDetailsTrailerListViewModelFactory(Application application, int id) {
        mApplication = application;
        mID = id;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MovieTrailerListViewModel(mApplication, mID);
    }
}