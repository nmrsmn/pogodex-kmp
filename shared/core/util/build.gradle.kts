plugins {
    id("dev.nmrsmn.kotlin.multiplatform.library")
    id("dev.nmrsmn.kotlin.multiplatform.detekt")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.bundles.coroutines)
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
                implementation(libs.android.viewmodel)
            }
        }
    }
}

android {
    namespace = "dev.nmrsmn.pogodex.shared.core.util"
}

dependencies {
    implementation("androidx.annotation:annotation:1.5.0")
}
