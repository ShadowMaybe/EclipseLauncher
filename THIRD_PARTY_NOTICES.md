# Third-party notices

Eclipse Launcher is licensed under the Apache License, Version 2.0. This file records direct third-party build, runtime, and test dependencies used by the current application. It is not a substitute for reviewing each dependency's complete license text or for generating the final transitive software bill of materials.

## Build tooling

| Component | Version | License | Source | Use |
|---|---:|---|---|---|
| Android Gradle Plugin | 9.4.1 | Apache-2.0 | https://developer.android.com/build/releases/agp-9-4-0-release-notes | Android build system |
| Kotlin Android and Compose compiler plugins | 2.4.20 | Apache-2.0 | https://kotlinlang.org/docs/releases.html | Kotlin Android compilation and Compose compiler integration |
| Gradle | 9.6.0 | Apache-2.0 | https://docs.gradle.org/9.6.0/release-notes.html | Build system and wrapper distribution |
| JUnit | 4.13.2 | Eclipse Public License 1.0 | https://junit.org/junit4/ | Local unit-test framework |

## Android runtime and UI

| Component | Version | License | Source | Use |
|---|---:|---|---|---|
| AndroidX Activity Compose | 1.13.0 | Apache-2.0 | https://developer.android.com/jetpack/androidx/releases/activity | Activity host and Compose integration |
| AndroidX Navigation Compose | 2.10.1 | Apache-2.0 | https://developer.android.com/jetpack/androidx/releases/navigation | Top-level back-stack and saved-state navigation |
| Compose BOM | 2026.09.00 | Apache-2.0 | https://developer.android.com/develop/ui/compose/bom | Coordinates compatible Compose runtime, UI, and Material 3 artifacts |
| Compose Material 3 | BOM-managed | Apache-2.0 | https://developer.android.com/jetpack/androidx/releases/compose-material3 | Material 3 components and theming |
| Compose UI and tooling | BOM-managed | Apache-2.0 | https://developer.android.com/jetpack/androidx/releases/compose-ui | Compose runtime, semantics, preview, and debug tooling |
| AndroidX Test runner | 1.7.0 | Apache-2.0 | https://developer.android.com/jetpack/androidx/releases/test | Connected Android test runner |
| AndroidX Test JUnit extensions | 1.3.0 | Apache-2.0 | https://developer.android.com/jetpack/androidx/releases/test | JUnit integration for connected tests |
| AndroidX Test Espresso | 3.7.0 | Apache-2.0 | https://developer.android.com/jetpack/androidx/releases/test | Android UI test infrastructure |

## CI-only tools

| Component | Version | License | Source | Use |
|---|---:|---|---|---|
| `reactivecircus/android-emulator-runner` | 2.38.0 | MIT | https://github.com/ReactiveCircus/android-emulator-runner | Ephemeral API emulator for connected Compose tests; not packaged |

## Distribution and provenance rules

- Dependency versions are pinned in `gradle/libs.versions.toml`.
- The final release process must generate and review an SBOM covering direct and transitive runtime artifacts.
- Required upstream license and notice texts must be reproduced in the release before publication.
- The Eclipse adaptive icon and Compose icon geometry are original project work; see `docs/PROVENANCE.md`.
- No game assets, account credentials, runtime archives, or binaries are included from an unrelated launcher.
- A dependency's license can impose obligations beyond this project's Apache-2.0 license. Compatibility must be reviewed before adding a dependency.
