# Eclipse Launcher legal review

**Review date:** 2026-09-24
**Status:** Phase 1 foundation passed CI; not a legal opinion or final publication clearance.

## 1. Independent-project identity

Eclipse Launcher is an independent clean-room project. It is not affiliated with, sponsored by, endorsed by, or operated by Mojang Studios or Microsoft.

The application does not distribute Minecraft: Java Edition game files, textures, sounds, music, worlds, account credentials, or other proprietary game assets. Users may manage or install content only through approved, documented sources and only within the permissions and terms applicable to that content.

Game and service names may be used as factual compatibility references where legally appropriate. Logos, promotional artwork, and game assets are not included.

## 2. Clean-room and source provenance

- The project follows Path A clean-room development.
- No source code, comments, strings, resources, artwork, data compilations, native binaries, runtime archives, or signing keys are inherited from another launcher.
- The Phase 1 adaptive icon was authored from original geometry for this project and is recorded in `docs/PROVENANCE.md`.
- Any future source-derived contribution must stop the clean-room path, be approved through `docs/DECISION_QUEUE.md`, and receive a separate license/source-availability review.

## 3. Project license and notices

- Original Eclipse Launcher code is licensed under Apache License 2.0; the full text is in `LICENSE`.
- Direct Phase 1 dependencies and their upstream links are listed in `THIRD_PARTY_NOTICES.md`.
- A complete transitive SBOM and any required upstream license texts must be generated and reviewed before a public release.
- No dependency may be added solely because it is available in a public repository. Source, checksum, license, security, maintenance, data collection, and native-code implications must be reviewed.

## 4. Trademark and brand status

The required word mark `Eclipse Launcher` and the Phase 1 geometric icon have not been cleared by qualified counsel. The original artwork reduces copying risk but does not establish trademark availability.

**Release blocker:** complete the review recorded as DQ-017 before public distribution. If the name or mark is unsuitable, stop release and approve a replacement rather than describing an uncleared asset as cleared.

## 5. Authentication and account data

The approved account design is official Microsoft OAuth plus an explicit offline local account. Arbitrary third-party/Yggdrasil providers are excluded from the baseline.

Production Microsoft authentication is blocked until the owner supplies an Eclipse-owned application registration and redirect configuration. Access tokens, refresh tokens, local account credentials, and OAuth state must not be logged, included in diagnostics, or committed. Credential backup must remain disabled. Secure storage and privacy disclosures are Phase 4 acceptance requirements.

## 6. Network, privacy, and content sources

The Phase 1 manifest requests no network permission and blocks cleartext traffic. Later network access requires an approved capability, TLS-only policy, cancellation/error behavior, and privacy disclosure.

There is no advertising, analytics, telemetry, sponsor tracking, news feed, or social-feed integration. Remote content catalogs and mod-loader providers remain blocked until their API terms, keys, integrity model, attribution, and data flows are approved through DQ-015.

## 7. Signing and release integrity

- Release signing values must be supplied only through GitHub Secrets.
- No keystore, password, key alias, or credential is committed.
- A new Eclipse signing identity must be generated outside the repository.
- The release workflow must verify the built APK signature before attaching it to GitHub Releases.
- A signed APK does not replace review of native libraries, bundled assets, permissions, or dependency licenses.

## 8. Current Phase 1 checklist

| Check | Result | Evidence or blocker |
|---|---|---|
| Independent identity and non-affiliation notice recorded | Pass | This document and future About screen |
| Apache-2.0 license text present | Pass | `LICENSE` |
| Direct dependency notices recorded | Pass | `THIRD_PARTY_NOTICES.md` |
| Game assets/credentials excluded | Pass | Empty/synthetic Phase 1 resources only |
| No ads/analytics/telemetry/social/news code | Pass | No dependencies or manifest permissions added |
| Clean-room provenance recorded | Pass | `docs/PROVENANCE.md` |
| Debug build, unit test, lint, and APK identity | Pass | GitHub Actions run 36036686843 |
| Release APK publication path | Pass, pending secrets | Tag-only workflow verifies signature and publishes directly to GitHub Releases |
| Trademark clearance | Blocked | DQ-017 |
| Microsoft OAuth registration | Blocked | DQ-016 |
| Official website/privacy/support endpoints | Blocked | DQ-007 and DQ-008 |
| Release signing secrets | Blocked | DQ-013 |
| Remote content-provider terms/API approval | Blocked | DQ-015 |
| Final transitive SBOM/license texts | Pending release phase | Must be generated before publication |

A qualified reviewer must approve the final distribution path before publishing a release.
