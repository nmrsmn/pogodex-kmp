@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("dev.nmrsmn.android.application")
    id("dev.nmrsmn.android.application.compose")
    id("dev.nmrsmn.kotlin.detekt")
}

android {
    defaultConfig {
        applicationId = "dev.nmrsmn.pokedex"

        versionCode = 1
        versionName = "1.0.0"
    }
}

dependencies {
    implementation(project(":shared:library"))
    implementation(project(":shared:core:database"))
    implementation(project(":shared:core:util"))
    implementation(project(":shared:pokemon:pokedex:implementation"))

    implementation(libs.android.viewmodel)

    implementation(libs.koin.core)
    implementation(libs.koin.android)

    implementation(libs.bundles.compose)
}