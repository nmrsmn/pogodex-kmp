package dev.nmrsmn.build.logic

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.versions(catalog: String) = extensions
    .getByType<VersionCatalogsExtension>()
    .named(catalog)

fun Project.version(alias: String, catalog: String = "libs"): String
    = versions(catalog).findVersion(alias).get().requiredVersion

fun Project.versionInt(alias: String, catalog: String = "libs"): Int
    = versions(catalog).findVersion(alias).get().requiredVersion.toInt()