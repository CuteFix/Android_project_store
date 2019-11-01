package com.example.projectthursday.Retrofit2.Items;

import com.squareup.moshi.Json;

import lombok.Data;

@Data
public class HeadersTest {

@Json(name = "headers")
private Headers headers;

}