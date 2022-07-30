@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
}

version = libs.versions.shared.library.get()

kotlin {
    android()
    iosArm32()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    val nativeTargets = listOf(
        "iosArm32",
        "iosArm64",
        "iosX64",
        "iosSimulatorArm64"
    )

    sourceSets {
        val commonMain by getting
        val commonTest by getting

        val androidMain by getting
        val androidTest by getting

        val darwinMain by creating {
            dependsOn(commonMain)
        }

        val darwinTest by creating {
            dependsOn(commonTest)
        }

        with(nativeTargets) {
            map { "${it}Main" }.forEach { getByName(it).dependsOn(darwinMain) }
            map { "${it}Test" }.forEach { getByName(it).dependsOn(darwinTest) }
        }
    }
}

android {
    compileSdk = libs.versions.android.sdk.compile.int()

    namespace = "dev.nmrsmn.pogodex.shared"

    sourceSets.getByName("main") {
        manifest {
            srcFile("src/androidMain/AndroidManifest.xml")
        }
    }

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.int()
        targetSdk = libs.versions.android.sdk.target.int()
    }
}