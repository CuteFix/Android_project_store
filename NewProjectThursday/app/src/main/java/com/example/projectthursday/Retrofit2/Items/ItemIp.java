package com.example.projectthursday.Retrofit2.Items;

import androidx.annotation.NonNull;

import com.squareup.moshi.Json;

public class ItemIp {

    @Json(name ="origin")
    private String origin;

    @NonNull
    @Override
    public String toString() {
        return "Ip = " + origin;
    }
}
