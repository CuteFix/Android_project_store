package com.example.projectthursday.Retrofit2.Items;

import com.squareup.moshi.Json;

import lombok.Data;

@Data
public class GetSubCategoryItem implements Category{

    @Json(name = "id")
    private Integer id;
    @Json(name = "image")
    private String image;
    @Json(name = "name")
    private String name;

}