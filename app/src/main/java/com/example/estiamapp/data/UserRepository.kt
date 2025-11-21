package com.example.estiamapp.data

import com.example.estiamapp.data.model.UserDto
import com.example.estiamapp.data.remote.ApiClient

class UserRepository {
    suspend fun fetchUsers(): List<UserDto> = ApiClient.api.getUsers()
}