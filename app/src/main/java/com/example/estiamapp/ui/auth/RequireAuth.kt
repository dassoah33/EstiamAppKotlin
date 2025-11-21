package com.example.estiamapp.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun RequireAuth (
    navController: NavController,
    content: @Composable () -> Unit
) {
    val vmAuth: AuthViewModel = viewModel()
    val isAuthed by vmAuth.isAuthenticated.collectAsState()

    LaunchedEffect(isAuthed) {
        if (!isAuthed) {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute != "login" && currentRoute != "register") {
                navController.navigate("login") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                        inclusive = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
        }
    }

    if (isAuthed) {
        content()
    }
}