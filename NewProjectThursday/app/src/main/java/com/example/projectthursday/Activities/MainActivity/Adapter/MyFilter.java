package com.example.projectthursday.Activities.MainActivity.Adapter;

import android.widget.Filter;

import com.example.projectthursday.Retrofit2.Items.GetCategoryItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

class MyFilter extends Filter {

    private DataAdapter adapter;

    MyFilter(DataAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        String searchText = charSequence.toString();
        List<GetCategoryItem> resultList = new ArrayList<>();

        Observable.fromIterable(adapter.getFullListData())
                .filter(x -> x.getName().toLowerCase().contains(searchText.toLowerCase()))
                .doOnNext(resultList::add)
                .subscribe();

        FilterResults filterResults = new FilterResults();
        filterResults.values = resultList;
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.setFilterListData((ArrayList<GetCategoryItem>) filterResults.values);
        adapter.notifyDataSetChanged();
    }

}