package com.example.estiamapp.data.model

data class UserDto (
    val id: Int,
    val email: String,
    val name: String,
    val password: String,
    val role: String,
    val avatar: String,
    val creationAt: String,
    val updatedAt: String
)