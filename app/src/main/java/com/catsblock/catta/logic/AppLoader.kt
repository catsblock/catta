package com.catsblock.catta.logic

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.catsblock.catta.model.AppInfo

class AppLoader(private val context: Context) {
    private val blacklist = listOf(
        "com.android.settings", 
        "com.android.systemui", 
        "com.miui.securitycenter",
        "com.google.android.packageinstaller"
    )

    fun loadApps(onProgress: (String) -> Unit): List<AppInfo> {
        val pm = context.packageManager
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        
        return packages.filter { it.packageName !in blacklist }.map { app ->
            onProgress(app.packageName)
            val isSystem = (app.flags and ApplicationInfo.FLAG_SYSTEM) != 0
            AppInfo(
                name = app.loadLabel(pm).toString(),
                packageName = app.packageName,
                icon = app.loadIcon(pm),
                isSystem = isSystem,
                tags = getTags(app, isSystem)
            )
        }.sortedBy { it.name }
    }

    private fun getTags(app: ApplicationInfo, isSystem: Boolean): List<String> {
        val tags = mutableListOf<String>()
        val pName = app.packageName.lowercase()
        
        if (isSystem) tags.add("System")
        if (pName.contains("daemon") || pName.contains("analytics") || pName.contains("spyware") || pName.contains("tracking")) tags.add("Nonsafe")
        if (pName.contains("facebook") || pName.contains("xiaomi.midrop") || pName.contains("mi_video")) tags.add("Recommended")
        if (isSystem && !pName.contains("miui") && !pName.contains("android")) tags.add("Advanced")
        if (tags.isEmpty()) tags.add("Normal")
        
        return tags
    }
}

