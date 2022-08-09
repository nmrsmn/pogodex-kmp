@file:Suppress("UnstableApiUsage")

package dev.nmrsmn.build.logic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureKotlinAndroid(
    extension: CommonExtension<*, *, *, *>
) {
    extension.apply {
        compileSdk = versionInt("android.sdk.compile")

        defaultConfig {
            minSdk = versionInt("android.sdk.min")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.Experimental",
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
            )

            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    with(this as ExtensionAware) {
        extensions.configure("kotlinOptions", block)
    }
}