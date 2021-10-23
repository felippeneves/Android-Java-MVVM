package com.felippeneves.project_mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.felippeneves.project_mvvm.R;
import com.felippeneves.project_mvvm.model.MovieModel;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    private Context context;
    private List<MovieModel> movieList;
    private ItemClickListener clickListener;

    public MovieListAdapter(Context context, List<MovieModel> movieList, ItemClickListener clickListener) {
        this.context = context;
        this.movieList = movieList;
        this.clickListener = clickListener;
    }

    public void setMovieList(List<MovieModel> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.movieList.get(position).getTitle().toString());

        holder.itemView.setOnClickListener(v -> clickListener.onMovieClick(movieList.get(position)));
        Glide.with(context)
                .load(this.movieList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movieList != null ? movieList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvTitle;
        AppCompatImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.titleView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public interface ItemClickListener{
        public void onMovieClick(MovieModel movie);
    }
}
