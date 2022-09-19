@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("dev.nmrsmn.kotlin.multiplatform.library")
    id("dev.nmrsmn.kotlin.multiplatform.cocoapods")
    id("dev.nmrsmn.kotlin.multiplatform.detekt")
    id("dev.nmrsmn.kotlin.multiplatform.sqldelight")
}

version = libs.versions.shared.library.get()

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":shared:domain:pokemon:api"))

                implementation(project(":shared:core:util"))

                api(project(":shared:domain:pokemon:implementation"))

                implementation(libs.bundles.coroutines)
                implementation(libs.bundles.sqldelight)
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.kotlin.multiplatform.test)
                implementation(libs.mockk.common)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.sqldelight.android)
            }
        }

        val darwinMain by getting {
            dependencies {
                implementation(libs.sqldelight.native)
            }
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

            export(project(":shared:domain:pokemon:implementation"))
        }
    }
}