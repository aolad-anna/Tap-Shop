package com.example.tap_shop.network


import com.example.tap_shop.model.response.GetAllCategories
import com.example.tap_shop.model.response.GetProduct
import retrofit2.http.*


interface ApiInterface {
    @GET
    suspend fun getAllCategories(
        @Url url: String?
    ): List<GetAllCategories>

    @GET
    suspend fun getProducts(
        @Url url: String?
    ): List<GetProduct>
}