package com.felippeneves.project_mvvm.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.felippeneves.project_mvvm.model.UniversityModel;
import com.felippeneves.project_mvvm.network.APIService;
import com.felippeneves.project_mvvm.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UniversityListViewModel extends ViewModel {
    private MutableLiveData<List<UniversityModel>> listUniversities;

    public UniversityListViewModel() {
        listUniversities = new MutableLiveData<>();
    }

    public MutableLiveData<List<UniversityModel>> getListUniversitiesObserver() {
        return listUniversities;
    }

    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<UniversityModel>> call = apiService.getListUniversities();
        call.enqueue(new Callback<List<UniversityModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<UniversityModel>> call, @NonNull Response<List<UniversityModel>> response) {
                listUniversities.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<UniversityModel>> call, @NonNull Throwable t) {
                listUniversities.postValue(null);
            }
        });
    }
}
