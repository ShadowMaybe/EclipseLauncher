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
    ":core:accounts",
    ":feature:home",
    ":feature:accounts",
    ":feature:downloads",
    ":feature:settings",
)
