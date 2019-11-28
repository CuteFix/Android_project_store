package com.example.projectthursday.Activities.MainActivity.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectthursday.Activities.MainActivity.Adapter.DataAdapter;
import com.example.projectthursday.R;
import com.example.projectthursday.Retrofit2.Items.Category;
import com.example.projectthursday.Retrofit2.Items.GetCategoryItem;
import com.example.projectthursday.Retrofit2.Items.GetSubCategoryItem;
import com.example.projectthursday.Retrofit2.ServerRequests.Requests;
import com.example.projectthursday.Utils.Language;
import com.example.projectthursday.Utils.ParsData.Colors;
import com.example.projectthursday.Utils.ParsData.Strings;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class BlankFragment1 extends Fragment {

    public static final String TAG = BlankFragment1.class.getCanonicalName();

    @BindView(R.id.recycle_view_categories)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_1_container)
    ViewGroup container;
    private DataAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        setBackgroundColor(Colors.getColorByName(getString(R.string.telegram_dark_hard)));

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        parseItems();
    }

    private void setBackgroundColor(int color) {
        container.setBackgroundColor(color);
    }

    public void show(List<Category> list) {
        if (list != null) {
            if (!list.isEmpty()) {
                Category category = list.get(0);
                if (getActivity() != null)
                    if (category instanceof GetCategoryItem) {

                        getActivity().setTitle(Strings.getByKey(getString(R.string.categories)));
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    } else if (category instanceof GetSubCategoryItem) {
                        getActivity().setTitle(Strings.getByKey(getString(R.string.subcategories)));
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    }
            }
        }
        adapter = new DataAdapter(list, this::show);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.blank_fragment1, container, false);
    }

    public void parseItems() {
        Requests.INSTANCE.getCategories(null, Language.get(), null)
                .subscribe(new SingleObserver<Response<List<GetCategoryItem>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Response<List<GetCategoryItem>> listResponse) {
                        Log.i(TAG, "parseItems onSuccess");
                        Log.i(TAG, "parseItems response = " + listResponse.code());

                        switch (listResponse.code()) {
                            case 200: {
                                List<GetCategoryItem> list = listResponse.body();
                                if (list != null) {
                                    show(new ArrayList<>(list));
                                }
                                break;
                            }
                            default: {

                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "parseItems onError");
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                parseItems();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteItems() {
        adapter.removeAll();
    }

    public void searchText(String searchText) {
        adapter.getFilter().filter(searchText);
    }
}
