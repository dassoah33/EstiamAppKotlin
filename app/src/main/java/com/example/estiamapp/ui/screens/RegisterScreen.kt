package com.example.estiamapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.estiamapp.ui.auth.AuthViewModel

@Composable
fun RegisterScreen (onNavigateLogin: () -> Unit, onRegistered: () -> Unit) {
    val vm: AuthViewModel = viewModel()
    val isAuthenticated by vm.isAuthenticated.collectAsState()

    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            onRegistered()
        }
    }

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Register", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(email, { email = it }, label = { Text("Email") })
        OutlinedTextField(pass, { pass = it }, label = { Text("Password") })

        Button(
            onClick = {
                vm.register(email, pass) { error = it }
            }
        ) { Text("Register") }

        Text(error, color = MaterialTheme.colorScheme.error)

        TextButton(onClick = onNavigateLogin) {
            Text("If you have account: Login")
        }
    }
}