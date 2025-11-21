package com.example.estiamapp.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.estiamapp.ui.theme.EstiamAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = { navigationIcon?.invoke() },
        actions = actions,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun AppTopBarPreview() {
    EstiamAppTheme {
        AppTopBar(title = "ESTIAM")
    }
}