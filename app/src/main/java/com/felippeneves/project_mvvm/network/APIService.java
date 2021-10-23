package com.felippeneves.project_mvvm.network;

import com.felippeneves.project_mvvm.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("volley_array.json")
    Call<List<MovieModel>> getMovieList();
}
