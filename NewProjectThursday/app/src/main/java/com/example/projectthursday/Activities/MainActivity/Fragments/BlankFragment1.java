package com.example.projectthursday.Activities.MainActivity.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectthursday.Activities.MainActivity.Adapter.DataAdapter;
import com.example.projectthursday.R;
import com.example.projectthursday.Retrofit2.Items.GetCategoryItem;
import com.example.projectthursday.ServerRequests.Requests;
import com.example.projectthursday.Utils.Language;

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
    private DataAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = new DataAdapter(new ArrayList<>());

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        parseItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.blank_fragment1, container, false);
    }

    public void parseItems() {
        Requests.INSTANCE.getCategories(null, Language.get())
                .subscribe(new SingleObserver<Response<List<GetCategoryItem>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Response<List<GetCategoryItem>> listResponse) {
                        Log.i(TAG,"parseItems onSuccess");
                        Log.i(TAG,"parseItems response = " + listResponse.code());

                        switch (listResponse.code()) {
                            case 200: {
                                List<GetCategoryItem> list = listResponse.body();
                                if (list != null) {
                                    adapter.addListItems(list);
                                }
                                break;
                            }
                            default:
                                Toast.makeText(getContext(),"ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,"parseItems onError");
                    }
                });


    }

    public void deleteItems() {
        adapter.removeAll();
    }

}
