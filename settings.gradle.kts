@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

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
    ":shared:core:database",

    ":shared:pokemon:pokedex:api",
    ":shared:pokemon:pokedex:implementation",

    ":android:app"
)