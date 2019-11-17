package com.example.projectthursday.Activities.MainActivity.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.projectthursday.R;
import com.example.projectthursday.Retrofit2.Items.Category;
import com.example.projectthursday.Retrofit2.Items.GetCategoryItem;
import com.example.projectthursday.Retrofit2.Items.GetSubCategoryItem;
import com.example.projectthursday.ServerRequests.Requests;
import com.example.projectthursday.Utils.Language;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import retrofit2.Response;

import static com.example.projectthursday.Utils.Constants.GET_IMAGE_CATEGORY_URL;
import static com.example.projectthursday.Utils.Constants.GET_IMAGE_SUB_CATEGORY_URL;

@Getter
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_category)
        TextView text;
        @BindView(R.id.image_category)
        ImageView imageView;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;
        @BindView(R.id.card_view_category)
        ViewGroup container;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    private static final String TAG = DataAdapter.class.getCanonicalName();
    private List<Category> fullListData;
    @Setter
    private List<Category> filterListData;
    private NewDataCallback callback;

    public DataAdapter(List<Category> listData, NewDataCallback callback) {
        this.fullListData = listData;
        this.filterListData = new ArrayList<>(listData);
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Category category = filterListData.get(position);

        holder.text.setText(category.getName());
        String imagePath = null;
        Log.i(TAG, "parseItems image(" + position + ") = " + imagePath + "start");


        if (category instanceof GetCategoryItem) {
            imagePath = GET_IMAGE_CATEGORY_URL + category.getImage();
            GetCategoryItem item = (GetCategoryItem) category;
            holder.container.setOnClickListener(view ->
                    Requests.INSTANCE.getSubCategories(item.getId(), Language.get(), null)
                            .subscribe(new SingleObserver<Response<List<GetSubCategoryItem>>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(Response<List<GetSubCategoryItem>> listResponse) {
                                    int responseCode = listResponse.code();
                                    Log.d(TAG, "getSubCategories response = " + responseCode);

                                    switch (responseCode) {
                                        case 200: {
                                            List<GetSubCategoryItem> list = listResponse.body();
                                            if (list != null) {
                                                if (list.isEmpty()) {
                                                    Log.d(TAG, "getSubCategories Error list is empty");
                                                } else {
                                                    Log.d(TAG, "getSubCategories is ok, size = " + list.size());
                                                    if (callback != null) {
                                                        callback.show(new ArrayList<>(list));
                                                    }
                                                }
                                            } else {
                                                Log.d(TAG, "getSubCategories Error list is null");
                                            }
                                            break;
                                        }
                                        case 400: {

                                        }
                                        case 404: {

                                        }
                                        case 405: {

                                        }
                                        default: {
                                            Log.d(TAG, "getSubCategories Error");
                                        }
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.d(TAG, "getSubCategories error", e);
                                }
                            })
            );
        } else if (category instanceof GetSubCategoryItem) {
            GetSubCategoryItem item = (GetSubCategoryItem) category;
            imagePath = GET_IMAGE_SUB_CATEGORY_URL + category.getImage();
        }

        Glide.with(context)
                .load(imagePath)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.i(TAG, "parseItems image(" + position + ") false");
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.i(TAG, "parseItems image(" + position + ") true");
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .error(R.drawable.non_image)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return filterListData.size();
    }

    @Override
    public Filter getFilter() {
        return new MyFilter(this);
    }

    public void addItem(Category data) {
        filterListData.add(data);
        notifyItemChanged(getItemCount() - 1);
    }

    public void addListItems(List<Category> data) {
        Log.d(TAG, "addListItems run");
        Log.d(TAG, "addListItems run old full list size = " + fullListData.size());
        Log.d(TAG, "addListItems run old filter list size = " + filterListData.size());
        filterListData = new ArrayList<>(data);
        fullListData = data;
        Log.d(TAG, "addListItems run new full list size = " + fullListData.size());
        Log.d(TAG, "addListItems run new filter list size = " + filterListData.size());
        notifyDataSetChanged();
    }

    public void removeAll() {
        filterListData = new ArrayList<>();
        notifyDataSetChanged();
    }

    public interface NewDataCallback {
        void show(List<Category> list);
    }

}

