@file:Suppress("UnstableApiUsage")

package dev.nmrsmn.build.logic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureAndroidCompose(
    extension: CommonExtension<*, *, *, *>
) {
    extension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = version("android.compose.compiler")
        }
    }
}