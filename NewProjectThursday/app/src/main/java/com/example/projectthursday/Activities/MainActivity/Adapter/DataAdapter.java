package com.example.projectthursday.Activities.MainActivity.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.projectthursday.R;
import com.example.projectthursday.Retrofit2.Items.GetCategoryItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.AllArgsConstructor;

import static com.example.projectthursday.Utils.Constants.GET_IMAGE_CATEGORY_URL;

@AllArgsConstructor
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private static final String TAG = DataAdapter.class.getCanonicalName();

    private List<GetCategoryItem> listData;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        GetCategoryItem item = listData.get(position);
        holder.text.setText(item.getName());
        String imagePath = GET_IMAGE_CATEGORY_URL + item.getImage();
        Log.i(TAG,"parseItems image(" + position + ") = " + imagePath + "start");
        Glide.with(context)
                .load(imagePath)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.i(TAG,"parseItems image(" + position + ") false");
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.i(TAG,"parseItems image(" + position + ") true");
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .error(R.drawable.non_image)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_category) TextView text;
        @BindView(R.id.image_category) ImageView imageView;
        @BindView(R.id.progressBar) ProgressBar progressBar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.card_view_category)
        void clickCategory(){
            Toast.makeText(itemView.getContext(), text.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public void addItem(GetCategoryItem data){
        listData.add(data);
        notifyItemChanged(getItemCount()-1);
    }

    public void addListItems(List<GetCategoryItem> data){
        listData = data;
        notifyDataSetChanged();
    }

    public void removeLastItem(){
        if(!listData.isEmpty()){
            listData.remove(getItemCount()-1);
            notifyDataSetChanged();
        }
    }

    public void removeAll(){
        listData = new ArrayList<>();
        notifyDataSetChanged();
    }
}

