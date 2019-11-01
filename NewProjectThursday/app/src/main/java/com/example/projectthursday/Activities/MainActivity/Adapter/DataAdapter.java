package com.example.projectthursday.Activities.MainActivity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.projectthursday.Activities.MainActivity.Adapter.Item.Data;
import com.example.projectthursday.R;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Data> listData;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        holder.text.setText("Категория");
        Glide.with(context).load(R.drawable.girl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_category) TextView text;
        @BindView(R.id.image_category) ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.card_view_category)
        void clickCategory(){
            Toast.makeText(itemView.getContext(), text.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public void addListItem(Data data){
        listData.add(data);
        notifyItemChanged(getItemCount()-1);
    }

    public void removeLastItem(){
        if(!listData.isEmpty()){
            listData.remove(getItemCount()-1);
            notifyDataSetChanged();
        }
    }


}

