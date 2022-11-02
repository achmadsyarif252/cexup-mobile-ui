pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com")
    }
}
rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "Cexup UI Android"
include(":app")
include(":lib")

