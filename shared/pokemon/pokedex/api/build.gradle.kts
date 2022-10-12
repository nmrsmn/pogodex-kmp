plugins {
    id("dev.nmrsmn.kotlin.multiplatform.library")
    id("dev.nmrsmn.kotlin.multiplatform.detekt")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:core:util"))

                implementation(libs.bundles.coroutines)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.android.viewmodel)
            }
        }
    }
}

android {
    namespace = "dev.nmrsmn.pogodex.shared.pokemon.pokedex.api"
}