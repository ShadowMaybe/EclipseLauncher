pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EclipseLauncher"

include(
    ":app",
    ":core:designsystem",
    ":feature:home",
    ":feature:downloads",
    ":feature:settings",
)
