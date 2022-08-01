@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.native.cocoapods)
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
        val commonMain by getting {
            dependencies {
                api(project(":shared:domain:pokemon:api"))

                implementation(project(":shared:domain:pokemon:implementation"))

                implementation(libs.koin.core)
            }
        }
        val commonTest by getting

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

    cocoapods {
        name = "PogodexLibrary"
        summary = "Shared library for the pogodex app"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../../ios/Podfile")
        framework {
            isStatic = false
            transitiveExport = true

            baseName = "PogodexLibrary"
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