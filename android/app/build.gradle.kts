@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    compileSdk = libs.versions.android.sdk.compile.int()

    defaultConfig {
        applicationId = "dev.nmrsmn.pokedex"

        versionCode = 1
        versionName = "1.0.0"

        minSdk = libs.versions.android.sdk.min.int()
        targetSdk = libs.versions.android.sdk.target.int()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.android.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":shared:shared"))

    implementation(libs.bundles.compose)
}