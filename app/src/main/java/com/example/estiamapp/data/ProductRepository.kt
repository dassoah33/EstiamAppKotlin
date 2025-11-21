package com.example.estiamapp.data

import com.example.estiamapp.data.model.ProductDto
import com.example.estiamapp.data.remote.ApiClient

class ProductRepository {
    suspend fun fetchProducts(): List<ProductDto> = ApiClient.api.getProducts()
}