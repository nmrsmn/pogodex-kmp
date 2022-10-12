@file:Suppress("UnstableApiUsage")

import com.squareup.sqldelight.gradle.SqlDelightExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KotlinMultiplatformSqlDelightConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.squareup.sqldelight")
            }

            extensions.configure<SqlDelightExtension> {
                database("PogedexCollector") {
                    packageName = "dev.nmrsmn.pogodex.database"
                    sourceFolders = listOf("sqldelight")
                }
            }
        }
    }
}