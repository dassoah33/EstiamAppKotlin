package com.example.estiamapp.data.remote

import com.example.estiamapp.data.model.ProductDto
import com.example.estiamapp.data.model.UserDto
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}