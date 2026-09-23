# EclipseLauncher technology stack

**Baseline date:** 2026-09-23
**Status:** Approved, stable baseline. This document records decisions only; it does not create an application or build.

## Build and platform baseline

| Area | Approved version | Release date | Official source | Rationale |
|---|---:|---:|---|---|
| JDK | 17 (vendor-patched Temurin 17 in CI) | 2021-09-14 GA | [OpenJDK JDK 17](https://openjdk.org/projects/jdk/17/), [Eclipse Temurin](https://adoptium.net/temurin/releases/?version=17) | JDK 17 is the minimum and default JDK for AGP 9.4. It is a vendor-supported LTS baseline, minimizes toolchain variance, and avoids adopting a newer feature release without an AGP/build audit. The selected 17.x patch is supplied and security-updated by the distribution provider. |
| Gradle | 9.6.0 | 2026-06-18 | [Gradle 9.6.0 release notes](https://docs.gradle.org/9.6.0/release-notes.html) | This is the minimum and default Gradle version listed for AGP 9.4. It is a stable, explicitly tested pairing. See “Why Gradle 9.6.0” below. |
| Android Gradle Plugin | 9.4.1 | 2026-09-18 | [AGP 9.4 release notes](https://developer.android.com/build/releases/agp-9-4-0-release-notes), [Google Maven metadata](https://dl.google.com/dl/android/maven2/com/android/tools/build/gradle/maven-metadata.xml) | Current stable 9.4 patch, aligned with Gradle 9.6.0, JDK 17, and the selected Android SDK baseline. |
| Kotlin | 2.4.20 | 2026-09-07 | [Kotlin releases](https://kotlinlang.org/docs/releases.html#release), [Kotlin 2.4.20](https://github.com/JetBrains/kotlin/releases/tag/v2.4.20) | Current stable tooling release in the Kotlin 2.4 line. The Compose compiler is supplied by the Kotlin plugin/toolchain, so one coordinated Kotlin version is retained. |
| `compileSdk` | 36 | 2025-06-10 | [Android 16/API 36](https://developer.android.com/about/versions/16/overview), [Android 16 release announcement](https://android-developers.googleblog.com/2025/06/android-16-is-here.html) | Compiles against the latest generally released Android platform SDK selected for production. API 36 is stable, while API 37 remains represented by Android 17 beta artifacts at this baseline. |
| `targetSdk` | 36 | 2025-06-10 | [Google Play target API policy](https://developer.android.com/google/play/requirements/target-sdk) | API 36 is the current stable production target and is required for new apps and updates submitted to Google Play from 2026-08-31. It also opts into final Android 16 security, privacy, performance, and adaptive-layout behavior. |
| `minSdk` | 26 (Android 8.0) | 2017-08-06 | [Android 8.0/API 26](https://developer.android.com/about/versions/oreo/android-8.0), [AOSP build reference](https://source.android.com/docs/setup/reference/build-numbers) | Covers the approved phone, tablet, and foldable device range while using modern notification channels, scoped/SAF-capable storage architecture, adaptive icons, and broadly supported security/runtime behavior. |

### Why API 36 is the stable target

Android 16/API 36 is a finished, generally released platform, not a preview or canary target. Google Play requires API 36 or newer for new phone/tablet submissions and updates as of 2026-08-31. Android 17/API 37 is still represented by beta release artifacts at this baseline, so API 37 and its minor SDK extensions are not production targets. Targeting API 36 therefore combines a stable API contract, current distribution compliance, and mature ecosystem validation. Moving to API 37 requires a separate compatibility, behavior-change, and device-test audit.

### Why Gradle 9.6.0 rather than Gradle 9.7.1

AGP 9.4 lists **Gradle 9.6.0 as both its minimum and default version**. Gradle 9.7.1 is a newer stable line, but selecting it would move beyond AGP 9.4.1’s default-tested pairing and introduce Isolated Projects, configuration-cache, and Gradle 10 migration changes that the project has not yet audited. The project therefore freezes the known-compatible AGP default baseline at Gradle 9.6.0. This is a deliberate compatibility decision, not a claim that 9.6.0 is the newest Gradle release.

## UI and application libraries

| Area | Approved version | Release date | Official source | Rationale |
|---|---:|---:|---|---|
| Compose BOM | 2026.09.00 | 2026-09-09 | [Compose BOM documentation](https://developer.android.com/develop/ui/compose/bom), [Google Maven metadata](https://dl.google.com/dl/android/maven2/androidx/compose/compose-bom/maven-metadata.xml) | Supplies a tested, mutually compatible set of stable Compose libraries from one version coordinate. Individual Compose artifacts are not independently floated. |
| Material 3 | 1.4.0, as mapped by the BOM | 2026-09-09 | [Compose Material 3 release notes](https://developer.android.com/jetpack/androidx/releases/compose-material3#1.4.0) | Current stable Material 3 line selected by the BOM, providing the approved component and theming foundation without alpha overrides. |
| Navigation | 2.10.1 | 2026-09-09 | [Navigation 2.10.1 release notes](https://developer.android.com/jetpack/androidx/releases/navigation#2.10.1) | Stable back-stack, destination, deep-link, and saved-state behavior. Google lists the library in maintenance mode, so it is retained behind a small navigation boundary and no new-feature dependency is planned. |
| Lifecycle | 2.11.0 | 2026-06-17 | [Lifecycle 2.11.0 release notes](https://developer.android.com/jetpack/androidx/releases/lifecycle#2.11.0) | Stable lifecycle-aware state and ViewModel integration compatible with the selected Compose baseline. |
| Activity | 1.13.0 | 2026-03-11 | [Activity 1.13.0 release notes](https://developer.android.com/jetpack/androidx/releases/activity#1.13.0) | Current stable Activity/Compose host integration, including edge-to-edge and activity-result behavior. |
| WorkManager | 2.11.2 | 2026-03-25 | [WorkManager 2.11.2 release notes](https://developer.android.com/jetpack/androidx/releases/work#2.11.2) | Stable constrained and deferrable background execution. The patch includes current Android network-constraint and retry fixes. |
| DataStore | 1.2.1 | 2026-03-11 | [DataStore 1.2.1 release notes](https://developer.android.com/jetpack/androidx/releases/datastore#1.2.1) | Stable asynchronous, transactional persistence for settings and local application state. It avoids a new SharedPreferences dependency for new data. |
| Benchmark | 1.5.0 | 2026-09-09 | [Benchmark 1.5.0 release notes](https://developer.android.com/jetpack/androidx/releases/benchmark#1.5.0) | Current stable microbenchmark, macrobenchmark, and baseline-profile tooling. It is isolated to benchmark/test configuration and is not shipped in the application runtime. |
| ProfileInstaller | 1.4.1 | 2024-10-02 | [ProfileInstaller 1.4.1 release notes](https://developer.android.com/jetpack/androidx/releases/profileinstaller#1.4.1) | Current stable runtime installer for ART baseline profiles, including multi-process recording support. Runtime and benchmark versions are kept compatible. |

## CI action baseline

Actions are pinned to exact release tags. Commit SHAs may be substituted by a separately audited workflow-hardening update, but floating tags such as `@v7` are not approved.

| Action | Approved version | Release date | Official source | Rationale |
|---|---:|---:|---|---|
| `actions/checkout` | 7.0.1 | 2026-07-20 | [Release v7.0.1](https://github.com/actions/checkout/releases/tag/v7.0.1) | Exact stable checkout release, including branch/ref safety fixes. |
| `actions/setup-java` | 6.0.1 | 2026-09-09 | [Release v6.0.1](https://github.com/actions/setup-java/releases/tag/v6.0.1) | Installs the audited Temurin 17 line and includes current Temurin signature-verification fixes. |
| `android-actions/setup-android` | 4.0.4 | 2026-09-17 | [Release v4.0.4](https://github.com/android-actions/setup-android/releases/tag/v4.0.4) | Installs the audited Android command-line tools and SDK packages reproducibly. |
| `gradle/actions` | 6.3.0 | 2026-08-02 | [Release v6.3.0](https://github.com/gradle/actions/releases/tag/v6.3.0) | Exact stable Gradle setup, caching, validation, and dependency-submission action release. |
| `softprops/action-gh-release` | 3.0.3 | 2026-08-30 | [Release v3.0.3](https://github.com/softprops/action-gh-release/releases/tag/v3.0.3) | Stable release-upload action for public GitHub releases; release signing credentials remain external. |

## GitHub Actions and release policy

- Pull requests and pushes run verification jobs without publishing an APK workflow artifact.
- Local development must not produce the release APK; GitHub Actions is the release build authority.
- A version tag such as `vX.Y.Z` triggers the release workflow after tests, lint, dependency verification, R8/resource shrinking, signing verification, and release-like checks pass.
- The workflow writes the signed APK directly to the corresponding GitHub Release using `softprops/action-gh-release`.
- The workflow must not use `actions/upload-artifact` for the release APK. APK/AAB inspection evidence may be attached to the Release as separate checksum/report files, but the APK itself is a Release asset.
- Release signing uses only the GitHub Secrets named in `docs/DECISION_QUEUE.md`; missing secrets fail before publication.
- The workflow is pinned to audited action release tags and full commit SHAs when the workflow is created. The Gradle wrapper includes the official distribution SHA-256 checksum.

## Phase 0 build baseline

The target had no Gradle wrapper or build scripts when the toolchain was selected. The reproducible baseline command was:

```text
./gradlew --version
```

Result: exit 127, `./gradlew: No such file or directory`. This is the expected pre-Phase-1 failure and is not presented as a passing build.

## Compatibility governance

The exact compatibility baseline may be revised in `gradle/libs.versions.toml` **only through a documented, audited update**. Such an update must include:

1. upstream release and compatibility citations;
2. the old-to-new version delta and reason for changing now;
3. Gradle/AGP/Kotlin/JDK/SDK compatibility verification;
4. dependency-resolution and license review;
5. clean unit, integration, benchmark/baseline-profile, and release-build checks as applicable;
6. an update to this document in the same reviewed change.

No ad hoc build-script overrides, floating dependency selectors, or unrecorded compatibility overrides are approved. Gradle wrapper and Android SDK changes are part of the same audit even where the underlying tool versions are not Maven library coordinates.
