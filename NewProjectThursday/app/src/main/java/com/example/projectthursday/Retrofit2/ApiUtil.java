package com.example.projectthursday.Retrofit2;

import com.example.projectthursday.ServerRequests.Requests;

public enum ApiUtil {

    INSTANCE;

    public RetrofitInterface getServiceClass(){
        return RetrofitAPI.getRetrofit("https://thursday.ml/").create(RetrofitInterface.class);
    }

}
