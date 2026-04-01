package com.catsblock.catta.ui.theme

import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun CattaTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicDarkColorScheme(context)
    } else {
        darkColorScheme()
    }
    MaterialTheme(colorScheme = colorScheme, content = content)
}

