package com.example.projectthursday.Retrofit2.Items;

import com.squareup.moshi.Json;

import lombok.Data;

@Data
public class ColorItem {

    @Json(name = "col_key")
    private String key;

    @Json(name = "value")
    private String value;

}
