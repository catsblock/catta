package com.catsblock.catta.logic

import dev.rikka.shizuku.Shizuku
import java.io.BufferedReader
import java.io.InputStreamReader

object ShizukuManager {
    fun isAvailable(): Boolean = Shizuku.pingBinder() && Shizuku.checkSelfPermission() == 0

    fun uninstallApp(packageName: String, callback: (Boolean) -> Unit) {
        if (!isAvailable()) {
            callback(false)
            return
        }
        try {
            val process = Shizuku.newProcess(arrayOf("pm", "uninstall", "--user", "0", packageName), null, null)
            val exitCode = process.waitFor()
            callback(exitCode == 0)
        } catch (e: Exception) {
            callback(false)
        }
    }
}

