# Decision queue

**Baseline date:** 2026-09-23

This queue uses the required decision format. Resolved answers are binding for later phases. Open entries are visible blockers and must not be represented by placeholder URLs, client IDs, secrets, branding, or service names in the application.

## DQ-001 — Supported Android minimum SDK

ID: DQ-001
Location: Product decisions; Gradle build configuration; compatibility and adaptive-layout test matrix
Current behavior: The target has no build yet. The clean-room baseline is being established without inheriting the reference toolchain.
Options: replace
Impact: Determines platform API availability, process/runtime behavior, storage handling, notification behavior, and device coverage.
Question for product owner: Resolved 2026-09-23 — set `minSdk 26`; test API 26, current API, portrait, landscape, tablets, and foldables.

## DQ-002 — Supported device classes

ID: DQ-002
Location: Product scope; adaptive navigation; support policy; benchmark devices
Current behavior: The target has no device policy yet.
Options: replace
Impact: Determines required window classes, large-screen layouts, orientation behavior, input coverage, and release exclusions.
Question for product owner: Resolved 2026-09-23 — support phones, tablets, and foldables; exclude ChromeOS from the supported product matrix.

## DQ-003 — Minecraft account method

ID: DQ-003
Location: Account repository, account screens, authentication callback, secure account storage, launch prerequisites
Current behavior: The target has no account implementation. It must not inherit any reference OAuth client, token format, password persistence, or third-party service.
Options: replace
Impact: Determines legal identity flow, network dependencies, secure-storage requirements, account recovery, and whether online launch is available offline.
Question for product owner: Resolved 2026-09-23 — support official Microsoft OAuth and an offline local account only; arbitrary third-party/Yggdrasil providers are excluded. Production Microsoft authentication remains blocked until DQ-016 is answered.

## DQ-004 — Optional launcher features to retain

ID: DQ-004
Location: Approved product scope, navigation, parity matrix, repository/module boundaries
Current behavior: The target has no implemented feature set.
Options: replace
Impact: Determines the final destination set, core interfaces, tests, and legal/provider reviews.
Question for product owner: Resolved 2026-09-23 — retain launcher-critical workflows: bootstrap, home, accounts, instances/profiles, versions, installation, scoped instance content, downloads/queues, runtimes, rendering, controls/gamepad, logs/crash recovery, and legal/about screens. Remote catalog and mod-loader integrations remain subject to DQ-015.

## DQ-005 — Features to delete

ID: DQ-005
Location: Navigation, manifest, repositories, network policy, build dependencies, resources, release checks
Current behavior: No target features exist yet; prohibited categories were found in the read-only reference inventory.
Options: delete
Impact: Reduces attack surface, privacy obligations, maintenance cost, and unrelated product scope.
Question for product owner: Resolved 2026-09-23 — delete ads, analytics, telemetry, sponsor/funding pages, news/announcements, social feeds, easter eggs, hidden/debug product behavior, experimental default settings, standalone arbitrary Java/JAR execution, unrestricted device file management, broad all-files access, unsigned launcher/renderer/driver plugins, and hardcoded third-party account providers.

## DQ-006 — Social links to retain or replace

ID: DQ-006
Location: About, settings, launcher shell, notifications, deep links, documentation links
Current behavior: The target contains no links.
Options: delete
Impact: Prevents accidental reuse of legacy communities, contributors, domains, and endorsement claims.
Question for product owner: Resolved 2026-09-23 — retain no social links and provide no replacements until the owner supplies a specific destination through a new decision. The approved source repository is project infrastructure, not a social-feed feature.

## DQ-007 — Official Eclipse website

ID: DQ-007
Location: About, privacy, support, update metadata, external-link allowlist
Current behavior: No website or domain has been supplied.
Options: replace
Impact: A fabricated domain would be unsafe and would create legal, privacy, support, and release-trust obligations.
Question for product owner: Open — provide a controlled HTTPS website URL after domain ownership is established, or explicitly approve a no-website release.

## DQ-008 — Support URL

ID: DQ-008
Location: About, error/crash screens, settings, diagnostics export
Current behavior: No support endpoint or staffed support channel has been supplied.
Options: replace
Impact: Buttons must either open a verified destination or remain visibly disabled with an explanation.
Question for product owner: Open — provide a controlled HTTPS support URL and owner; until then the UI exposes no fake support action.

## DQ-009 — Source-derived code

ID: DQ-009
Location: Entire repository, provenance policy, dependency intake, code review
Current behavior: The target contains only Phase 0 audit documents and no copied implementation.
Options: delete
Impact: Determines whether the project remains an independent clean-room work or assumes source-derived copyleft/source-availability obligations.
Question for product owner: Resolved 2026-09-23 — source-derived code is not allowed. Use Path A clean-room implementation with no copied source, comments, strings, assets, data, binaries, or keys.

## DQ-010 — Eclipse Launcher license

ID: DQ-010
Location: Root legal files, About screen, dependency intake, repository metadata
Current behavior: The target has no license file yet.
Options: replace
Impact: Determines distribution terms, notice obligations, and compatibility requirements for every dependency and asset.
Question for product owner: Resolved 2026-09-23 — use Apache License 2.0 for original Eclipse Launcher code and maintain complete third-party notices.

## DQ-011 — Brand colors and logo

ID: DQ-011
Location: App icon, adaptive icon, splash, top app bar, theme tokens, About, release metadata
Current behavior: The target has no visual identity or assets.
Options: replace
Impact: Requires original provenance, accessibility review, app-store readiness, and trademark review.
Question for product owner: Resolved 2026-09-23 — create a new original clean-room mark and palette; do not reuse or trace reference artwork. Production use remains subject to DQ-017.

## DQ-012 — Source repository and visibility

ID: DQ-012
Location: Git remote, CI, release workflow, legal notices
Current behavior: Public repository `ShadowMaybe/EclipseLauncher` was created and Phase 0 was pushed to `main` on 2026-09-23.
Options: replace
Impact: Determines source visibility, collaboration model, release provenance, and public support expectations.
Question for product owner: Resolved 2026-09-23 — create public repository `ShadowMaybe/EclipseLauncher`.

## DQ-013 — Release-signing configuration

ID: DQ-013
Location: Gradle signing configuration and GitHub Actions release workflow
Current behavior: No Eclipse signing key exists in the target.
Options: replace
Impact: A release APK must be installable, verifiable, and reproducible under one controlled signing identity.
Question for product owner: Approved secret names: `ECLIPSE_KEYSTORE_BASE64`, `ECLIPSE_KEY_ALIAS`, `ECLIPSE_STORE_PASSWORD`, and `ECLIPSE_KEY_PASSWORD`. The owner must generate a new key outside the repository and populate GitHub Secrets before the first signed release; no legacy key may be used.

## DQ-014 — In-app self-update

ID: DQ-014
Location: Settings, About, update repository, downloads, package installer, release workflow
Current behavior: The initial distribution decision is GitHub Releases; no update metadata service exists.
Options: delete
Impact: Avoids inventing endpoints, unverifiable metadata, rollback failures, and unnecessary package-install permission.
Question for product owner: Open — choose `no in-app self-update` for the initial release, or provide a controlled HTTPS metadata endpoint plus signing, rollback, privacy, and store-policy decisions before implementation.

## DQ-015 — Remote content and mod-loader providers

ID: DQ-015
Location: Downloads hub, mod/mod-pack/resource/save/shader catalogs, loader selectors, network policy, dependency intake
Current behavior: The target has no provider integration. The reference inventory identified several third-party catalogs and loader ecosystems, but none is approved merely by appearing there.
Options: replace
Impact: Provider terms, API keys, endpoint ownership, content integrity, attribution, rate limits, and compatibility determine whether these rows can be implemented.
Question for product owner: Open — specify approved content providers and loader ecosystems. No remote catalog action may be enabled until each provider has an approved source/API, terms review, integrity policy, and test plan. Local imports and official version-manifest work may proceed independently.

## DQ-016 — Microsoft OAuth registration

ID: DQ-016
Location: OAuth configuration, app links/custom tabs, account callback, privacy notice
Current behavior: No Eclipse OAuth client ID, redirect URI, or registration ownership record exists.
Options: replace
Impact: Production Microsoft sign-in cannot be implemented or tested without a legitimate app registration.
Question for product owner: Open — register an Eclipse-owned Microsoft application, approve exact redirect URI(s), and provide the non-secret client ID through the approved configuration channel. Do not commit or invent a client secret.

## DQ-017 — Trademark and brand clearance

ID: DQ-017
Location: Name, logo, store listing, domains, public repository description, legal review
Current behavior: The required product name is selected, but formal clearance has not been evidenced.
Options: replace
Impact: An unclear name/logo can block publication or create legal and consumer-confusion risk.
Question for product owner: Open — obtain qualified review for `Eclipse Launcher` and the final logo in target jurisdictions/distribution channels. If clearance fails, approve a new name before Phase 1 visual identity work ships.

## DQ-018 — External repository links in the app

ID: DQ-018
Location: About, legal notices, support/help, update presentation
Current behavior: The approved public source repository exists at `https://github.com/ShadowMaybe/EclipseLauncher`.
Options: replace
Impact: Links must accurately identify Eclipse Launcher and must not imply endorsement or expose unapproved social/community identities.
Question for product owner: Resolved 2026-09-23 — after repository creation, the About/legal area may link to `https://github.com/ShadowMaybe/EclipseLauncher` as the source repository. No other social or promotional link is approved.
