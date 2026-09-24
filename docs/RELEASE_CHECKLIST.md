# Eclipse Launcher release checklist

**Baseline date:** 2026-09-24
**Release status:** Not ready for publication. This checklist records completed foundations and explicit release blockers.

## Repository and identity

- [x] Product name is Eclipse Launcher.
- [x] Release application ID is `me.shadow.eclipse`.
- [x] Debug application ID is `me.shadow.eclipse.debug`.
- [x] Public source repository exists at `https://github.com/ShadowMaybe/EclipseLauncher`.
- [x] Phase 2 source/resources/manifest contain no unexplained legacy identity.
- [x] Trademark review is recorded as a blocker rather than assumed complete.

## Build and CI

- [x] Gradle wrapper uses 9.6.0 and records the official distribution SHA-256.
- [x] Versions are pinned in `gradle/libs.versions.toml`.
- [x] Debug unit test, lint, and debug assembly pass in GitHub Actions.
- [x] Connected Compose semantics and screenshot contract pass in GitHub Actions.
- [x] CI does not publish an APK, screenshot, or test workflow artifact.
- [x] Release workflow is tag-only and publishes directly to GitHub Releases.
- [x] Release workflow uses least-privilege `contents: write` permission.
- [x] Release workflow verifies the APK signature and package metadata.
- [ ] Signed release workflow executed successfully — **Owner:** repository owner; **Blocker:** DQ-013 signing values.

## Legal and provenance

- [x] Apache License 2.0 text is present.
- [x] Direct third-party notices are present.
- [x] Clean-room provenance policy is present.
- [x] Original Phase 1 icon and Phase 2 Compose icon provenance are recorded.
- [x] Mojang/Microsoft non-affiliation and no-game-assets rules are recorded.
- [x] No ads, analytics, telemetry, sponsor, news, or social-feed code is present.
- [ ] Final transitive SBOM and license texts generated and reviewed — **Owner:** release owner; **Blocker:** Phase 5/6 dependency lock.
- [ ] Trademark clearance approved — **Owner:** project owner/legal reviewer; **Blocker:** DQ-017.
- [ ] Website, privacy, and support endpoints approved — **Owner:** project owner; **Blockers:** DQ-007 and DQ-008.

## Product implementation

- [x] Centralized Material 3 design-system module and token/component tests exist.
- [x] Home compact/expanded shell, prerequisite states, previews, and Actions Compose tests exist.
- [ ] All 132 parity rows have final implemented/removed/replaced/unblocked status.
- [ ] Real account, profile, version, runtime, download, renderer, control, diagnostics, and launch flows are verified on a device.
- [ ] No fake or simulated success/progress paths remain.
- [ ] Offline, permission, validation, destructive, process-death, and restoration states pass.
- [ ] Localization, TalkBack, keyboard/switch access, RTL, 200% font scale, landscape, tablet, and foldable checks pass.
- [ ] Release-like performance and baseline-profile targets pass.
- [ ] Final APK/AAB resources, manifest, URLs, permissions, metadata, and assets are inspected.

## Release command

The final verification command will be documented after the Phase 5/6 test and benchmark modules exist. Current authoritative CI commands are:

```bash
./gradlew --no-daemon --stacktrace testDebugUnitTest lintDebug assembleDebug
./gradlew --no-daemon --stacktrace connectedDebugAndroidTest
```

They are executed by `.github/workflows/ci.yml` and `.github/workflows/ui-test.yml`, not in the local workspace.
