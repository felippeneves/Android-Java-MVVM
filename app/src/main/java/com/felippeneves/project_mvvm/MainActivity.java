package com.felippeneves.project_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.felippeneves.project_mvvm.adapter.UniversityListAdapter;
import com.felippeneves.project_mvvm.model.UniversityModel;
import com.felippeneves.project_mvvm.viewmodel.UniversityListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UniversityListAdapter.ItemClickListener {

    private UniversityListAdapter adapter;
    private UniversityListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        initializeScreen();
    }

    private void initializeScreen() {
        RecyclerView recyclerView = findViewById(R.id.rvInformations);
        final AppCompatTextView tvNoResult = findViewById(R.id.tvNoResult);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new UniversityListAdapter(this, new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        //
        viewModel = new ViewModelProvider(this).get(UniversityListViewModel.class);

        viewModel.getListUniversitiesObserver().observe(this, model -> {
            if(model != null) {
                adapter.setListUniversities(model);
                tvNoResult.setVisibility(View.GONE);
            } else {
                tvNoResult.setVisibility(View.VISIBLE);
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onUniversityClick(UniversityModel model) {
        Toast.makeText(this, String.format(getString(R.string.clicked_university_param), model.getName()), Toast.LENGTH_SHORT).show();
    }
}