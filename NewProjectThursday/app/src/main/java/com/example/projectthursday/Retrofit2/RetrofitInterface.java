package com.example.projectthursday.Retrofit2;


import com.example.projectthursday.Retrofit2.Items.GetCategoryItem;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("/categories")
    Single<Response<List<GetCategoryItem>>> getCategories(@Query("id") Integer id, @Query("lang") String lang);

    @POST("/categories")
    Single<Response<String>> postCategories();
    @PUT("/categories")

    Single<Response<String>> putCategories();

    @DELETE("/categories")
    Single<Response<String>> deleteCategories();

    //...................................................

    @GET("/Subcategory")
    Single<Response<String>> getSubcategory();

    @POST("/Subcategory")
    Single<Response<String>> postSubcategory();

    @PUT("/Subcategory")
    Single<Response<String>> putSubcategory();

    @DELETE("/Subcategory")
    Single<Response<String>> deleteSubcategory();

    //...................................................

    @GET("/languages")
    Single<Response<String>> getLanguage();

    @POST("/languages")
    Single<Response<String>> postLanguage();

    @PUT("/languages")
    Single<Response<String>> putLanguage();

    @DELETE("/languages")
    Single<Response<String>> deleteLanguage();

    //...................................................

    @GET("/colors")
    Single<Response<String>> getColors();

    @POST("/colors")
    Single<Response<String>> postColors();

    @PUT("/colors")
    Single<Response<String>> putColors();

    @DELETE("/colors")
    Single<Response<String>> deleteColors();

    //...................................................

    @GET("/locale")
    Single<Response<String>> getLocate();

    @POST("/locale")
    Single<Response<String>> postLocate();

    @PUT("/locale")
    Single<Response<String>> putLocate();

    @DELETE("/locale")
    Single<Response<String>> deleteLocate();

    //...................................................
}
