# Changelog

All notable changes to Eclipse Launcher are recorded here. The format is based on Keep a Changelog, and the project uses semantic versioning once signed releases begin.

## [Unreleased]

### Added

- Complete 132-row clean-room UI/state parity ledger and implementation specification.
- Independent `me.shadow.eclipse` application identity and debug variant.
- Kotlin 2.4.20, Compose BOM 2026.09.00, Material 3, AGP 9.4.1, and Gradle 9.6.0 foundation.
- Centralized Material 3 design-system module with accessible theme roles, dimensions, reusable components, and original Compose icon geometry.
- Adaptive Home vertical slice with compact/expanded layouts, prerequisite empty states, previews, and disabled-action explanations.
- Top-level Home/Downloads/Settings navigation with adaptive category rails/tabs and honest destination empty states.
- Connected Compose semantics, navigation/Back, and screenshot theme-contract tests in GitHub Actions.
- Original Eclipse adaptive and monochrome launcher geometry with provenance.
- Apache-2.0 license, direct third-party notices, legal review, provenance policy, and release checklist.
- GitHub Actions CI for unit tests, lint, debug assembly, and APK identity inspection.
- Tag-only signed release workflow that attaches APKs directly to GitHub Releases without workflow artifacts.

### Removed

- No legacy source, resources, artwork, strings, data, binaries, signing keys, social links, or update/news infrastructure is included.

### Pending

- Complete navigation shell and parity destinations.
- Real launcher repositories and device integration.
- OAuth registration, content-provider approval, trademark clearance, support endpoints, and signing values.
