package com.example.projectthursday.Retrofit2.Items;

import com.squareup.moshi.Json;

import lombok.Data;

@Data
public class GetStringsItem {

    @Json(name = "id")
    private Integer id;

    @Json(name = "key")
    private String key;

    @Json(name = "value")
    private String value;
}
