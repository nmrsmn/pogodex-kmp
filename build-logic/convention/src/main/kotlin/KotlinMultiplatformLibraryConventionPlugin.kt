@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.LibraryExtension
import dev.nmrsmn.build.logic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformLibraryConventionPlugin: Plugin<Project> {
    private val nativeTargets = setOf(
        "iosArm64",
        "iosX64",
        "iosSimulatorArm64"
    )

    private val removeSourceSets = setOf(
        "androidAndroidTestRelease",
        "androidTestFixtures",
        "androidTestFixturesDebug",
        "androidTestFixturesRelease"
    )

    private val optIns = setOf(
        "kotlin.Experimental",
        "kotlin.RequiresOptIn",
        "kotlinx.coroutines.ExperimentalCoroutinesApi",
        "kotlinx.coroutines.FlowPreview",
        "kotlinx.serialization.ExperimentalSerializationApi",
        "kotlin.experimental.ExperimentalTypeInference"
    )

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                android()
                iosArm64()
                iosX64()
                iosSimulatorArm64()

                val commonMain = sourceSets.getByName("commonMain")
                val commonTest = sourceSets.getByName("commonTest")

                val darwinMain = sourceSets.create("darwinMain") {
                    dependsOn(commonMain)
                }

                val darwinTest = sourceSets.create("darwinTest") {
                    dependsOn(commonTest)
                }

                with(nativeTargets) {
                    map { "${it}Main" }.forEach { sourceSets.getByName(it).dependsOn(darwinMain) }
                    map { "${it}Test" }.forEach { sourceSets.getByName(it).dependsOn(darwinTest) }
                }

                sourceSets.removeIf { set -> removeSourceSets.contains(set.name) }

                sourceSets.all {
                    languageSettings.apply {
                        optIns.forEach { annotation ->
                            optIn(annotation)
                        }
                    }
                }
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                sourceSets.getByName("main") {
                    manifest {
                        srcFile("src/androidMain/AndroidManifest.xml")
                    }
                }
            }

            /**
             * Disable iosTest Task for now. Using mockk causes the build to fail. Revisit later.
             */
            gradle.startParameter.excludedTaskNames.addAll(
                listOf(
                    "compileTestKotlinIosSimulatorArm64",
                    "compileTestKotlinIosArm64",
                    "compileTestKotlinIosX64"
                )
            )
        }
    }
}