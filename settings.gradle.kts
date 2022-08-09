@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "PogodexCollector"

include(
    ":shared:library",

    ":shared:core:util",

    ":shared:domain:pokemon:api",
    ":shared:domain:pokemon:implementation",

    ":android:app"
)