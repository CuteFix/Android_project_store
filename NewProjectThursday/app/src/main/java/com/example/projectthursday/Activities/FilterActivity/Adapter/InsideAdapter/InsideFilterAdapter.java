package com.example.projectthursday.Activities.FilterActivity.Adapter.InsideAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectthursday.Items.FilterUnderItem;
import com.example.projectthursday.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InsideFilterAdapter extends RecyclerView.Adapter<InsideFilterAdapter.InsideViewHolder> {

    class InsideViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.checkBox)
        CheckBox checkBox;

        public InsideViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private List<FilterUnderItem> filterUnderItems;

    public InsideFilterAdapter(List<FilterUnderItem> filterUnderItems) {
        this.filterUnderItems = filterUnderItems;
    }

    @NonNull
    @Override
    public InsideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inside_filter_list,parent,false);
        return new InsideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InsideViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        FilterUnderItem item = filterUnderItems.get(position);
        holder.checkBox.setText(item.getName());
        holder.checkBox.setOnClickListener(view -> {
            if(holder.checkBox.isChecked()){
                Toast.makeText(context,"click " + position,Toast.LENGTH_SHORT).show();
            }
            else {

            }
        });
    }

    @Override
    public int getItemCount() {
        return filterUnderItems.size();
    }

}
