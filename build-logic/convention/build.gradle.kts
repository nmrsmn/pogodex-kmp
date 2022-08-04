@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

group = "dev.nmrsmn.buildlogic"

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
}

gradlePlugin {
    val namespace = "dev.nmrsmn"

    plugins {
        register("androidApplication") {
            id = "${namespace}.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidComposeApplication") {
            id = "${namespace}.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "${namespace}.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("kotlinMultiplatformLibrary") {
            id = "${namespace}.kotlin.multiplatform.library"
            implementationClass = "KotlinMultiplatformLibraryConventionPlugin"
        }
    }
}