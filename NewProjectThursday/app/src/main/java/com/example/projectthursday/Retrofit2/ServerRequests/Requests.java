package com.example.projectthursday.Retrofit2.ServerRequests;

import com.example.projectthursday.Retrofit2.ApiUtil;
import com.example.projectthursday.Retrofit2.Items.ColorItem;
import com.example.projectthursday.Retrofit2.Items.GetCategoryItem;
import com.example.projectthursday.Retrofit2.Items.GetStringsItem;
import com.example.projectthursday.Retrofit2.Items.GetSubCategoryItem;
import com.example.projectthursday.Retrofit2.RetrofitInterface;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public enum Requests {

    INSTANCE;

    private final RetrofitInterface retrofitInterface = ApiUtil.INSTANCE.getServiceClass();

    public Single<Response<List<ColorItem>>> getColors() {
        return retrofitInterface.getColors()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public  Single<Response<List<GetCategoryItem>>> getCategories(Integer id, String lang, Boolean admin) {
        return retrofitInterface.getCategories(id, lang)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public  Single<Response<List<GetSubCategoryItem>>> getSubCategories(Integer catId, String lang, Boolean admin) {
        return retrofitInterface.getSubcategories(catId, lang, admin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Single<Response<List<GetStringsItem>>> getStrings(String lang, Boolean admin) {
        return retrofitInterface.getStrings(lang, admin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}
