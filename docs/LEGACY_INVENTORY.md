# Legacy inventory and clean-room boundary

## Audit boundary

- **Target repository:** `/root/Project/EclipseLauncher`
- **Read-only legacy reference:** `/root/Project/ref/ZalithLauncher`
- **Target state at audit start:** empty directory (`0` entries).
- **Files created by Phase 0:** `docs/LEGACY_INVENTORY.md`, `docs/UI_SPEC.md`, `docs/UI_PARITY_MATRIX.md`, `docs/REBRANDING_MANIFEST.md`, `docs/DECISION_QUEUE.md`, `docs/TECH_STACK.md`, and `docs/PROVENANCE.md` only.
- **Reference relationship:** the legacy reference is **excluded from the target repository**. It is not copied, vendored, referenced as a source dependency, configured as a Git submodule, symlinked, or treated as target implementation input.
- **Use restriction:** `/root/Project/ref/ZalithLauncher` is retained outside the target only as a read-only comparison and legal/provenance reference. The target must be a clean-room implementation based on approved requirements, public interfaces, and separately licensed dependencies and assets.
- **Copy prohibition:** no legacy source, branding, artwork, screenshots, databases, keys, credentials, account data, or opaque binaries may be copied into the target.
- **Name-use policy:** legacy names and URLs appear in this audit only where needed to identify a finding. They must never appear in target user-facing copy, metadata, notifications, links, or network identity.
- **Secret handling:** password values, access/refresh tokens, and the hard-coded OAuth client identifier are not reproduced. The presence and ownership of sensitive inputs are recorded instead.

## Status meanings used in this audit

- **Checked:** the occurrence was directly identified at the stated legacy path; the initially empty target contains no app implementation in which it could occur.
- **Unchecked:** no target implementation or selected replacement artifact exists yet, so future verification is required.
- **Finding:** a prohibited, unresolved, high-risk, or legally unresolved occurrence was identified.

## 1. Empty target inventory

At audit start, `/root/Project/EclipseLauncher` contained no files or directories. The approved target identity and policy are recorded here for future implementation planning, but no app scaffolding was created.

| Target category | Count at audit start | Current audit action |
|---|---:|---|
| Application modules | 0 | No module created |
| Kotlin/Java source files | 0 | No source copied or created |
| Gradle/build files | 0 | No build created |
| Android manifests | 0 | No manifest created |
| Resource files | 0 | No resource copied or created |
| Native source/build files | 0 | No native code copied or created |
| Native binaries | 0 | No `.so` copied |
| Assets | 0 | No asset copied |
| Databases/data packs | 0 | No database copied |
| Dependency declarations | 0 | No dependency selected |
| Local JAR/AAR files | 0 | No binary copied |
| License/notice files | 0 | Apache-2.0 is approved; the root legal files are scheduled for Phase 1 after the Phase 0 documentation gate |
| Tests | 0 | No test source set or test created |
| CI workflows | 0 | No workflow copied or created |
| Screens/activities/fragments | 0 | No app screen created |
| Screenshots | 0 | No screenshot copied |
| Manifest components/permissions | 0 | No component or permission declared |
| Deep links | 0 | None registered |
| Analytics/ads/telemetry | 0 | None added |
| Social/news/sponsor links | 0 | None added |
| Easter eggs | 0 | None added |
| Notifications | 0 | None added |

## 2. Approved target baseline for future implementation

These values are requirements, not existing target files:

| Setting | Approved value |
|---|---|
| User-facing product name | `Eclipse Launcher` |
| Application ID / namespace | `me.shadow.eclipse` |
| Debug application ID | `me.shadow.eclipse.debug` |
| Project license | `Apache-2.0` |
| Implementation model | Clean-room, independent implementation |
| Legacy source/assets/data/binaries | Excluded |
| Legacy links/social/news/sponsor | Excluded |
| Advertising | Excluded |
| Analytics | Excluded |
| Telemetry | Excluded |
| Easter eggs | Excluded |
| External deep links | None unless separately approved in a future requirements decision |
| Signing identity | Must be newly generated outside the repository; no legacy key may be used |

Provider authorities, process names, artifact filenames, notification channels, and OAuth registrations were not approved by this task. They must be independently designed under `me.shadow.eclipse`; they must not be mechanical replacements of legacy identifiers.

## 3. Legacy top-level files and modules

Legacy root:

- `/root/Project/ref/ZalithLauncher`

Important top-level items:

| Path | Role | Clean-room disposition |
|---|---|---|
| `/root/Project/ref/ZalithLauncher/settings.gradle.kts` | Root name and module inclusion | Do not copy; module names must be independently designed |
| `/root/Project/ref/ZalithLauncher/build.gradle.kts` | Root buildscript and plugins | Do not copy; select and review independently |
| `/root/Project/ref/ZalithLauncher/gradle.properties` | Legacy root properties | Do not copy |
| `/root/Project/ref/ZalithLauncher/gradle/wrapper/gradle-wrapper.jar` | Gradle wrapper binary | Do not copy; verify/regenerate from official Gradle |
| `/root/Project/ref/ZalithLauncher/jre_lwjgl3glfw/` | Runtime/LWJGL helper module | Exclude from target; independently implement or select upstream components |
| `/root/Project/ref/ZalithLauncher/ZalithLauncher/` | Android application module | Exclude from target; clean-room only |
| `/root/Project/ref/ZalithLauncher/.github/` | CI, issue templates, screenshots | Exclude from target |
| `/root/Project/ref/ZalithLauncher/.git/config` | Legacy Git origin metadata | Exclude; never initialize target from this repository |
| `/root/Project/ref/ZalithLauncher/LICENSE` | Legacy GPLv3 license | Exclude as target project license |
| `/root/Project/ref/ZalithLauncher/README.md` | Legacy identity and acknowledgements | Audit reference only |
| `/root/Project/ref/ZalithLauncher/README_ZH_CN.md` | Legacy localized identity | Audit reference only |
| `/root/Project/ref/ZalithLauncher/README-ZH_TW.md` | Legacy localized identity | Audit reference only |

### Gradle modules

`/root/Project/ref/ZalithLauncher/settings.gradle.kts` declares:

1. Root project name: `Zalith Launcher`.
2. `:jre_lwjgl3glfw`.
3. `:ZalithLauncher`.

Neither module exists in the target. Their names, source, build logic, and outputs are excluded.

## 4. Legacy application identity and build state

Primary files:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/gradle.properties`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml`

| Property | Legacy value | Target rule |
|---|---|---|
| Build name | `ZalithLauncher` | Do not reuse |
| Display name | `Zalith Launcher` | Use approved `Eclipse Launcher` only in future app copy |
| Namespace/application ID | `com.movtery.zalithlauncher` | Use `me.shadow.eclipse` in a future build |
| Debug suffix | `.debug` | Use `me.shadow.eclipse.debug` |
| Version name | `1.4.1.4` | Start an independent version scheme |
| Version code | `141400` | Do not reuse |
| Min SDK | 26 | Approved target baseline: `minSdk 26` |
| Target/compile SDK | 34 | Approved target baseline: compile/target SDK 36 |
| ABI splits | all, arm, arm64, x86, x86_64 | Reassess independently |
| Generated source directory | `$buildDir/generated/source/zalith/java` | Remove; no legacy generation path |
| Generated class | `com.movtery.zalithlauncher.InfoDistributor` | Remove; design a new mechanism if needed |
| Release APK | `ZalithLauncher-<version>[-ABI].apk` | New Eclipse-owned artifact name required |
| Debug APK | `ZalithLauncher-Debug-<version>[-ABI].apk` | New Eclipse-owned artifact name required |

### Build types and states

The legacy application declares:

- `debug`.
- `release`.
- `proguard`.
- `proguardNoDebug`.

Additional legacy build-state values are injected through `ZL_BUILD_TYPE`:

- `DEBUG` (the default when the environment value is absent).
- `PRE_RELEASE`.
- `RELEASE`.

`com.movtery.zalithlauncher.InfoDistributor.BUILD_TYPE`, launcher name, app name, and CurseForge key are generated into source before build. StringFog obfuscates the configured package but does not make embedded key material secret.

The read-only audit did **not** build, sign, install, or execute the legacy application. Consequently:

- No reproducible-build claim is made.
- No passing-test claim is made.
- No release-signature validation was performed.
- Runtime behavior of opaque binaries remains unverified.

## 5. Legacy source sets and source families

### Android application source sets

Primary source root:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/`

The application mixes Kotlin and Java in that source directory. Major package families include:

- `com.movtery.zalithlauncher` — primary legacy application code.
- `com.movtery.anim` — animation helpers.
- `com.mio.util` — contributor/identity-derived utility package.
- `com.kdt` — upstream launcher UI/utilities.
- `net.kdt.pojavlaunch` — upstream Pojav-derived implementation.
- `org.jackhuang.hmcl` — HMCL-derived code.
- `org.lwjgl` — LWJGL integration.
- `com.oracle.dalvik` — Oracle-derived package.
- `dalvik.annotation.optimization` — critical-native optimization support.

All are excluded. The target must not preserve these package paths through a package rename.

### Other application source/build areas

| Path | Contents | Count/status |
|---|---|---|
| `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/jni/` | C/C++ native implementation and make files | 43 files: 23 C/C++, 18 headers, 2 make files |
| `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/` | Android resources | Excluded |
| `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/` | Bundled data, runtimes, JARs, configuration | 51 files |
| `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/jniLibs/` | Prebuilt native libraries | 51 `.so` instances over 4 ABIs |
| `/root/Project/ref/ZalithLauncher/ZalithLauncher/libs/` | Local JAR/AAR dependencies | 5 binaries |
| `/root/Project/ref/ZalithLauncher/jre_lwjgl3glfw/src/main/java/` | LWJGL/Cacio/helper module source | Excluded |

### Tests

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/test/` — not present.
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/androidTest/` — not present.
- No conventional unit-test or instrumentation-test source set was identified in the application module.
- CI builds artifacts but does not run an explicit test, lint, or instrumentation job.

Target test policy: future tests must be newly designed around approved requirements. Legacy tests, fixtures, samples, and expected outputs must not be copied.

## 6. Legacy dependencies

### Repositories and build plugins

- Google Maven.
- Maven Central.
- JitPack (`https://jitpack.io`).
- Gradle Plugin Portal.
- Android Gradle Plugin `8.2.2`.
- Kotlin Android plugin `2.0.21`.
- StringFog Gradle plugin `5.2.0` and XOR runtime `5.0.0`.

A legacy JitPack coordinate or commit does not transfer to the target. Every future dependency needs a fresh version, source, hash, license, and vulnerability review under the Apache-2.0 policy.

### Declared direct dependencies

The following coordinates were found in `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts`:

- `javax.annotation:javax.annotation-api:1.3.2`
- `commons-codec:commons-codec:1.17.1`
- `androidx.drawerlayout:drawerlayout:1.2.0`
- `androidx.viewpager2:viewpager2:1.1.0-beta01`
- `androidx.annotation:annotation:1.7.0`
- `androidx.constraintlayout:constraintlayout:2.1.4`
- `androidx.core:core-ktx:1.13.0`
- `androidx.palette:palette-ktx:1.0.0`
- `com.github.duanhong169:checkerboarddrawable:1.0.2`
- `com.github.PojavLauncherTeam:portrait-sdp:ed33e89cbc`
- `com.github.PojavLauncherTeam:portrait-ssp:6c02fd739b`
- `com.github.Mathias-Boulay:ExtendedView:1.0.0`
- `com.github.Mathias-Boulay:android_gamepad_remapper:2.0.3`
- `com.github.Mathias-Boulay:virtual-joystick-android:1.14`
- `com.github.skydoves:powerspinner:1.2.7`
- `com.github.bumptech.glide:glide:4.16.0`
- `com.github.angcyo.DslTablayout:TabLayout:3.6.5`
- `com.github.megatronking.stringfog:xor:5.0.0`
- `top.fifthlight.touchcontroller:proxy-client-android:0.0.2`
- `org.tukaani:xz:1.9`
- `net.sourceforge.htmlcleaner:htmlcleaner:2.6.1`
- `com.bytedance:bytehook:1.0.10`
- `com.squareup.okhttp3:okhttp:4.12.0`
- `org.commonmark:commonmark:0.19.0`
- `com.google.android.material:material:1.12.0`
- `com.google.android.flexbox:flexbox:3.0.0`
- `com.getkeepsafe.taptargetview:taptargetview:1.14.0`
- `io.github.petterpx:floatingx:2.3.3`
- `org.greenrobot:eventbus:3.3.1`
- `com.moandjiezana.toml:toml4j:0.7.2`, excluding its declared Gson version

### Local binary dependencies

`/root/Project/ref/ZalithLauncher/ZalithLauncher/libs/` contains:

- `gson-2.8.6.jar`
- `openal-soft-release.aar`
- `exp4j-0.4.9-SNAPSHOT.jar`
- `ExagearApacheCommons.jar`
- `lwjgl3-natives-release.aar`

Additional opaque JARs exist in the assets tree and runtime module. Their contents were not unpacked for this read-only audit. They are not approved target dependencies.

## 7. Native code and binaries

### Native source

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/jni/`

The source tree includes launcher/exec, AWT bridge, input bridge, exit hooks, LWJGL loading, EGL/GL bridges, OSMesa, virgl, linker hooks, affinity logic, and GL headers. Visible notices refer to Oracle/OpenJDK, Mesa, Khronos, AOSP, LWJGL, Pojav, and contributors.

These files are reference-only. No target native implementation is approved by copying or translating them.

### Prebuilt native libraries

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/jniLibs/arm64-v8a/`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/jniLibs/armeabi-v7a/`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/jniLibs/x86/`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/jniLibs/x86_64/`

Inventory:

- 51 `.so` instances.
- 4 ABIs.
- 19 unique filenames.

Unique legacy filenames:

- `libunpack200.so`
- `libshaderconv.so`
- `libjnidispatch.so`
- `libimgui-java.so`
- `libimgui-javaarm.so`
- `libimgui-javaarm64.so`
- `libgl4es_114.so`
- `libc++_shared.so`
- `libGLESv2_angle.so`
- `libEGL_angle.so`
- `libepoxy_0.so`
- `libOSMesa_8.so`
- `libOSMesa_2300d.so`
- `libOSMesa_2121.so`
- `libtwitchsdk.so`
- `libvirgl_test_server.so`
- `libvirglrenderer_1.so`
- `libvulkan_freedreno.so`
- `libVkLayer_khronos_timeline_semaphore.so`

No legacy `.so` may be copied. A future target may independently acquire an upstream component that uses the same conventional filename only after provenance, license, hash, and vulnerability verification.

## 8. Assets, visual identity, screenshots, and data

### Drawables and launcher icons

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/`
  - 102 top-level entries.
  - 22 obvious raster files: 19 PNG and 3 WebP.
  - 80 XML files.
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/mipmap-hdpi/`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/mipmap-mdpi/`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/mipmap-xhdpi/`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/mipmap-xxhdpi/`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/mipmap-xxxhdpi/`
  - 15 density PNGs total: launcher, round launcher, and foreground variants.
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/mipmap-anydpi-v26/`
  - 2 adaptive-icon XML files.

Legacy wordmark:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/app_name_title.png`

Personal/service portraits:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/image_about_movtery.png`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/image_about_mcmod.png`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/image_about_verafirefly.png`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/image_about_lingmuqiuzhu.png`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/image_about_shirosakimio.png`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/image_about_bangbang93.png`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/drawable/image_xibao.png`

Third-party/game/Pojav marks and textures are also present, including Minecraft, Fabric, Quilt, NeoForge, Modrinth, CurseForge, OptiFine, Pojav, grass, cobblestone, chicken, anvil, and command-block imagery. Compatibility names may be factual references in a future requirements document, but no legacy mark or artwork may be copied.

No TTF, OTF, WOFF, or WOFF2 font files were found.

### Colors and localized resources

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/values/colors.xml` — 28 declarations.
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/values-night/colors_night.xml` — 26 declarations.
- Total palette declarations: 54.

Nine `strings.xml` sets exist for the default resources and Arabic, German, French, Portuguese-Brazil, Russian, Vietnamese, Simplified Chinese, and Traditional Chinese resources. All legacy localized identity copy is excluded from the target.

### Screenshots

- `/root/Project/ref/ZalithLauncher/.github/images/`

Nine files are present:

- `Screenshot_Launcher_Light_ZH_CN.jpg`
- `Screenshot_Launcher_Light_ZH_TW.jpg`
- `Screenshot_Launcher_Light_EN_US.jpg`
- `Screenshot_Launcher_Dark_ZH_CN.jpg`
- `Screenshot_Launcher_Dark_ZH_TW.jpg`
- `Screenshot_Launcher_Dark_EN_US.jpg`
- `Screenshot_Game_ZH_CN.jpg`
- `Screenshot_Game_ZH_TW.jpg`
- `Screenshot_Game_EN_US.jpg`

No target screenshot exists. Future screenshots must show only independently designed target UI and independently cleared content.

### Bundled assets

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/`
- 51 files total.
- 19 JRE `.tar.xz` archives and 4 JRE version markers for JRE 8, 17, 21, and 25.
- 14 asset JARs, including Pojav-derived helpers, injectors, installers, and Cacio components.
- 9 component version markers.
- 2 Log4J patch XML files.
- 1 Java sandbox policy.
- 6 root-level files: `steve.png`, `resolv.conf`, `options.txt`, `default.json`, `mod_data.txt`, and `modpack_data.txt`.

Game-related avatar requiring provenance review:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/steve.png`

Opaque asset areas include JRE archives, `MioLibPatcher.jar`, `MioFabricAgent.jar`, `forge_installer.jar`, `OptiFineRenamer.jar`, `pro-grade.jar`, Cacio JARs, `authlib-injector.jar`, and `nide8auth.jar`. None is approved for target use.

### Databases

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/mod_data.txt` — 22,611 mod records.
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/assets/modpack_data.txt` — 1,219 modpack records.
- Total: 23,830 records.

The files identify Hello Minecraft/MC百科 and include copyright and “All Rights Reserved” metadata. Repository-level GPL wording does not resolve the separate database rights. The datasets are excluded unless the rights owner later grants explicit redistribution permission.

## 9. Licenses, notices, and unresolved legal provenance

### Project license

- `/root/Project/ref/ZalithLauncher/LICENSE` — GPLv3, with a ZalithLauncher/MovTery notice.

The legacy README states that the project is based on Pojav Launcher, that Pojav is based on Boardwalk, and that some HMCL source is used. This is incompatible with treating the reference as permissively licensed source for an Apache-2.0 target.

### Notice coverage

- 3 README files.
- 1 root GPL license.
- 0 standalone third-party `LICENSE`, `NOTICE`, or `COPYING` files were found.
- The root README provides acknowledgements, not a complete dependency/SBOM or corresponding-source package.
- Seven in-app acknowledgements are not a substitute for license compliance.

Unresolved or high-risk items include Boardwalk and LWJGLX licensing, OpenJDK source obligations, native libraries, local JAR/AARs, injectors/installers, asset JARs, game art, portraits, and the mod databases.

Future target licensing obligations:

- Original target code may use Apache-2.0.
- No legacy GPL source may be copied.
- Separately selected dependencies must be compatible with the intended distribution model and must have complete license/notice handling.
- A dependency license may impose requirements beyond the target's own Apache-2.0 grant.

## 10. Android manifest, package IDs, components, and permissions

Manifest:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/AndroidManifest.xml`

### Application identity

- Namespace/application ID: `com.movtery.zalithlauncher`.
- Debug application ID suffix: `.debug`.
- Application class: `net.kdt.pojavlaunch.PojavApplication`.
- Default process suffix: `:launcher`.
- Game process suffix: `:game`.
- GUI installer process suffix: `:gui_installer`.
- Storage authority: legacy package plus `.storage_provider`; debug appends `.debug`.
- File authority: `${applicationId}.provider`.

All are excluded. The future target may use only independently chosen components/authorities/processes under the approved package identity.

### Activities: 8

| Legacy component | Export status in legacy | Target disposition |
|---|---|---|
| `com.movtery.zalithlauncher.ui.activity.SplashActivity` | Exported launcher entry | Exclude class and layout; independently design |
| `net.kdt.pojavlaunch.MissingStorageActivity` | Not exported | Exclude |
| `net.kdt.pojavlaunch.LauncherActivity` | Not explicitly exported | Exclude |
| `com.movtery.zalithlauncher.ui.activity.ErrorActivity` | Not explicitly exported | Exclude |
| `net.kdt.pojavlaunch.ShowErrorActivity` | Not explicitly exported | Exclude |
| `net.kdt.pojavlaunch.JavaGUILauncherActivity` | `:gui_installer` process | Exclude |
| `net.kdt.pojavlaunch.CustomControlsActivity` | Not exported | Exclude |
| `net.kdt.pojavlaunch.MainActivity` | `:game` process | Exclude |

### Services: 2

| Legacy component | Type/state | Target disposition |
|---|---|---|
| `net.kdt.pojavlaunch.services.ProgressService` | Foreground, `dataSync` | Exclude; independently justify any future foreground use |
| `net.kdt.pojavlaunch.services.GameService` | `:game`, foreground, `specialUse` | Exclude; independently justify any future foreground use |

### Providers: 2

| Legacy component | Authority/export | Target disposition |
|---|---|---|
| `net.kdt.pojavlaunch.scoped.FolderProvider` | Legacy storage authority; exported with `MANAGE_DOCUMENTS` | Exclude and redesign storage access |
| `androidx.core.content.FileProvider` | `${applicationId}.provider`; not exported | Library component may be independently used under a new authority, but legacy path XML is excluded |

### Permissions: 13

- `android.permission.POST_NOTIFICATIONS`
- `android.permission.WRITE_EXTERNAL_STORAGE`
- `android.permission.READ_EXTERNAL_STORAGE`
- `android.permission.MANAGE_EXTERNAL_STORAGE`
- `android.permission.REQUEST_INSTALL_PACKAGES`
- `android.permission.INTERNET`
- `android.permission.ACCESS_NETWORK_STATE`
- `android.permission.RECORD_AUDIO`
- `android.permission.FOREGROUND_SERVICE`
- `android.permission.FOREGROUND_SERVICE_DATA_SYNC`
- `android.permission.FOREGROUND_SERVICE_SPECIAL_USE`
- `android.permission.WAKE_LOCK`
- `android.permission.VIBRATE`

No target permission is approved by inheritance. Future permissions require a feature-level necessity and privacy review. In particular, microphone, broad storage, package-install, and foreground-service permissions must not be carried over automatically.

### Exported surfaces and deep links

- Main launcher activity is exported because it is the app entry point.
- `FolderProvider` is exported with a documents-provider permission.
- `FileProvider` is not exported.
- No registered HTTP or application deep-link intent filter was found.
- `pojav://` is an internal runtime-prefix sentinel, not a registered external deep link.
- An explicit `CustomControlsActivity` action exists, but the activity is not exported.

No target deep link exists or is approved.

### Network security

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/res/xml/network_security_config.xml`
- Cleartext traffic is globally permitted.

The target has no network-security file. A future target should require TLS by default and must not copy the global cleartext allowance.

## 11. Notifications

Legacy notification inventory:

- 2 foreground services.
- 1 notification channel.
- 5 notification IDs.
- Notification-permission prompting.
- Launcher-icon/branding reused in notification surfaces.

The target has no notification channel, ID, icon, service, copy, or permission. Future notifications, if separately justified, must use new target-owned IDs, icons, copy, and lifecycle behavior. Legacy notification code and branding are excluded.

## 12. URLs, network identity, social links, and remote data

### Eight centralized legacy URL constants

Defined in:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt`

| Legacy destination | Purpose |
|---|---|
| `https://api.github.com/repos/ZalithLauncher/Zalith-Info/contents/` | Version, notice, and sponsor data base |
| `https://www.mcmod.cn/` | MC百科 link/data service |
| `https://www.minecraft.net/` | Minecraft site link |
| `https://piston-meta.mojang.com/mc/game/version_manifest_v2.json` | Mojang version metadata |
| `https://afdian.com/a/MovTery` | Legacy sponsorship |
| `https://github.com/ZalithLauncher/ZalithLauncher` | Legacy project home |
| `https://github.com/ShirosakiMio/FCLRendererPlugin/releases/tag/Renderer` | Renderer plugin release |
| `https://github.com/FCL-Team/FCLDriverPlugin/releases/tag/Turnip` | Driver plugin release |

These URLs must not appear in target code, resources, metadata, or user-facing copy. Future compatibility URLs must be independently selected from current official documentation and approved requirements; they must not be inherited from this list.

### Additional endpoint categories

- Mojang session server.
- Microsoft/Xbox OAuth, Xbox Live, XSTS, entitlement, and Minecraft-profile services.
- Modrinth APIs.
- CurseForge API.
- Forge/Fabric/Quilt/NeoForge resources.
- Third-party mirrors.
- FCL plugin/download endpoints.
- User-selected Yggdrasil/third-party authentication servers.

The hard-coded Microsoft OAuth client identifier is redacted. A future target requires its own registered application.

### Remote operational data

Three legacy GitHub-hosted JSON resources provide:

- Launcher version/update metadata.
- Notice/news content.
- Sponsor data.

Files:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/update/UpdateUtils.kt`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/notice/CheckNewNotice.kt`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/CheckSponsor.kt`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/ui/fragment/about/AboutSponsorPageFragment.kt`

Target policy:

- No legacy update, notice/news, or sponsor channel.
- No remote news polling.
- No sponsor UI, sponsor data, or sponsor avatar loading.
- No target update service has been approved or created.

### Network identity

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/utils/path/UrlManager.kt`

Legacy requests use a User-Agent composed from the legacy launcher name and version. No target User-Agent exists. A future target must identify only the approved target and version and must not claim legacy identity.

### Social and support links

Legacy surfaces include:

- GitHub project and CI badges.
- Legacy QQ group/community flow.
- Legacy Discord invite.
- Four Bilibili profile links in the About page.
- MC百科 link.
- Two AFDian sponsorship links.
- Contributor acknowledgements and portraits.
- Planned legacy domain `zalithlauncher.cn`.

Target policy:

- No old link or social account may be reused.
- No target social links are approved by this task.
- No target news/sponsor link is approved.

## 13. CI, tests, and build output

CI file:

- `/root/Project/ref/ZalithLauncher/.github/workflows/android.yml`

Legacy workflow characteristics:

- Two jobs: `BuildRelease` and `BuildDebug`.
- Five ABI modes: all, arm, arm64, x86, x86_64.
- JDK 17.
- Builds `:jre_lwjgl3glfw` before the application.
- Builds release or debug APK variants.
- Uses release keystore-password and CurseForge API-key secret inputs.
- Uploads Zalith-named artifacts.
- Contains no explicit unit-test, instrumentation, lint, license-scan, or SBOM job.

Target status:

- No CI file exists.
- No test exists.
- No build has been run.
- No artifact, APK, AAB, signing identity, or build-status badge exists.
- No legacy CI file may be copied.

## 14. Screens and user-interface families

No target screens exist. Legacy screen families include:

- Splash/launcher entry.
- Launcher and game activities.
- About, contributor, and sponsor pages.
- Account login/management.
- File/provider management.
- Version list, manager, selector, and configuration.
- Mod/resource download and installation.
- Forge/Fabric/Quilt/NeoForge/OptiFine download pages.
- Launcher/game/control/video settings.
- Experimental settings.
- Custom background, mouse, controls, and gamepad mapping.
- Update, progress, error, runtime, role-selection, and plugin dialogs.

No legacy activity, fragment, layout, navigation graph, menu, dialog, string, icon, screenshot, or design may be copied. A future target screen may implement the same independently documented functional requirement using original structure and assets.

## 15. States, hidden behavior, samples, and debug traces

### Product/runtime state families observed

- Debug, release, proguard, and proguard-no-debug builds.
- Debug, pre-release, and release build labels.
- Light and dark themes.
- Microsoft, offline/local, and third-party account types.
- Download, install, unpack, update, notice, and progress states.
- Foreground progress/game service states.
- Renderer and driver plugin discovery/configuration.
- Experimental settings state.

These are audit categories, not target requirements. The target currently implements no states.

### Hidden/easter-egg behavior

- Random title-click Easter egg.
- April Fools hair/Easter-egg behavior.

Both are prohibited in the target. No hidden or Easter-egg behavior may be ported.

### Experimental features

Three legacy experimental settings were identified. They are not approved target requirements. Future experimental features require a separate written decision and must not inherit legacy implementation or naming.

### Samples

- Four layouts use design-time `@tools:sample/avatars` references.
- No bundled sample user account was found.

Legacy sample data and layouts are excluded. Future tests must use clearly synthetic fixtures and must not contain real credentials, tokens, personal data, or copied legacy layouts.

### Debug/diagnostic traces

- `ZL_BUILD_TYPE` and generated `BUILD_TYPE`.
- `BuildConfig.DEBUG`-dependent behavior.
- Debug build-status presentation.
- `CriticalNativeTest` and `@CriticalNative`.
- GLFW input-debug functionality.
- Design-time sample references.
- Debug signing configuration and debug artifact names.
- StringFog string obfuscation.

The target has no debug implementation. Future debug facilities must be newly designed, must not expose internal build/security information in user-facing copy, and must be absent from production behavior unless explicitly required.

### Analytics, advertising, telemetry, and crash reporting

No explicit advertising SDK, analytics SDK, advertising-ID access, or hosted crash-reporting integration was found in visible legacy source. This is not a full binary attestation:

- Opaque dependencies were not unpacked.
- Future dependencies have not been selected.
- Automatic update/notice requests, sponsor-avatar requests, branded User-Agent requests, local logs, and user-shared logs are observable operational behavior and require exclusion from the target under the approved no-telemetry policy.

Target policy is stricter and definitive: no ads, analytics, telemetry, sponsor tracking, or crash-reporting backend is approved.

## 16. Secrets and account-data findings

Bundled signing files:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/movtery-key.jks`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/debug.keystore`

No keystore may be copied. If the release JKS signed public releases, it must be treated as compromised and handled by its owner.

CI/build secret surfaces are documented in:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/build.gradle.kts`
- `/root/Project/ref/ZalithLauncher/.github/workflows/android.yml`

No secret value is reproduced.

Legacy account persistence appears in:

- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/net/kdt/pojavlaunch/value/MinecraftAccount.java`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/accounts/OtherLoginHelper.kt`
- `/root/Project/ref/ZalithLauncher/ZalithLauncher/src/main/java/com/movtery/zalithlauncher/feature/login/OtherLoginApi.kt`

Serialized account data includes access, client, refresh, third-party account, and third-party password material. The target has no account store and must use a newly designed secure-storage model, a new OAuth registration, backup exclusion, and a strict no-telemetry policy.

## 17. Audit limitations and release blockers

The following legacy artifacts were not unpacked, so their complete embedded metadata, licenses, notices, and provenance remain unverified:

- JRE `.tar.xz` archives.
- Asset JARs.
- Local JAR/AAR dependencies.
- LWJGL module JARs.
- 51 native `.so` binaries.
- Keystore internals.

These are legacy blockers, not candidate target dependencies.

Before the corresponding later implementation phases begin, the following must be independently established:

1. The original target architecture and package structure (approved for Phase 1).
2. Apache-2.0-compatible dependency implementation and SBOM process (policy approved; files pending Phase 1).
3. Original visual identity and asset provenance (direction approved; trademark/legal clearance remains a release dependency).
4. New signing, OAuth, update, and support identities where those features are enabled.
5. Secure account and network implementation.
6. Test strategy with synthetic data.
7. Phase-by-phase verification that no legacy source, binary, data, link, social identity, news/sponsor system, ad/analytics/telemetry integration, Easter egg, sample, or debug trace has entered the target.

## Final clean-room rule

`/root/Project/ref/ZalithLauncher` is an external, read-only audit reference only. It is not part of `/root/Project/EclipseLauncher` and must never become a source dependency, vendored module, submodule, symlink, copied asset set, or implementation shortcut.
