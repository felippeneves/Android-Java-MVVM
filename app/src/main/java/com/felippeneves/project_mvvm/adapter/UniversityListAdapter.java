package com.felippeneves.project_mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.felippeneves.project_mvvm.R;
import com.felippeneves.project_mvvm.model.UniversityModel;

import java.util.List;

public class UniversityListAdapter extends RecyclerView.Adapter<UniversityListAdapter.MyViewHolder> {
    private Context context;
    private List<UniversityModel> listUniversities;
    private ItemClickListener clickListener;

    public UniversityListAdapter(Context context, List<UniversityModel> listUniversities, ItemClickListener clickListener) {
        this.context = context;
        this.listUniversities = listUniversities;
        this.clickListener = clickListener;
    }

    public void setListUniversities(List<UniversityModel> listUniversities) {
        this.listUniversities = listUniversities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UniversityListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityListAdapter.MyViewHolder holder, int position) {
        UniversityModel model = listUniversities.get(position);

        holder.tvName.setText(model.getName());
        holder.tvCountry.setText(String.format(context.getString(R.string.two_values_param), model.getCountry(), model.getAlpha_two_code()));

        holder.itemView.setOnClickListener(v -> clickListener.onUniversityClick(model));
    }

    @Override
    public int getItemCount() {
        return listUniversities != null ? listUniversities.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvName;
        AppCompatTextView tvCountry;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCountry = itemView.findViewById(R.id.tvCountry);
        }
    }

    public interface ItemClickListener{
        void onUniversityClick(UniversityModel model);
    }
}
