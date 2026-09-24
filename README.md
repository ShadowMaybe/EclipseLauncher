# Eclipse Launcher

Eclipse Launcher is an independent, clean-room Android launcher for Minecraft: Java Edition, built with Kotlin, Jetpack Compose, and Material 3.

It is not affiliated with, sponsored by, or endorsed by Mojang Studios or Microsoft. The project does not distribute game files or proprietary game assets.

## Development policy

- Source and documentation work may be performed locally.
- Gradle tests, lint, compilation, APK assembly, signing verification, and release publication run in GitHub Actions.
- Pull-request and branch CI does not publish APK workflow artifacts.
- A signed version tag publishes the APK and checksum directly to GitHub Releases.
- No advertising, analytics, telemetry, news, sponsor feed, or social feed is included.

## Current status

Phase 1 establishes the independent identity, Gradle/version-catalog foundation, minimal Compose shell, legal files, original icon geometry, and CI/release workflows. Launcher functionality is tracked in `docs/UI_PARITY_MATRIX.md`.

See:

- `docs/UI_SPEC.md` — interface and adaptive-layout specification
- `docs/UI_PARITY_MATRIX.md` — 132-row implementation ledger
- `docs/DECISION_QUEUE.md` — approved and blocked product decisions
- `docs/RELEASE_CHECKLIST.md` — release gates
- `docs/LEGAL_REVIEW.md` — non-affiliation, provenance, and legal status

## License

Original Eclipse Launcher code is licensed under Apache License 2.0. See `LICENSE` and `THIRD_PARTY_NOTICES.md`.
