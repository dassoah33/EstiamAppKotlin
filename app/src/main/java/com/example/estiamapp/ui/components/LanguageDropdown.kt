package com.example.estiamapp.ui.components

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import com.example.estiamapp.R

@Composable
fun LanguageDropdown (modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val activity = context as? android.app.Activity

    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        "en" to R.string.lang_en,
        "fr" to R.string.lang_fr
    )

    Box (modifier) {
        Button(onClick = { expanded = true }) {
            Text(stringResource(R.string.choose_language))
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false}) {
            items.forEach {
                (tag, labelId) ->
                DropdownMenuItem(
                    text = { Text(stringResource(labelId)) },
                    onClick = {
                        expanded = false
                        val locales = LocaleListCompat.forLanguageTags(tag)
                        AppCompatDelegate.setApplicationLocales(locales)
                        activity?.recreate()
                    }
                )
            }
        }
    }
}