package com.example.estiamapp.ui.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        _isAuthenticated.value = firebaseAuth.currentUser != null
    }

    private val _isAuthenticated = MutableStateFlow(auth.currentUser != null)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    init {
        auth.addAuthStateListener(authStateListener)
    }

    val currentUser get() = auth.currentUser

    fun login(email: String, password: String, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnFailureListener { onError(it.message ?: "Login failure!") }
    }

    fun register(email: String, password: String, onError: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email.trim(), password)
            .addOnFailureListener { onError(it.message ?: "Register failure!") }
    }

    fun logout() {
        auth.signOut()
    }

    override fun onCleared() {
        auth.removeAuthStateListener(authStateListener)
        super.onCleared()
    }
}