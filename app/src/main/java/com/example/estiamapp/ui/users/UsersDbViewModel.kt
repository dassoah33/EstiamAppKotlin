package com.example.estiamapp.ui.users

import android.app.Application
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.estiamapp.data.FirestoreRepository
import com.example.estiamapp.data.UserLocalRepository
import com.example.estiamapp.data.local.UserEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UsersDbViewModel(app: Application): AndroidViewModel(app) {

    private val repo = UserLocalRepository(app)
    private val remoteRepo = FirestoreRepository()

    val users = repo.observedUsers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun addUser(firstName: String, lastName: String, email: String) {
        viewModelScope.launch {
            // Add to local DB
            val entity = UserEntity(
                firstName = firstName.trim(),
                lastName = lastName.trim(),
                email = email.trim()
            )

            repo.addUser(entity)

            // Add to firestore
            runCatching { remoteRepo.addUser(entity) }
                .onFailure { it.printStackTrace() }
        }
    }

    fun clearAll() {
        viewModelScope.launch { repo.clear() }
    }
}