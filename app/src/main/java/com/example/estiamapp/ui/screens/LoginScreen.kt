package com.example.estiamapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.estiamapp.R
import com.example.estiamapp.ui.auth.AuthViewModel

/**
 * LoginScreen avec validation UI complète
 *
 * Validations:
 * - Email doit être formaté (contenir @ et .)
 * - Mot de passe doit contenir au moins 6 caractères
 * - Messages d'erreur en temps réel
 * - Bouton désactivé si validation échoue
 */
@Composable
fun LoginScreen(onNavigateRegister: () -> Unit, onLoggedIn: () -> Unit) {
    val vm: AuthViewModel = viewModel()
    val isAuthenticated by vm.isAuthenticated.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }

    // Validation states
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // Validation en temps réel
    fun validateEmail(value: String): String {
        return when {
            value.isEmpty() -> "Email requis"
            !value.contains("@") -> "Email doit contenir @"
            !value.contains(".") -> "Email doit contenir un domaine"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches() -> "Format email invalide"
            else -> ""
        }
    }

    fun validatePassword(value: String): String {
        return when {
            value.isEmpty() -> "Mot de passe requis"
            value.length < 6 -> "Mot de passe doit contenir au moins 6 caractères"
            else -> ""
        }
    }

    val isFormValid = email.isNotEmpty() &&
            password.isNotEmpty() &&
            emailError.isEmpty() &&
            passwordError.isEmpty()

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            onLoggedIn()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Login",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        // Email field avec validation
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = validateEmail(it)
            },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Filled.Email, "Email") },
            isError = emailError.isNotEmpty(),
            supportingText = {
                if (emailError.isNotEmpty()) {
                    Text(
                        emailError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Password field avec validation
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = validatePassword(it)
            },
            label = { Text("Mot de passe") },
            leadingIcon = { Icon(Icons.Filled.Lock, "Password") },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        "Toggle password visibility"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = passwordError.isNotEmpty(),
            supportingText = {
                if (passwordError.isNotEmpty()) {
                    Text(
                        passwordError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Login button
        Button(
            onClick = {
                emailError = validateEmail(email)
                passwordError = validatePassword(password)

                if (isFormValid) {
                    vm.login(email.trim(), password) { error = it }
                }
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Se connecter")
        }

        // Firebase error message
        if (error.isNotEmpty()) {
            Text(
                error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Register navigation
        TextButton(
            onClick = onNavigateRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pas de compte ? S'inscrire")
        }
    }
}