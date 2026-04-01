plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.catsblock.catta"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.catsblock.catta"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.1" }

    // Logic to rename the APK file to catta.apk
    applicationVariants.all {
        outputs.all {
            val output = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            output.outputFileName = "catta.apk"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    
    // Material You & Compose Material 3
    implementation("androidx.compose.ui:ui:1.6.0")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("com.google.android.material:material:1.11.0")
    
    // Shizuku & Lifecycle
    implementation("dev.rikka.shizuku:api:13.1.0")
    implementation("dev.rikka.shizuku:provider:13.1.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    
    // Icon Loading
    implementation("io.coil-kt:coil-compose:2.5.0")
}
