package com.example.projectthursday.Activities.MainActivity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectthursday.Activities.MainActivity.Adapter.DataAdapter;
import com.example.projectthursday.Activities.MainActivity.Adapter.Item.Data;
import com.example.projectthursday.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlankFragment1 extends Fragment {

    public static final String TAG = BlankFragment1.class.getCanonicalName();

    @BindView(R.id.recycle_view_categories) RecyclerView recyclerView;
    private DataAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = new DataAdapter(new ArrayList<>());

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.blank_fragment1, container, false);
    }

    public void addItem(){
       adapter.addListItem(new Data());
    }

    public void deleteItem(){
        adapter.removeLastItem();
    }

}
