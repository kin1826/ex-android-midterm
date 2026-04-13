package com.example.midterm_project.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import com.example.midterm_project.model.Product

interface ApiService {

    @GET("getProducts.php")
    fun getProducts(): Call<List<Product>>

    @FormUrlEncoded
    @POST("insert.php")
    fun insertProduct(
        @Field("name") name: String,
        @Field("description") desc: String,
        @Field("price") price: String,
        @Field("image") image: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteProduct(
        @Field("id") id: Int
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("update.php")
    fun updateProduct(
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("description") desc: String,
        @Field("price") price: String,
        @Field("image") image: String
    ): Call<ResponseBody>
}