package com.example.projectthursday.Retrofit2.ServerRequests;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.projectthursday.Retrofit2.Items.ColorItem;
import com.example.projectthursday.Retrofit2.Items.GetStringsItem;
import com.example.projectthursday.Utils.Language;

import java.util.List;

public enum RequestsComplete {

    INSTANCE;

    private final static String TAG = RequestsComplete.class.getCanonicalName();

    @SuppressLint("CheckResult")
    public void getColors(DoOnGetColors doOnGetColors) {
        Requests.INSTANCE.getColors().subscribe(listResponse -> {
            switch (listResponse.code()) {
                case 200: {
                    if (listResponse.body() != null) {
                        List<ColorItem> list = listResponse.body();
                        doOnGetColors.complete(list);
                    }
                    break;
                }
                default: {
                    Log.d(TAG, "getColors ERROR, code = " + listResponse.code());
                }
            }
        }, throwable -> Log.d(TAG, "getColors ERROR", throwable));
    }

    public interface DoOnGetColors {
        public void complete(List<ColorItem> list);
    }

    @SuppressLint("CheckResult")
    public void getStrings(DoOnGetStrings doOnGetStrings) {
        Requests.INSTANCE.getStrings(Language.get(), false).subscribe(listResponse -> {
            switch (listResponse.code()) {
                case 200: {
                    if (listResponse.body() != null) {
                        List<GetStringsItem> list = listResponse.body();
                        doOnGetStrings.complete(list);
                    }
                    break;
                }
                default: {
                    Log.d(TAG, "getStrings ERROR, code = " + listResponse.code());
                }
            }
        }, throwable -> Log.d(TAG, "getStrings ERROR", throwable));
    }

    public interface DoOnGetStrings {
        public void complete(List<GetStringsItem> list);
    }
}
