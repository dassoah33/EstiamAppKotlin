package com.example.estiamapp.ui.screens

import android.Manifest
import android.os.Build
import androidx.annotation.StringRes
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.estiamapp.Greeting
import com.example.estiamapp.R
import com.example.estiamapp.notifications.NotificationHelper
import com.example.estiamapp.ui.auth.RequireAuth
import com.example.estiamapp.ui.components.AppSnackbarButton
import com.example.estiamapp.ui.components.AppToastButton
import com.example.estiamapp.ui.components.AppTopBar
import com.example.estiamapp.ui.components.LanguageDropdown
import com.example.estiamapp.ui.theme.EstiamAppTheme
import com.example.estiamapp.work.enqueueOneTimeAfter10Sec
import com.example.estiamapp.work.enqueueOneTimeWifiCharging

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = AppDestination.fromRoute(backStackEntry?.destination?.route)
    val bottomDestinations = remember {
        listOf(AppDestination.Home, AppDestination.Products, AppDestination.Users, AppDestination.DbUsers)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = stringResource(id = currentDestination.titleRes),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(AppDestination.Settings.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        enabled = currentDestination != AppDestination.Settings
                    ) {
                        Icon(
                            imageVector = AppDestination.Settings.icon,
                            contentDescription = stringResource(id = R.string.settings_action_description)
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar {
                bottomDestinations.forEach { destination ->
                    NavigationBarItem(
                        selected = currentDestination.route == destination.route,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = stringResource(id = destination.labelRes)
                            )
                        },
                        label = { Text(text = stringResource(id = destination.labelRes)) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppDestination.Home.route) {
                HomeScreen(
                    snackbarHostState = snackbarHostState
                )
            }

            composable(AppDestination.Products.route) {
                RequireAuth(navController) {
                    ProductsScreen()
                }
            }
            composable(AppDestination.Users.route) {
                RequireAuth(navController) {
                    UsersScreen()
                }
            }
            composable(AppDestination.DbUsers.route) {
                RequireAuth(navController) {
                    UsersDbScreen()
                }
            }
            composable(AppDestination.Settings.route) {
                RequireAuth(navController) {
                    SettingsScreen()
                }
            }

            composable("login") {
                LoginScreen(
                    onNavigateRegister = { navController.navigate("register")},
                    onLoggedIn = {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }

            composable("register") {
                RegisterScreen(
                    onNavigateLogin = { navController.navigate("login")},
                    onRegistered = {
                        navController.navigate("home") {
                            popUpTo("register") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun HomeScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Greeting(name = Build.BRAND)

        AppToastButton(
            label = "Display Toast",
            message = "Toast works!!!"
        )

        AppSnackbarButton(
            snackbarHostState = snackbarHostState,
            label = "Display Snackbar",
            message = "Snackbar works!!",
            actionLabel = "Confirm"
        )

        NotificationSection()

        LanguageDropdown()

        Button(
            onClick = { enqueueOneTimeAfter10Sec(context) }
        ) {
            Text("One Time Action - 10 sec")
        }

        Button(
            onClick = { enqueueOneTimeWifiCharging(context) }
        ) {
            Text("Action - Wifi & Charging")
        }
    }
}

@Composable
fun NotificationSection() {
    val context = LocalContext.current

    var hasPermission by remember {
        mutableStateOf(
            Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(
                context, Manifest.permission.POST_NOTIFICATIONS
            ) == PermissionChecker.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        hasPermission = granted
    }

    Button(
        onClick = {
            if (Build.VERSION.SDK_INT >= 33 && !hasPermission) {
                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                NotificationHelper.show(
                    context,
                    title = "Test event",
                    message = "This is a notification"
                )
            }
        }
    ) {
        Text("Send notification")
    }
}

@Preview(
    showBackground = true,
    name = "Phone - Light",
    device = Devices.PHONE,
    locale = "fr"
)
@Composable
fun MainScreenPreview() {
    EstiamAppTheme {
        MainScreen()
    }
}

private sealed class AppDestination(
    val route: String,
    @StringRes val titleRes: Int,
    @StringRes val labelRes: Int,
    val icon: ImageVector
) {
    data object Home : AppDestination(
        route = "home",
        titleRes = R.string.main_title,
        labelRes = R.string.main_tab,
        icon = Icons.Filled.Home
    )

    data object Products : AppDestination(
        route = "products",
        titleRes = R.string.products_title,
        labelRes = R.string.products_tab,
        icon = Icons.Filled.ShoppingCart
    )

    data object Users : AppDestination(
        route = "users",
        titleRes = R.string.users_title,
        labelRes = R.string.users_tab,
        icon = Icons.Filled.Group
    )

    data object DbUsers : AppDestination(
        route = "dbUsers",
        titleRes = R.string.users_db_title,
        labelRes = R.string.users_db_tab,
        icon = Icons.Filled.Group
    )

    data object Settings : AppDestination(
        route = "settings",
        titleRes = R.string.settings_title,
        labelRes = R.string.settings_title,
        icon = Icons.Filled.Settings
    )

    companion object {
        fun fromRoute(route: String?): AppDestination = when (route) {
            Home.route -> Home
            Products.route -> Products
            Users.route -> Users
            DbUsers.route -> DbUsers
            Settings.route -> Settings
            else -> Home
        }
    }
}