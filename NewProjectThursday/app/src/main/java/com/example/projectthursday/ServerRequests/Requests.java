package com.example.projectthursday.ServerRequests;

import com.example.projectthursday.Retrofit2.ApiUtil;
import com.example.projectthursday.Retrofit2.Items.HeadersTest;
import com.example.projectthursday.Retrofit2.Items.ItemIp;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public enum  Requests {

    INSTANCE;

    public  Single<Response<String>> getColors(){
        return ApiUtil.INSTANCE.getServiceClass().getColors()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}
