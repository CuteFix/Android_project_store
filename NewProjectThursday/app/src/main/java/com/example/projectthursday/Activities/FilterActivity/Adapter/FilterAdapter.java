package com.example.projectthursday.Activities.FilterActivity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectthursday.Activities.FilterActivity.Adapter.InsideAdapter.InsideFilterAdapter;
import com.example.projectthursday.Items.FilterItem;
import com.example.projectthursday.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {

    class FilterViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.filter_name) TextView filterName;
        @BindView(R.id.filter_image) ImageView filterImage;
        @BindView(R.id.filter_recycler_view) RecyclerView filterRecyclerView;

        FilterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private List<FilterItem> filterItemList;

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_list,parent,false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        FilterItem filterItem = filterItemList.get(position);
        Context context = holder.itemView.getContext();
        holder.filterName.setText(filterItem.getName());

        holder.filterImage.setOnClickListener(view -> {
            int recyclerViewVisible = holder.filterRecyclerView.getVisibility();

            if (recyclerViewVisible == View.GONE){
                holder.filterRecyclerView.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(R.drawable.chevron_down)
                        .into(holder.filterImage);
            }
            else {
                holder.filterRecyclerView.setVisibility(View.GONE);
                Glide.with(context)
                        .load(R.drawable.chevron_up)
                        .into(holder.filterImage);
            }
        });

        holder.filterRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.filterRecyclerView.setAdapter(new InsideFilterAdapter(filterItem.getFilterUnderItems()));
    }

    @Override
    public int getItemCount() {
        return filterItemList.size();
    }
}
