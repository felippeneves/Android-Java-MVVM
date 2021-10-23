package com.felippeneves.project_mvvm.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.felippeneves.project_mvvm.model.MovieModel;
import com.felippeneves.project_mvvm.network.APIService;
import com.felippeneves.project_mvvm.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {
    private MutableLiveData<List<MovieModel>> moviesList;

    public MovieListViewModel() {
        moviesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<MovieModel>> getMoviesListObserver() {
        return moviesList;
    }

    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<MovieModel>> call = apiService.getMovieList();
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<MovieModel>> call, @NonNull Response<List<MovieModel>> response) {
                moviesList.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<MovieModel>> call, @NonNull Throwable t) {
                moviesList.postValue(null);
            }
        });
    }
}
