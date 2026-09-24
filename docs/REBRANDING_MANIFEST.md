# Rebranding and exclusion manifest

## Scope and verification policy

This manifest is an audit/rebranding record. Legacy names, identifiers, URLs, and filenames appear here only to locate prohibited source material or residual-risk categories. They are not approved target copy and must never be placed in target UI, notifications, metadata, network identity, documentation intended for end users, or release filenames.

Approved target identity:

- Product name: **Eclipse Launcher**
- Application ID/namespace: **`me.shadow.eclipse`**
- Debug application ID: **`me.shadow.eclipse.debug`**
- Project license: **Apache-2.0**
- Implementation: **clean-room**
- Legacy links/social/news/sponsor: **none**
- Advertising/analytics/telemetry: **none**
- Easter eggs: **none**

Status values in the required `Verified` column:

- **Checked:** directly verified in the read-only legacy reference and/or the current target scan; the target has no approved user-facing occurrence.
- **Unchecked:** no target implementation or selected replacement artifact exists yet; verification is required on every future change.
- **Finding:** a prohibited, unresolved, high-risk, or legally unresolved condition was found.

## Product identity, licensing, and repository boundary

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| `Zalith Launcher` | Product/display name | Replace | `Eclipse Launcher` | `/root/Project/ref/ZalithLauncher/ZalithLauncher/gradle.properties` | Checked — legacy value confirmed; target was initially empty |
| `ZalithLauncher` | Build name, module name, APK prefix | Replace | Use an independently selected Eclipse build/module/artifact name; do not mechanically retain the legacy prefix | `/root/Project/ref/ZalithLauncher/settings.gradle.kts`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked — root/module/output occurrences confirmed |
| `Zalith Launcher` | Root Gradle project name | Replace | Independent future root name | `/root/Project/ref/ZalithLauncher/settings.gradle.kts` | Checked |
| `com.movtery.zalithlauncher` | Namespace/application ID/package | Replace | `me.shadow.eclipse` | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `com.movtery.zalithlauncher.debug` | Debug application ID | Replace | `me.shadow.eclipse.debug` | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked — derived from application ID plus `.debug` |
| `com.movtery.zalithlauncher.storage_provider` | Provider authority | Replace | Derive a new provider authority from `me.shadow.eclipse` only after independent storage design | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `com.movtery.zalithlauncher.storage_provider.debug` | Debug provider authority | Replace | New target debug authority; exact value is not approved by this task | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `com.movtery.zalithlauncher.provider` | File-provider authority | Replace | New target file-provider authority derived from `me.shadow.eclipse`; no legacy URI contract | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `nameId` | Build abbreviation/identifier | Replace | Use a target-owned namespace identifier such as `applicationNamespace`; do not preserve abbreviation | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `generatedZalithDir` | Build identifier/path abbreviation | Remove | No legacy generated-source path; any future generation belongs under an independently designed target path | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `com.movtery.zalithlauncher.InfoDistributor` | Generated class/package | Remove | No legacy generated class; design a new mechanism if later required | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `InfoDistributor.LAUNCHER_NAME` | Generated identity constant | Replace | New target-owned build metadata only; never populate with a legacy name | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| `InfoDistributor.APP_NAME` | Generated identity constant | Replace | Future user-facing value may be `Eclipse Launcher`; do not copy generation design | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| `InfoDistributor.BUILD_TYPE` | Generated build-state constant | Replace | Independently designed build metadata; no legacy debug/release labeling | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `ZHTools` | Class abbreviation/name | Remove | No target class with legacy name or direct source translation | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/ZHTools.kt` | Checked |
| `Mio` / `MioLibPatcher.jar` / `MioFabricAgent.jar` | Contributor abbreviation and binary names | Remove | No legacy contributor abbreviation or copied binary; independently implement functionality and select upstream artifacts | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/mio/util/AndroidUtil.kt`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/components/` | Checked |
| `MTP` / `movtery` signing identifiers | Author/signing abbreviation | Remove | Generate a new Eclipse signing identity with a new alias; never reuse legacy key or alias | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/movtery-key.jks` | Checked — alias context confirmed; secret material not reproduced |
| `1.4.1.4` | Version name | Replace | Start a new Eclipse version scheme | `/root/Project/ref/ZalithLauncher/ZalithLauncher/gradle.properties` | Checked |
| `141400` | Version code | Replace | Independent target version code | `/root/Project/ref/ZalithLauncher/ZalithLauncher/gradle.properties` | Checked |
| `ZalithLauncher-<version>[-ABI].apk` | Release binary filename | Remove | New Eclipse-owned release artifact filename; never inherit old package/update identity | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `ZalithLauncher-Debug-<version>[-ABI].apk` | Debug binary filename | Remove | New Eclipse-owned debug artifact filename | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| Legacy Git origin `ZalithLauncher/ZalithLauncher` | Repository identity | Remove | Initialize an independent target repository; do not copy `.git` metadata or origin | `/root/Project/ref/ZalithLauncher/.git/config` | Checked |
| GPLv3 project license | License | Replace | Apache-2.0 for original target code, with complete third-party notices | `/root/Project/ref/ZalithLauncher/LICENSE` | Finding — legacy GPLv3 cannot be the target license without copying source |
| Legacy source tree | Source provenance | Remove | Clean-room implementation only | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/` | Finding — legacy source is GPL/Pojav/HMCL-derived and excluded |
| Legacy root build files | Build provenance | Remove | Future build must be newly authored and dependency-reviewed | `/root/Project/ref/ZalithLauncher/settings.gradle.kts`; `/root/Project/ref/ZalithLauncher/build.gradle.kts` | Checked |

## Authors, acknowledgements, and personal/service identity

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| `MovTery` / `墨北MovTery` | Author/maintainer identity | Remove | No legacy author identity in target user-facing copy; attribute only separately reused upstream material in a legal notices file | `/root/Project/ref/ZalithLauncher/LICENSE`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt` | Checked |
| `MovTery and contributors` | Copyright notice | Remove as target ownership notice | Use independently owned target copyright metadata; do not claim or erase upstream rights for separately used material | `/root/Project/ref/ZalithLauncher/LICENSE` | Checked |
| `com.movtery` | Package/author identity | Replace | `me.shadow.eclipse` | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/` | Checked |
| `PojavLauncherTeam` | Upstream acknowledgement/logo | Remove from target identity | May be named only in a future legal-history notice if an independently selected upstream dependency is actually used; never imply product ownership | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt` | Checked |
| `MC百科` | Service/contributor identity | Remove from target branding | No legacy service portrait, endorsement, or link; future compatibility references require separate approval | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt` | Checked |
| `Vera-Firefly` | Contributor identity | Remove from target About/social UI | Retain attribution only if a separately reused licensed contribution requires it | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt` | Checked |
| `柃木湫竹` | Contributor identity | Remove from target About/social UI | Retain attribution only for independently reused licensed material | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt` | Checked |
| `ShiroakiMio` / `ShirosakiMio` | Contributor identity/plugin publisher identity | Remove from target UI | No legacy portrait, endorsement, or plugin link; separately acquired upstream artifacts require their own verified provenance | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| `bangbang93` | Contributor/sponsor identity | Remove from target UI | No legacy portrait or sponsorship link | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt` | Checked |
| Six named contributor portraits plus `image_xibao.png` | Personal/service artwork | Remove | Original target artwork only; personal images require explicit permission | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/` | Checked |
| `zalithlauncher.cn` | Domain | Remove | No legacy domain; no replacement domain is approved or configured | `/root/Project/ref/ZalithLauncher/README.md` | Checked |

## Package families and upstream-derived source identifiers

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| `net.kdt.pojavlaunch` | Package/source lineage | Remove | No target package under this namespace; independently design `me.shadow.eclipse` packages | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/` | Checked |
| `net.kdt.pojavlaunch.PojavApplication` | Application class | Remove | New target application class under `me.shadow.eclipse` if required | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/PojavApplication.java` | Checked |
| `com.kdt` | Upstream package lineage | Remove | No target class under legacy package | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/kdt/` | Checked |
| `org.jackhuang.hmcl` | HMCL-derived package lineage | Remove | No copied/translated HMCL source; any future upstream dependency must be independently reviewed | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/org/jackhuang/hmcl/` | Checked |
| `com.oracle.dalvik` | Oracle-derived package | Remove | No target class under this package; do not mechanically rename copied source | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/oracle/dalvik/VMLauncher.java` | Checked |
| `dalvik.annotation.optimization.CriticalNative` | Optimization package/annotation | Remove | Reassess performance needs independently; no copied annotation/class | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/dalvik/annotation/optimization/CriticalNative.java` | Checked |
| `org.lwjgl` | Third-party API package | Remove as copied source namespace | A separately selected official LWJGL dependency may expose its own API package under its license; no legacy source copy | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/org/lwjgl/` | Checked |
| `com.movtery.anim` | Legacy helper package | Remove | Original target UI/animation implementation | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/anim/` | Checked |
| `com.movtery.zalithlauncher` | Legacy application package | Replace | `me.shadow.eclipse` | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/` | Checked |

## Manifest application, activities, services, providers, and processes

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| `com.movtery.zalithlauncher.ui.activity.SplashActivity` | Activity | Remove | Independently design target launch/entry screen; no class/layout copy | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `net.kdt.pojavlaunch.MissingStorageActivity` | Activity | Remove | Independently design storage-permission flow if needed | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `net.kdt.pojavlaunch.LauncherActivity` | Activity | Remove | Independently design target UI | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `com.movtery.zalithlauncher.ui.activity.ErrorActivity` | Activity | Remove | New target error handling; no legacy copy | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `net.kdt.pojavlaunch.ShowErrorActivity` | Activity | Remove | New target error handling if required | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `net.kdt.pojavlaunch.JavaGUILauncherActivity` | Activity/process | Remove | No legacy GUI installer component or `:gui_installer` process | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `net.kdt.pojavlaunch.CustomControlsActivity` | Activity/internal action | Remove | No legacy custom-control activity or intent contract | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `net.kdt.pojavlaunch.MainActivity` | Game-process activity | Remove | Independently design target game-hosting component if required | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `net.kdt.pojavlaunch.services.ProgressService` | Foreground service | Remove | Future notification/progress service, if justified, must be new and target-owned | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `net.kdt.pojavlaunch.services.GameService` | Foreground game service | Remove | No legacy service, special-use text, or `:game` process | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `net.kdt.pojavlaunch.scoped.FolderProvider` | Exported provider | Remove | Redesign scoped storage under target authority and current Android security guidance | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `androidx.core.content.FileProvider` with `${applicationId}.provider` | File provider | Replace configuration | Library may be independently selected; use a new target authority and new paths XML | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `:launcher` | Process suffix | Remove | No approved target process suffix; choose only for an independent architecture reason | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `:game` | Process suffix | Remove | No legacy game process split | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `:gui_installer` | Process suffix | Remove | No legacy GUI installer process | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |

## Permissions and security policy

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| `POST_NOTIFICATIONS` | Permission | Do not inherit | Add only if a separately justified target notification feature exists | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Unchecked — future target necessity not decided |
| `WRITE_EXTERNAL_STORAGE` | Legacy broad storage permission | Remove | Prefer scoped storage/document access in future target | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `READ_EXTERNAL_STORAGE` | Legacy broad storage permission | Remove | Prefer scoped storage/document picker in future target | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `MANAGE_EXTERNAL_STORAGE` | High-risk all-files permission | Remove | No target all-files access is approved; add only after separate product/privacy decision | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Finding — high-risk legacy permission |
| `REQUEST_INSTALL_PACKAGES` | Package-install permission | Remove | No target updater/installer is approved; add only for a separately justified user-initiated flow | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `INTERNET` | Permission | Do not inherit automatically | Add only for approved target network features under no-telemetry policy | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Unchecked — future requirements not selected |
| `ACCESS_NETWORK_STATE` | Permission | Do not inherit automatically | Add only if required by approved target networking | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Unchecked |
| `RECORD_AUDIO` | Microphone permission | Remove | No microphone feature is approved | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Finding — unnecessary/high-risk legacy permission |
| `FOREGROUND_SERVICE` | Permission | Remove pending justification | Add only for a separately approved target foreground workflow | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Unchecked |
| `FOREGROUND_SERVICE_DATA_SYNC` | Permission | Remove | No inherited data-sync foreground service | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `FOREGROUND_SERVICE_SPECIAL_USE` | Permission | Remove | No inherited special-use service or legacy special-use text | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `WAKE_LOCK` | Permission | Remove pending justification | Add only if an approved target workflow requires it | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Unchecked |
| `VIBRATE` | Permission | Do not inherit automatically | Add only for an independently approved target feedback feature | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Unchecked |
| Global `cleartextTrafficPermitted="true"` | Network-security policy | Remove | Require TLS by default; no target network policy currently exists | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/xml/network_security_config.xml` | Finding — legacy policy is overbroad |
| `android:allowBackup="true"` with sensitive account files | Backup/privacy policy | Remove | Future target must define backup rules and exclude credentials | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Finding — legacy account persistence makes this unsafe |

## URLs, remote data, social, news, sponsor, and network identity

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| `https://api.github.com/repos/ZalithLauncher/Zalith-Info/contents/` | Legacy API/update/news/sponsor endpoint | Remove | No legacy update, news, or sponsor backend; no target endpoint is approved | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| `https://www.mcmod.cn/` | Legacy service link | Remove | No legacy service link or branding; independently approved official API use may be evaluated later | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| `https://www.minecraft.net/` | Legacy website link | Remove | No hard-coded legacy web link; future official links must come from current approved requirements | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| `https://piston-meta.mojang.com/mc/game/version_manifest_v2.json` | Legacy hard-coded metadata URL | Remove | No inherited endpoint; independently verify any future official API URL and terms | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| `https://afdian.com/a/MovTery` | Sponsorship/social URL | Remove | No target sponsor or social links | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| `https://afdian.com/a/bangbang93` | Sponsorship/social URL | Remove | No target sponsor or social links | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt` | Checked |
| `https://github.com/ZalithLauncher/ZalithLauncher` | Legacy project/social URL | Replace | Approved source repository is `https://github.com/ShadowMaybe/EclipseLauncher`; it is not a social feed | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked — legacy URL confirmed; target public repository created and verified |
| `https://github.com/ShirosakiMio/FCLRendererPlugin/releases/tag/Renderer` | Plugin/social URL | Remove | No legacy plugin link; future plugin policy and artifacts require separate approval | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| `https://github.com/FCL-Team/FCLDriverPlugin/releases/tag/Turnip` | Plugin/social URL | Remove | No legacy plugin link | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| Legacy GitHub release download URL containing `ZalithLauncher/ZalithLauncher` and `ZalithLauncher-<version>` | Update URL/filename | Remove | No inherited update channel, APK filename, package, or signing identity | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/update/UpdateUtils.kt` | Checked |
| `launcher_version.json` from legacy information API | Remote update/news JSON | Remove | No legacy remote control channel; no target update/news channel is approved | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/update/UpdateUtils.kt` | Checked |
| `launcher_notice.json` from legacy information API | News/notice JSON | Remove | No target news or notice polling | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/notice/CheckNewNotice.kt` | Checked |
| Legacy sponsor JSON | Sponsor/remote data | Remove | No sponsor UI, sponsor data, or remote sponsor avatar loading | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/CheckSponsor.kt` | Finding — sponsor tracking/content surface |
| Legacy User-Agent composed from launcher name/version | Network identity | Remove | Future User-Agent may identify only `Eclipse Launcher` and approved version; no legacy name | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Checked |
| Legacy GitHub actions/download badges | Social/CI URL | Remove | No legacy badge or link; no target CI exists | `/root/Project/ref/ZalithLauncher/README.md`; `/root/Project/ref/ZalithLauncher/README_ZH_CN.md`; `/root/Project/ref/ZalithLauncher/README-ZH_TW.md` | Checked |
| Legacy QQ group flow | Social/community link | Remove | No target social/community links | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt` | Checked |
| Legacy Discord invite | Social link | Remove | No target social links | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt` | Checked |
| Four legacy Bilibili profile links | Social links | Remove | No target social links or contributor promotion | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutInfoPageFragment.kt` | Checked |
| Five-minute automatic update polling | Network automation | Remove | No legacy update polling; no target update service is approved | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/update/UpdateUtils.kt` | Finding — remote operational behavior |
| Two-minute automatic notice polling | News/network automation | Remove | No target news or remote notice polling | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/notice/CheckNewNotice.kt` | Finding — prohibited news surface |
| User-selected legacy authentication base URLs | Arbitrary endpoint | Remove with legacy authentication | No target authentication endpoint is approved; future auth requires a new security design | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/accounts/OtherLoginHelper.kt` | Checked |

## Ads, analytics, telemetry, logs, and crash-reporting surfaces

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| No explicit legacy advertising SDK identified | Ads finding | Do not add | Advertising is prohibited by approved target policy | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/` | Checked for visible source; Unchecked in opaque binaries |
| No explicit legacy advertising-ID access identified | Ads/privacy finding | Do not add | No advertising ID or profile access in target | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/` | Checked for visible source; Unchecked in opaque binaries |
| No explicit legacy analytics SDK identified | Analytics finding | Do not add | Analytics is prohibited by approved target policy | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/` | Checked for visible source; Unchecked in opaque binaries |
| No explicit hosted crash-reporting backend identified | Telemetry finding | Do not add | No crash telemetry backend; future local error handling must be newly designed | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/` | Checked for visible source; Unchecked in opaque binaries |
| Automatic update/notice requests and branded User-Agent | Operational telemetry risk | Remove | No remote polling or legacy network identity in target | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt` | Finding |
| Remote sponsor metadata/avatar loading | Sponsor/telemetry risk | Remove | No sponsor system, remote avatar request, or sponsor tracking | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/CheckSponsor.kt`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutSponsorPageFragment.kt` | Finding |
| Legacy `Logging` and user-shared logs | Diagnostic/share data | Remove legacy implementation | Future logs, if independently required, must redact secrets, avoid telemetry, and use synthetic tests | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/log/Logging.kt` | Checked |
| `ic_profile_crash_report` | Internal crash-report screen | Remove | No legacy crash-report screen; no hosted reporting is approved | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/ic_profile_crash_report.xml` | Checked |
| Legacy account token/password serialization | Sensitive data | Remove | New secure target design; no plaintext third-party passwords or tokens | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/value/MinecraftAccount.java`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/accounts/OtherLoginHelper.kt` | Finding — prohibited design |
| Hard-coded OAuth client identifier | OAuth identity | Replace | New target-registered OAuth application; value intentionally not reproduced | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/fragments/MicrosoftLoginFragment.java`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/authenticator/microsoft/MicrosoftBackgroundLogin.java` | Checked — value redacted |

## Deep links, internal URI contracts, and exported surfaces

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| `pojav://` | Internal URI-prefix sentinel | Remove | No legacy scheme or runtime prefix; no target deep link is approved | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/` | Checked — internal sentinel, not a manifest deep link |
| No registered legacy HTTP/app deep-link intent filter | Deep-link finding | Preserve absence | Target registers no external deep link unless separately approved | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| `.CustomControlsActivity` explicit action | Internal intent action | Remove | No legacy action contract or component | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked — activity is not exported |
| Exported launcher entry activity | Exported component | Replace independently | A future launcher may export only its own new entry activity with a new package | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Unchecked — future target has no manifest |
| Exported legacy `FolderProvider` | Exported provider | Remove | New target storage provider design required; no legacy authority or permission model | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |
| Non-exported legacy `FileProvider` | Provider URI surface | Replace configuration | New target authority and path exposure only if independently needed | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml` | Checked |

## Notifications and foreground UI

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| Legacy notification channel | Notification identity | Remove | No target channel; future channel needs a new target-owned ID/name if separately justified | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/utils/NotificationUtils.java` | Finding — exact legacy channel identity is not reusable |
| Five legacy notification IDs | Notification state | Remove | New notification IDs only; no numerical or semantic continuity | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/services/` | Checked — count confirmed |
| Launcher-branded notification icon | Notification asset | Remove | Original target small icon if notifications are later approved | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/mipmap-*/` | Checked |
| `ProgressService` notification behavior | Foreground notification | Remove | New service/channel/copy only after independent requirement approval | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/services/ProgressService.java` | Checked |
| `GameService` notification/foreground behavior | Foreground notification | Remove | No inherited game service notification | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/services/GameService.java` | Checked |
| Notification-permission prompt flow | Permission UX | Remove | No target notification prompt until a target feature is approved | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/` | Unchecked — future target flow not selected |

## Build, CI, debug status, and diagnostic traces

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| `ZL_BUILD_TYPE` | Build abbreviation/environment key | Remove | Use an independently named target build variable; no `ZL` abbreviation | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts`; `/root/Project/ref/ZalithLauncher/.github/workflows/android.yml` | Checked |
| `DEBUG` default when build value is absent | Build-state behavior | Remove | No legacy default; future build state must be explicit and independently designed | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `PRE_RELEASE` and `RELEASE` build labels | Build-state identity | Remove as inherited labels | Future channel, if any, requires separate requirements and no legacy update identity | `/root/Project/ref/ZalithLauncher/.github/workflows/android.yml` | Checked |
| `debug`, `release`, `proguard`, `proguardNoDebug` build types | Build variants | Replace independently | Future target may use its own variants; no mechanical legacy variant model | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `customDebug` signing config | Build/signing name | Remove | New target signing configuration and externally managed key | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `BuildDebug` CI job | CI identity | Remove | No legacy CI; future tests/build/lint/license jobs must be newly designed | `/root/Project/ref/ZalithLauncher/.github/workflows/android.yml` | Checked |
| `BuildRelease` CI job and owner/ref condition | CI/update identity | Remove | No legacy release automation or signing path | `/root/Project/ref/ZalithLauncher/.github/workflows/android.yml` | Checked |
| Zalith release/debug artifact names | CI binary filename | Remove | New target artifact names | `/root/Project/ref/ZalithLauncher/.github/workflows/android.yml` | Checked |
| Legacy build-status presentation | User-facing/debug state | Remove | Do not expose copied internal build/channel status; future diagnostics stay non-user-facing | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/` | Unchecked — future implementation not selected |
| `CriticalNativeTest` | Debug/test class | Remove | No copied test; future native tests must be newly written | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/CriticalNativeTest.java` | Checked |
| `@CriticalNative` | Debug/optimization trace | Remove | New performance design only | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/dalvik/annotation/optimization/CriticalNative.java` | Checked |
| GLFW input-debug behavior | Debug trace | Remove | No copied debug logging/input path | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/org/lwjgl/glfw/CallbackBridge.java` | Checked |
| StringFog package obfuscation | Build/debug hardening | Remove | Do not copy or use as a clean-room identity mechanism; secrets must not be embedded | `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts` | Checked |
| `debug.keystore` | Binary filename/signing material | Remove | Generate a new target debug key outside source control if Android tooling requires one | `/root/Project/ref/ZalithLauncher/ZalithLauncher/debug.keystore` | Checked |
| `movtery-key.jks` | Binary filename/author/release key | Remove | New Eclipse signing key; legacy key must never be used | `/root/Project/ref/ZalithLauncher/ZalithLauncher/movtery-key.jks` | Finding — release key present in source tree |

## Easter eggs, hidden behavior, experimental features, and samples

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| Random title-click Easter egg | Hidden/easter egg | Remove | Easter eggs are prohibited | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/activity/SplashActivity.kt` | Finding |
| April Fools hair/easter-egg behavior | Hidden/easter egg | Remove | Easter eggs are prohibited | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/ic_hair.xml` | Finding |
| Three legacy experimental settings | Experimental feature | Remove by default | No legacy experimental requirement is approved; any future feature needs a new written decision | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/settings/ExperimentalSettingsFragment.kt` | Checked — count confirmed |
| Arbitrary JAR execution behavior | Plugin/executable feature | Remove legacy behavior | No copied JAR execution; any future plugin/JAR feature requires explicit consent, verification, and a new security design | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/plugins/` | Finding |
| Renderer plugin import/discovery | Plugin feature | Remove legacy implementation | No copied plugin loader; future plugin policy is undecided | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/plugins/renderer/` | Checked |
| Driver plugin import/discovery | Plugin feature | Remove legacy implementation | No copied driver plugin system | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/plugins/driver/` | Checked |
| Four layouts using `@tools:sample/avatars` | Design-time samples | Remove | No copied layouts or sample references; future fixtures must be synthetic and independently authored | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/layout/` | Checked — count confirmed; no bundled sample account found |
| Legacy `default.json` control data | Default/sample-like data | Remove | Independently define target defaults; no copied control data | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/default.json` | Checked |
| Legacy `options.txt` | Bundled default data | Remove | Future target options must be independently authored and documented | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/options.txt` | Checked |
| Legacy `resolv.conf` | Runtime configuration | Remove | No copied runtime DNS configuration | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/resolv.conf` | Checked |

## Visual assets, colors, fonts, screenshots, and databases

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| `app_name_title.png` legacy wordmark | Brand asset | Remove | Original target wordmark; do not crop, recolor, or trace legacy art | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/app_name_title.png` | Checked |
| Zalith `Z` launcher/round/foreground icons at 5 densities | Brand asset | Remove | Original target launcher, round, and adaptive icons | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/mipmap-*/` | Finding — 15 legacy PNGs plus 2 adaptive XML files |
| `ic_pojav_full.webp` | Upstream logo asset | Remove | No copied Pojav logo; separately selected dependencies retain their own upstream notices | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/ic_pojav_full.webp` | Checked |
| Legacy Minecraft/Fabric/Quilt/NeoForge/Modrinth/CurseForge/OptiFine marks | Third-party brand assets | Remove as copied assets | Use factual text references only where separately approved; no copied logos/icons | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/` | Finding — third-party artwork present |
| Legacy grass/cobblestone/chicken/anvil/command-block/game images | Game artwork | Remove | No copied game textures/art | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/` | Finding |
| `steve.png` | Game/avatar artwork | Remove | No copied game avatar; independently licensed or original target art only | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/steve.png` | Finding — provenance unresolved |
| 54 legacy light/dark color declarations | Visual design | Replace | Independently designed Eclipse palette; no wholesale copy | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/values/colors.xml`; `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/values-night/colors_night.xml` | Checked — 28 light, 26 dark |
| Nine legacy `strings.xml` resource sets | Localized UI copy | Remove | Original target translations only; no legacy identity/about/support copy | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/values*/strings.xml` | Checked — 9 sets |
| Nine launcher/game screenshots | Promotional screenshot | Remove | Future screenshots must show only original target UI and cleared content | `/root/Project/ref/ZalithLauncher/.github/images/` | Checked |
| `mod_data.txt` with 22,611 records | Database/data compilation | Remove | No legacy database; future data requires independent license/provenance | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/mod_data.txt` | Finding — includes rights metadata conflict |
| `modpack_data.txt` with 1,219 records | Database/data compilation | Remove | No legacy database; future data requires independent license/provenance | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/modpack_data.txt` | Finding — includes rights metadata conflict |
| No legacy font files | Font finding | Preserve absence | Any future font must be independently licensed and recorded | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/` | Checked — 0 TTF/OTF/WOFF/WOFF2 |

## Binary, archive, runtime, and source-derived filenames

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| JRE 8/17/21/25 `universal.tar.xz` and `bin-*.tar.xz` patterns, 19 archives | Runtime binary | Remove | Independently build/provenance/verify target runtimes; no copied archive | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/jre-*/` | Finding — archives not unpacked |
| `pro-grade.jar` | Binary filename | Remove as copied artifact | Select only a current upstream artifact with compatible license and SBOM entry | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/components/pro-grade.jar` | Finding — contents not verified |
| `forge_installer.jar` | Binary filename | Remove as copied artifact | Future installer/download logic must be independently designed and sourced | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/components/forge_installer.jar` | Finding |
| `OptiFineRenamer.jar` | Binary filename | Remove as copied artifact | No legacy helper binary; future compatibility behavior must be newly implemented or omitted | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/components/OptiFineRenamer.jar` | Finding |
| `MioLibPatcher.jar` | Contributor binary filename | Remove | No legacy contributor binary or name | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/components/MioLibPatcher.jar` | Finding |
| `MioFabricAgent.jar` | Contributor binary filename | Remove | No legacy contributor binary or name | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/components/MioFabricAgent.jar` | Finding |
| `cacio-tta-1.19.1-SNAPSHOT.jar` | Snapshot binary filename | Remove | No copied snapshot; future artifacts must be pinned to verified releases | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/caciocavallo17/` | Finding |
| `cacio-shared-1.19.1-SNAPSHOT.jar` | Snapshot binary filename | Remove | No copied snapshot | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/caciocavallo17/` | Finding |
| `cacio-agent.jar` | Binary filename | Remove | No copied injector/agent | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/caciocavallo17/` | Finding |
| `cacio-androidnw-1.10-SNAPSHOT.jar` | Snapshot binary filename | Remove | No copied snapshot | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/caciocavallo/` | Finding |
| `cacio-shared-1.10-SNAPSHOT.jar` | Snapshot binary filename | Remove | No copied snapshot | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/caciocavallo/` | Finding |
| `ResConfHack.jar` | Binary filename | Remove | No copied helper binary | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/caciocavallo/ResConfHack.jar` | Finding |
| `authlib-injector.jar` | Injector binary filename | Remove | No legacy authentication injector; future auth requires a new security design | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/other_login/authlib-injector.jar` | Finding |
| `nide8auth.jar` | Authentication binary filename | Remove | No legacy auth helper/binary | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/other_login/nide8auth.jar` | Finding |
| `lwjgl3-glfw-classes.jar` | Runtime/helper binary filename | Remove as copied artifact | Independently select a verified LWJGL artifact if later approved | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/lwjgl3/lwjgl3-glfw-classes.jar` | Finding |
| `gson-2.8.6.jar` | Local dependency filename | Remove as copied artifact | Future target should select and pin a current compatible dependency under Apache-2.0 policy | `/root/Project/ref/ZalithLauncher/ZalithLauncher/libs/gson-2.8.6.jar` | Finding |
| `openal-soft-release.aar` | Local binary filename | Remove as copied artifact | Independently source/verify any required audio component | `/root/Project/ref/ZalithLauncher/ZalithLauncher/libs/openal-soft-release.aar` | Finding |
| `exp4j-0.4.9-SNAPSHOT.jar` | Snapshot binary filename | Remove | No snapshot dependency; use a pinned verified release only if needed | `/root/Project/ref/ZalithLauncher/ZalithLauncher/libs/exp4j-0.4.9-SNAPSHOT.jar` | Finding |
| `ExagearApacheCommons.jar` | Local binary filename | Remove | No legacy helper binary | `/root/Project/ref/ZalithLauncher/ZalithLauncher/libs/ExagearApacheCommons.jar` | Finding |
| `lwjgl3-natives-release.aar` | Local native-binary filename | Remove as copied artifact | Independently source/verify any required native component | `/root/Project/ref/ZalithLauncher/ZalithLauncher/libs/lwjgl3-natives-release.aar` | Finding |
| Eleven JARs under `jre_lwjgl3glfw` | Build/helper binaries | Remove | No copied module JARs; future dependency names must come from independently selected upstream releases | `/root/Project/ref/ZalithLauncher/jre_lwjgl3glfw/` | Finding — count confirmed; exact target replacement set unselected |
| `gradle-wrapper.jar` | Build binary filename | Remove as copied artifact | Verify/regenerate wrapper from official Gradle distribution | `/root/Project/ref/ZalithLauncher/gradle/wrapper/gradle-wrapper.jar` | Checked |
| `libunpack200.so`, `libshaderconv.so`, `libjnidispatch.so`, `libimgui-java.so`, `libimgui-javaarm.so`, `libimgui-javaarm64.so`, `libgl4es_114.so`, `libc++_shared.so`, `libGLESv2_angle.so`, `libEGL_angle.so`, `libepoxy_0.so`, `libOSMesa_8.so`, `libOSMesa_2300d.so`, `libOSMesa_2121.so`, `libtwitchsdk.so`, `libvirgl_test_server.so`, `libvirglrenderer_1.so`, `libvulkan_freedreno.so`, `libVkLayer_khronos_timeline_semaphore.so` | 19 unique native binary filenames, 51 ABI instances | Remove all legacy instances | No native binary copy; an independently sourced upstream artifact may retain its conventional name only after license/hash/provenance verification | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/jniLibs/` | Finding — exact names/count checked; contents/license not unpacked |
| Legacy C/C++ source filenames under `jni` | Native source | Remove | No copied or translated native source; independently implement from requirements and approved upstream components | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/jni/` | Finding — 23 C/C++, 18 headers, 2 make files |
| `java_sandbox.policy` and two Log4J patch XML files | Runtime/config filenames | Remove | No copied runtime hardening/configuration | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/components/` | Checked |
| Nine legacy component `version` marker files | Version/data filenames | Remove | Future version metadata must be independently defined and integrity-protected | `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/components/` | Checked |

## CI, tests, and source-set exclusions

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| `.github/workflows/android.yml` | CI build file | Remove | No legacy CI; future CI must be newly authored with tests, lint, dependency/license checks, and secure signing | `/root/Project/ref/ZalithLauncher/.github/workflows/android.yml` | Checked |
| Five-ABI legacy release/debug matrix | CI/build behavior | Remove | Independently select target ABIs and test coverage | `/root/Project/ref/ZalithLauncher/.github/workflows/android.yml` | Checked |
| Legacy `assembleRelease` / `assembleDebug` workflow commands | Build command | Remove | Future commands and signing paths must be target-owned | `/root/Project/ref/ZalithLauncher/.github/workflows/android.yml` | Checked |
| No conventional application unit-test source set | Test finding | Replace | Phase 1 adds an original application-identity unit test; further repository tests are independently designed | `/root/Project/EclipseLauncher/app/src/test/` | Checked |
| No conventional application instrumentation-test source set | Test gap | Replace in Phase 2 | `:feature:home` now has original connected Compose semantics and screenshot-contract tests; navigation tests remain for later phases | `/root/Project/EclipseLauncher/feature/home/src/androidTest/` | Checked — run 36040467149 passed |
| No explicit test/lint CI job | CI gap | Replace | CI runs unit tests, lint, debug assembly, APK inspection, and a separate connected Compose suite without publishing workflow artifacts | `/root/Project/EclipseLauncher/.github/workflows/ci.yml`; `/root/Project/EclipseLauncher/.github/workflows/ui-test.yml` | Checked — runs 36040467221 and 36040467149 passed |
| `jre_lwjgl3glfw` source/module | Module/source set | Remove | No legacy helper module; target begins with one independently configured app module | `/root/Project/ref/ZalithLauncher/jre_lwjgl3glfw/` | Checked |
| `ZalithLauncher` Android module | Module/source set | Replace | Target app module is `:app` under `me.shadow.eclipse` | `/root/Project/EclipseLauncher/app/` | Checked |
| Legacy generated source set | Build source set | Remove | Target uses no legacy generated package or path | `/root/Project/EclipseLauncher/app/build.gradle.kts` | Checked |

## Mandatory target verification

| Old occurrence | Type | Action | New value or removal reason | File/resource | Verified |
|---|---|---|---|---|---|
| Any legacy occurrence listed in this manifest | Release-gate check | Block release | Fail scanning/build if prohibited old user-facing names, IDs, URLs, assets, binaries, or data enter target | `/root/Project/EclipseLauncher` | Checked for Phase 2 source/resources/manifest; repeat every phase |
| Any dependency or binary copied from the legacy path | Provenance check | Block release | Artifact must have independent source, version, hash, license, notice, and vulnerability record | `/root/Project/EclipseLauncher` | Checked — dependencies come from official repositories; wrapper hash recorded |
| Any ads/analytics/telemetry/sponsor/news/social integration | Policy check | Block release | Prohibited by approved target policy | `/root/Project/EclipseLauncher` | Checked for Phase 2; repeat on every dependency/build change |
| Any Easter egg or copied sample | Policy/provenance check | Block release | Prohibited; fixtures and assets must be original and synthetic | `/root/Project/EclipseLauncher` | Checked for Phase 2; repeat during review |
| Any copied legacy source or direct translation | Clean-room check | Block release | Source must be independently implemented from approved requirements/public interfaces | `/root/Project/EclipseLauncher` | Checked for Phase 2 |
| Any missing target Apache-2.0 or direct third-party notice | License check | Block release | Root license and direct notices are present; final transitive notices remain a release gate | `/root/Project/EclipseLauncher` | Checked for Phase 2 |
| Any use of `me.shadow.eclipse.debug` in release | Build-identity check | Block release | Debug ID is approved only for the debug variant; release workflow checks `me.shadow.eclipse` | `/root/Project/EclipseLauncher` | Checked in workflow configuration; signed release pending secrets |
| Any user-facing occurrence of a legacy name | Rebranding check | Block release | Legacy names are allowed only in audit/legal records, never app copy or runtime identity | `/root/Project/EclipseLauncher` | Checked for app source/resources/manifest |

Phase 2 adds only Eclipse-owned design tokens, geometry, strings, and test fixtures. The Home source scan found no legacy user-facing identity, URL, social/news/sponsor integration, copied asset, or prohibited binary. The scan is repeated whenever a module, dependency, resource, or workflow changes.

## Final manifest rule

The target identity is limited to `Eclipse Launcher`, `me.shadow.eclipse`, and `me.shadow.eclipse.debug` as approved. Every other legacy occurrence is either excluded, independently reimplemented from requirements, or separately reobtained and verified. No old link, social identity, news/sponsor system, advertising, analytics, telemetry, Easter egg, sample, signing key, or copied legacy binary is approved.
