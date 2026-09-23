# Provenance and clean-room policy

**Path:** A — clean-room
**Applies to:** `/root/Project/EclipseLauncher`
**Policy status:** Mandatory for all implementation phases

## 1. Path A declaration

EclipseLauncher is a clean-room project. Implementation will be derived from requirements, official specifications, official documentation, independently written design, and explicitly approved dependencies—not by translating, adapting, or reproducing another application’s implementation.

A neutral legacy reference may be inspected only at a structural level to understand broad categories such as modules, build boundaries, platform integration points, and test surfaces. Its prohibited identity occurrences are recorded only in the dedicated audit/rebranding files needed to prove removal; implementation artifacts, user-facing documentation, metadata, and release materials use only approved Eclipse Launcher identity.

## 2. Non-copying rule

The following material **must not** be copied, pasted, transformed, paraphrased at expression level, or embedded from a reference project or any other unapproved source:

- source code, fragments, templates, generated code, or build logic;
- comments, documentation prose, commit messages, issue text, or code comments;
- user-visible strings, translations, labels, error text, help text, or metadata text;
- images, icons, fonts, sounds, videos, animations, layouts, themes, or other assets;
- sample data, fixtures, catalogs, databases, schemas, seeds, or test data;
- binaries, archives, JARs, AARs, APKs, native libraries, SDK copies, or build outputs;
- cryptographic material, signing keys, certificates, keystores, tokens, client secrets, or production credentials;
- names, logos, product identifiers, package identifiers, or brand treatment that are not independently approved.

Reimplementing a documented behavior from first principles is allowed. Reproducing non-public structure, naming patterns, or distinctive expression is not.

## 3. Provenance categories

Every project input must be assigned one of these categories before use.

| Category | Description | Admission rule |
|---|---|---|
| **P1 — Original project work** | Requirements, architecture, Kotlin/Java code, tests, documentation, and original UX created for EclipseLauncher. | Created from scratch in this repository and reviewed by the project. |
| **P2 — Official specifications and documentation** | Public platform standards and vendor documentation used to understand behavior and compatibility. | Record URL, publisher, document title/version, access date, and the decision or implementation influenced. No source snippets are copied when prose or an independent implementation is sufficient. |
| **P3 — Approved open-source dependencies** | Build plugins, libraries, SDKs, and tools obtained from their official repositories or package registries. | Record exact version, source URL, artifact checksum, license, transitive dependencies, and approved usage. |
| **P4 — Original or licensed assets** | EclipseLauncher icons, illustrations, fonts, sounds, sample content, and test fixtures. | Must be original, commissioned under written terms, public domain, or separately licensed. Record creator, source, license, and modification history. |
| **P5 — External operational facts** | OAuth registration, official URLs, signing material, trademark evidence, and other values supplied by accountable external parties. | Store only in the approved secret/configuration system. Repository documentation records ownership and provenance, never the secret value. |
| **P6 — Generated and derived outputs** | Compiler output, generated bindings, dependency metadata, reports, packages, and reproducible build artifacts. | Derive only from approved P1–P5 inputs; identify generator/tool version; never treat generated output as a new source of authority. |
| **P7 — Rejected material** | Unlicensed, unknown-origin, copied, or provenance-incomplete material. | Do not use. Quarantine outside the product tree and delete when retention is no longer legally or diagnostically required. |

## 4. Dependency intake process

Before a dependency enters the build:

1. **Identify need.** Record the capability, why the standard platform or an original implementation is insufficient, and the smallest acceptable dependency surface.
2. **Use the official source.** Obtain release metadata, source repository, and binary artifacts from the upstream project or an official package repository. Mirrors and unsolicited binaries are rejected by default.
3. **Pin identity.** Record exact version or immutable commit and, where available, SHA-256 checksums and provenance/SLSA attestations.
4. **Review license.** Preserve the exact license and notices; reject incompatible or unclear terms.
5. **Review security and maintenance.** Check advisories, release provenance, maintainer activity, transitive dependency count, requested permissions, native code, and data collection.
6. **Review compatibility.** Verify the approved JDK, Gradle, AGP, Kotlin, SDK, and architecture baseline.
7. **Generate inventory.** Add the dependency to the reviewed version catalog, notices inventory, and software bill of materials.
8. **Test admission.** Run clean dependency resolution, compilation, tests, static analysis, and release assembly before approval.
9. **Record the decision.** Preserve the intake record and update `docs/TECH_STACK.md` for baseline versions.
10. **Monitor updates.** Upgrades are new intake events; they do not occur automatically.

Direct dependencies and transitive dependencies are both in scope. A dependency’s presence in a public repository is not sufficient provenance.

## 5. Asset and data intake process

Before committing any non-source asset:

1. create a record with asset path, purpose, author, origin URL or commissioning agreement, license, creation date, modifications, and approved distribution scope;
2. verify that the license permits Apache-2.0 distribution of the combined work and any required attribution;
3. confirm the asset is not a logo, wordmark, screenshot, UI element, sound, or distinctive design from a reference project;
4. use synthetic or purpose-created data for tests and demonstrations unless real-data use is separately reviewed;
5. record privacy classification and ensure no personal, credential, token, device, or production data is committed;
6. calculate and retain a checksum for release verification.

AI-generated output, commissioned output, and stock output are not presumed free of third-party rights. They require the same provenance record and review as any other asset.

## 6. Binary and native-code policy

- Prefer source-built, reproducible outputs from approved upstream projects.
- Record source revision, compiler/toolchain version, build flags, target ABI, and checksum for every native artifact.
- Do not extract or repackage binaries from an unapproved application or neutral legacy reference.
- Do not accept unsigned release artifacts when an official signed release and checksum are available.
- Dynamic binaries, prebuilt `.so` files, fonts, and executables require individual security and license review.
- A binary with unknown origin is P7 rejected material and is not admitted.

## 7. Keys, signing, and secrets

- Production signing keys, OAuth client secrets, passwords, and release tokens must never be committed.
- Development, staging, and production credentials are distinct.
- Key material is generated for EclipseLauncher or supplied by an approved external signing authority; it is never copied from a reference project.
- Secrets enter CI only through the approved secret manager and are masked in logs.
- Key rotation, revocation, recovery ownership, and release-signing verification are recorded in the security operations runbook when that runbook is created.
- Public certificates and public keys may be committed only after provenance and intended distribution are reviewed.

## 8. Phase 1 original visual asset

| Asset | Purpose | Author/source | Creation method | License/distribution | Modifications | Provenance status |
|---|---|---|---|---|---|---|
| `app/src/main/res/drawable/ic_eclipse_foreground.xml` | Original Eclipse adaptive-icon foreground | Eclipse Launcher project | Geometric circle, offset eclipse disc, and cardinal corona rays authored from scratch for this repository | Apache-2.0 with the project, subject to DQ-017 trademark clearance | Initial creation | P1 original work; no external asset or tracing |
| `app/src/main/res/drawable/ic_eclipse_monochrome.xml` | Original Android themed-icon geometry | Eclipse Launcher project | Independently authored from the same project geometry using a single tintable paint | Apache-2.0 with the project, subject to DQ-017 trademark clearance | Initial creation | P1 original work; no external asset or tracing |

The Phase 1 mark does not incorporate game artwork, third-party logos, source-project marks, fonts, raster images, or generated imagery. It is original Eclipse Launcher artwork, not a representation of another product. It must not be described as trademark-cleared until DQ-017 closes.

## 9. Review gates

A change is provenance-clean only when reviewers can answer “yes” to all applicable questions:

- Was every non-original input identified with a source and license?
- Is every dependency and asset present in the notices/SBOM inventory?
- Could any output be substantially derived from reference source, expression, assets, data, binaries, or keys?
- Are all trademarks and brand claims supported by recorded clearance?
- Are all secrets external and absent from the repository and logs?
- Are provenance records preserved outside generated build directories?
- Would the input still be defensible if the neutral legacy reference were unavailable?

A failed gate blocks merge or release. Silence about an input’s origin is treated as a failed gate.

## 10. Planned legal and policy files

The following files are planned for later phases and must be reviewed before release:

| Planned file | Purpose |
|---|---|
| `LICENSE` | Project-wide Apache License 2.0 text and copyright notice. |
| `NOTICE` | Required project attribution and third-party notices, if applicable. |
| `THIRD_PARTY_NOTICES.md` | Human-readable inventory of dependencies, assets, licenses, versions, and required attributions. |
| `LICENSES/` | Verbatim copies or references for third-party licenses where required. |
| `SBOM.spdx.json` | Machine-readable software bill of materials generated from approved dependencies. |
| `PRIVACY.md` | Plain-language app privacy disclosures, including authentication and network data flows. |
| `SECURITY.md` | Supported versions, reporting channel, and coordinated disclosure policy. |
| `CONTRIBUTING.md` | Contribution, provenance, licensing, and clean-room review requirements. |
| `CODE_OF_CONDUCT.md` | Community expectations and enforcement contact, if public contributions are accepted. |
| `TRADEMARK_POLICY.md` | Permission and prohibited-use policy for approved EclipseLauncher branding. |

Until those files are created, this file records their intent; their absence is not approval to omit required legal attribution.

## 11. Path A completion rule

The project remains Path A clean-room only while every source, dependency, asset, data set, binary, key, legal statement, and brand input has a recorded provenance category and passes the applicable review gate. Any uncertainty moves the item to P7 until independently resolved.
