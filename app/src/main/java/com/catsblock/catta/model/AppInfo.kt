package com.catsblock.catta.model

import android.graphics.drawable.Drawable

data class AppInfo(
    val name: String,
    val packageName: String,
    val icon: Drawable?,
    val isSystem: Boolean,
    val tags: List<String>,
    var isChecked: Boolean = false,
    val isUninstalled: Boolean = false
)

