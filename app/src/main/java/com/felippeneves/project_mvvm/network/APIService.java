package com.felippeneves.project_mvvm.network;

import com.felippeneves.project_mvvm.model.UniversityModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("search?country=Brazil")
    Call<List<UniversityModel>> getListUniversities();
}