package com.example.estiamapp.data

import android.content.Context
import com.example.estiamapp.data.local.AppDatabase
import com.example.estiamapp.data.local.UserEntity

class UserLocalRepository (context: Context) {
    private val dao = AppDatabase.get(context).userDao()

    fun observedUsers() = dao.observedUsers()

    suspend fun addUser(user: UserEntity) = dao.insert(user)

    suspend fun clear() = dao.clear()
}