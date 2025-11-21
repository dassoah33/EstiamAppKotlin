package com.example.estiamapp.data.model

data class ProductDto (
    val id: Int,
    val title: String,
    val slug: String,
    val price: Double,
    val description: String,
    val category: CategoryDto,
    val images: List<String> = emptyList(),
    val creationAt: String,
    val updatedAt: String,
)