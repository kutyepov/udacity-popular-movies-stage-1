package com.example.stas.movies.viewmodel;

        import android.app.Application;
        import android.arch.lifecycle.ViewModel;
        import android.arch.lifecycle.ViewModelProvider;

public class MovieListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private String mSortBy;


    public MovieListViewModelFactory(Application application, String sortBy) {
        mApplication = application;
        mSortBy = sortBy;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MovieListViewModel(mApplication, mSortBy);
    }
}
