package com.example.foodexpiryscanner.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BackendApi {
    @GET("api/items")
    suspend fun getItems(): Response<List<BackendItemDto>>

    @POST("api/items")
    suspend fun createItem(@Body item: BackendItemDto): Response<BackendItemDto>

    @PUT("api/items/{id}")
    suspend fun updateItem(@Path("id") id: String, @Body item: BackendItemDto): Response<BackendItemDto>

    @DELETE("api/items/{id}")
    suspend fun deleteItem(@Path("id") id: String): Response<Unit>
}
