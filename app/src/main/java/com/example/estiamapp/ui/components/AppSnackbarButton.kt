package com.example.estiamapp.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.estiamapp.ui.theme.EstiamAppTheme
import kotlinx.coroutines.launch

@Composable
fun AppSnackbarButton(
    snackbarHostState: SnackbarHostState,
    label: String,
    message: String,
    actionLabel: String? = null
) {
    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel
                )
            }
        }
    ) {
        Text(label)
    }
}

@Preview(showBackground = true)
@Composable
fun AppSnackbarButtonPreview() {
    EstiamAppTheme {

    }
}