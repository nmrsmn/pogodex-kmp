@file:Suppress("ConflictingExtensionProperty")

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.the

val Project.libs get() = the<LibrariesForLibs>()
fun Provider<String>.int(): Int = get().toInt()