package com.example.estiamapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.estiamapp.R
import com.example.estiamapp.ui.components.AppTopBar
import com.example.estiamapp.ui.users.UserCard
import com.example.estiamapp.ui.users.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen() {
    val vm: UsersViewModel = viewModel()
    val state by vm.state

    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = { vm.refresh() },
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isLoading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.padding(24.dp))
                }
            }
            state.error != null -> {
                Column(Modifier.fillMaxSize().padding(16.dp)) {
                    Text("Error: ${state.error}")
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { vm.load() }) {
                        Text("Try Again")
                    }
                }
            }
            else -> {
                val visible = state.all.take(state.visibleCount)

                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(visible, key = { _, u -> u.id }) { index, user ->
                        UserCard(user)

                        if (index == visible.lastIndex) {
                            LaunchedEffect(visible.size) { vm.loadMore() }
                        }
                    }

                    if (state.visibleCount < state.all.size) {
                        item {
                            Box (Modifier.fillMaxWidth().padding(16.dp)) {
                                LinearProgressIndicator(Modifier.fillMaxWidth())
                            }
                        }
                    }
                }
            }
        }
    }
}