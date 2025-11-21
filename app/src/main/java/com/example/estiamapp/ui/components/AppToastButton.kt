package com.example.estiamapp.ui.components

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.estiamapp.ui.theme.EstiamAppTheme

@Composable
fun AppToastButton(
    label: String,
    message: String
) {
    val context = LocalContext.current

    Button(
        onClick = {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    ) {
        Text(label)
    }
}

@Preview(showBackground = true)
@Composable
fun AppToastButtonPreview() {
    EstiamAppTheme {
        AppToastButton(
            label = "Display Toast",
            message = "Heeeey toast works!!!"
        )
    }
}