@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    android()
    ios()
}

android {
    compileSdk = libs.versions.android.sdk.compile.int()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.int()
        targetSdk = libs.versions.android.sdk.target.int()
    }
}