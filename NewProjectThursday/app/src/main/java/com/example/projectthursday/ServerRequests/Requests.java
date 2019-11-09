package com.example.projectthursday.ServerRequests;

import com.example.projectthursday.Retrofit2.ApiUtil;
import com.example.projectthursday.Retrofit2.Items.GetCategoryItem;
import com.example.projectthursday.Retrofit2.RetrofitInterface;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public enum Requests {

    INSTANCE;

    private final RetrofitInterface retrofitInterface = ApiUtil.INSTANCE.getServiceClass();

    public Single<Response<String>> getColors() {
        return retrofitInterface.getColors()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public  Single<Response<List<GetCategoryItem>>> getCategories(Integer id, String lang) {
        return retrofitInterface.getCategories(id, lang)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}
