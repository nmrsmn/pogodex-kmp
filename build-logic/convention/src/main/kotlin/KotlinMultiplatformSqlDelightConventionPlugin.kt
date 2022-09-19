@file:Suppress("UnstableApiUsage")

import com.squareup.sqldelight.gradle.SqlDelightExtension
import dev.nmrsmn.build.logic.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformSqlDelightConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.squareup.sqldelight")
            }

            extensions.configure<SqlDelightExtension> {
                database("PogedexCollector") {
                    packageName = "dev.nmrsmn.pogodex.db"
                }
            }
        }
    }
}