package com.example.projectthursday.Retrofit2.Items;

import androidx.annotation.NonNull;

import com.squareup.moshi.Json;

import lombok.Data;

@Data
public class Headers {

    @Json(name = "Accept")
    private String accept;
    @Json(name = "Accept-Encoding")
    private String acceptEncoding;
    @Json(name = "Accept-Language")
    private String acceptLanguage;
    @Json(name = "Dnt")
    private String dnt;
    @Json(name = "Host")
    private String host;
    @Json(name = "Upgrade-Insecure-Requests")
    private String upgradeInsecureRequests;
    @Json(name = "User-Agent")
    private String userAgent;

}