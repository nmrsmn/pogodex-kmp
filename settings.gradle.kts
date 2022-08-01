enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "PogodexCollector"

include(
    ":shared:library",
    ":shared:domain:pokemon:api",
    ":shared:domain:pokemon:implementation",

    ":android:app"
)