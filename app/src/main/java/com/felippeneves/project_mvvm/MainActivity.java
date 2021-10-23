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

import com.felippeneves.project_mvvm.adapter.MovieListAdapter;
import com.felippeneves.project_mvvm.model.MovieModel;
import com.felippeneves.project_mvvm.viewmodel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.ItemClickListener {

    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;

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
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new MovieListAdapter(this, movieModelList, this);
        recyclerView.setAdapter(adapter);
        //
        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        viewModel.getMoviesListObserver().observe(this, movieModels -> {
            if(movieModels != null) {
                movieModelList = movieModels;
                adapter.setMovieList(movieModels);
                tvNoResult.setVisibility(View.GONE);
            } else {
                tvNoResult.setVisibility(View.VISIBLE);
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onMovieClick(MovieModel movie) {
        Toast.makeText(this, String.format(getString(R.string.clicked_movie_param), movie.getTitle()), Toast.LENGTH_SHORT).show();
    }
}