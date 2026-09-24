# Eclipse Launcher UI Specification

## 1. Document status and authority

- This document is the normative product and UI specification for Eclipse Launcher.
- It is a clean-room design derived from the completed normalized audit of the legacy reference.
- The accompanying parity matrix is the normative 132-row coverage ledger.
- No legacy visual assets, application code, credentials, service endpoints, OAuth client configuration, or release URLs are included.
- Implementation is in its early phases. Matrix statuses describe required disposition; a row is marked implemented only when its required behavior and verification checks exist and pass.

## 2. Product scope

Eclipse Launcher is an Android Minecraft launcher supporting API 26 and newer on phones, tablets, and foldables. It is not a ChromeOS application.

### 2.1 In scope

- First-run and existing-user startup.
- Official Microsoft OAuth account authentication.
- Offline local accounts.
- Account selection, removal, refresh, and explicit empty states.
- Installed-game discovery, installation, selection, removal, renaming, copying, and configuration.
- Supported base versions and explicitly supported add-ons.
- Scoped instance content management for instances, mods, resource packs, saves, shader packs, screenshots, logs, and crash reports.
- A scoped content browser backed only by approved, documented sources.
- Java runtime management, memory allocation, launch arguments, and compatibility diagnostics.
- Touch controls, virtual mouse/keyboard, custom cursors, and optional custom touch layouts.
- In-game launcher controls, live settings, FPS/memory monitor, and logs.
- Crash recovery, support-data export, and required legal notices.
- In-app self-update presentation is excluded from the baseline until its endpoint, signing, rollback, and store-policy dependencies are approved; GitHub Releases remain the initial distribution channel.
- Day/night appearance, dynamic color where appropriate, and accessibility settings.

### 2.2 Explicitly excluded

- Social feeds, news cards, advertising, analytics, telemetry, and sponsor/funding pages.
- Easter eggs or hidden non-product experiences.
- Third-party account authentication providers other than official Microsoft OAuth.
- Standalone arbitrary JAR execution and its Java/AWT runner.
- An unrestricted device file manager.
- Broad all-files access or legacy external-storage permission requests.
- Unsigned executable renderer, driver, or arbitrary code plugins.
- Experimental settings exposed in the default build.
- ChromeOS support.
- Copying source code, branding, text, visual assets, or distinctive implementation details from the legacy reference.

### 2.3 Licensed-content rule

Eclipse may discover or install content only when:

1. the source is approved for the release;
2. its API and terms are documented;
3. required attribution is displayed;
4. the install flow exposes source, version, compatibility, integrity information, and dependency information;
5. installation requires explicit user consent; and
6. executable game content is confined to the selected game instance and is never loaded into the launcher process.

Mods and mod loaders may contain code that the game executes by design; they are not treated as launcher plugins. Hash/signature availability, source trust, compatibility, and user consent must be explicit. The product owner must separately approve any executable launcher/renderer/driver plugin architecture. Until that approval, launcher-plugin discovery, import, execution, and management surfaces remain absent.

## 3. Information architecture and layout-parity contract

### 3.1 Primary routes

The launcher has three primary routes, matching the observable first-generation interaction model:

1. **Home** — account, selected instance, launch, management shortcuts, and diagnostics sharing.
2. **Downloads** — version/content installation and task progress.
3. **Settings** — launcher, game/runtime, rendering, controls, accessibility, storage, diagnostics, and legal categories.

These routes are **not** presented as a new permanent bottom-navigation model. The recognizable shell remains a top app bar with the product title at the start and Downloads and Settings actions at the end. Home is the root route reached with system Back or the title/home action.

### 3.2 Launcher shell

The persistent shell follows this order:

1. edge-to-edge Material 3 top app bar;
2. Eclipse Launcher title at the leading edge;
3. Downloads action near the trailing edge;
4. Settings action at the trailing edge;
5. current destination content;
6. global task-progress dock at the bottom when work exists.

The title, Downloads, and Settings positions remain stable in every top-level route. Meaningful actions use labeled tooltips/content descriptions and at least 48dp touch targets. No news, sponsor, or announcement surface is added to this shell.

### 3.3 Home spatial hierarchy

When horizontal space is sufficient, Home preserves the recognizable two-pane relationship:

- **Left management pane:** approximately 65–68% of the width, containing a vertically scrollable set of large action rows for About, control-layout management, the selected instance directory, and diagnostic sharing. The removed arbitrary JAR action is replaced by a real instance/content management action rather than leaving a visual gap.
- **Right launch pane:** approximately 30–35% of the width, containing the current account in the upper region, selected instance/version and its management action in the lower-middle region, and the primary Launch action anchored near the bottom.
- A subtle Material 3 tonal divider may separate the panes; the legacy shadow treatment is not copied.

The account remains above the selected version, and the version remains above the Launch action. The management action remains adjacent to the selected version. The left action order remains stable unless a documented product decision changes it.

### 3.4 Compact portrait transformation

Portrait and narrow windows retain the same content order without forcing an unusably narrow right pane:

1. current account summary;
2. selected instance/version and management action;
3. primary Launch action;
4. management action rows;
5. global progress when present.

The Launch action may be pinned in a bottom app bar only while the management list is scrolled; its order and semantics remain unchanged. Management rows use the same icon-leading/text hierarchy as the two-pane layout. No bottom navigation is introduced.

### 3.5 Category navigation

- Downloads and Settings retain a category selector adjacent to their content.
- On medium/expanded windows, use a Material 3 navigation rail with the category order preserved top-to-bottom.
- On compact windows, transform the same ordered categories into a horizontally scrollable tab row or navigation drawer; do not reorder or omit categories.
- Use list-detail presentation where both list and detail remain usable.
- Preserve the familiar settings order: rendering, controls, game/runtime, launcher, then approved additional categories. The legacy experimental category is intentionally removed rather than retained as dead space.

### 3.6 Hierarchy and prerequisites

- Launch and account state are global prerequisites but are not duplicated in every top app bar.
- Home shows actionable account and instance states without blocking Settings.
- Settings remains reachable before an account exists; only Launch is gated.
- Downloads and content management communicate whether an instance is required.
- Game-session UI is full-screen and outside the normal launcher shell.

## 4. Navigation and back behavior

### 4.1 Back-stack rules

- Use one predictable navigation graph with route-level state restoration.
- Opening a detail, editor, picker, or search route pushes exactly one back-stack entry.
- Selecting a list item replaces a sibling detail selection without growing the stack.
- Tabs change the active child destination without creating duplicate parent entries.
- Dialogs, menus, bottom sheets, and transient overlays take Back before the underlying route.
- A system Back action never bypasses unsaved-change protection, destructive confirmation, or an in-progress modal that must be resolved first.

### 4.2 Screen-specific behavior

| Screen or context | Back behavior |
|---|---|
| Startup | During preflight, Back does not bypass prerequisite checks. If an indispensable prerequisite fails, show the blocking recovery destination. |
| Home | Back follows normal top-level navigation; do not repeatedly create duplicate Home entries. |
| Account manager | Back returns to the prior origin. Deletion has already required confirmation. |
| Microsoft OAuth | Use an approved system/custom-tab flow. Cancel returns to the account manager without losing previously selected accounts. |
| Settings | Back returns to the previous category or the prior top-level destination according to navigation mode. |
| Version list | Back returns to its origin. Selecting a favorite category does not add a route. |
| Version detail/configuration | Back prompts if edits are unsaved. Discard and keep-editing are separate explicit actions. |
| Installation composer | Back preserves entered name and selected compatible add-ons. System process recreation restores them. |
| Content/instance manager | Back returns to the instance or content origin. Folder-picker confirmation is a separate action, not a substitute for Back. |
| Search | Back first dismisses an expanded search or filter sheet. |
| Control editor | Back requests save, discard, or cancel. Deleting a control requires confirmation. |
| Game session | Outside editing, Back maps to the expected game menu/escape action. Inside editing, it follows the editor’s unsaved-change policy. |
| Terminal error | Back exits the failed task/activity; no hidden recovery route is added. |

### 4.3 Up navigation

- Every non-top-level screen exposes a visible Up action in addition to system Back.
- Up and Back must produce the same result unless the child explicitly consumes Back for a nested interaction.
- Breadcrumbs are not required for shallow flows; screen titles and section headers provide orientation.

## 5. Action rules

### 5.1 Primary and secondary actions

- Each screen has at most one visually dominant primary action.
- Use verbs for actions and nouns for destinations.
- Destructive actions use a distinct error color, explicit object naming, and confirmation.
- Disabled actions remain nearby and explain their blocked condition; they are not removed without explanation when the condition is user-fixable.
- Long press may accelerate an action but must never be the sole route to delete, remove, select default, edit, or reset.

### 5.2 Launch action

The Launch action is enabled only when:

- a supported account is selected;
- a valid installed instance is selected;
- required runtime is available or can be selected;
- no incompatible launch task is active; and
- required content dependencies are valid.

When blocked, the action remains visible and presents the first actionable reason. It must not silently disappear.

### 5.3 Account actions

- Add official Microsoft account.
- Add offline local account.
- Select current account.
- Refresh renewable official account.
- Remove account after confirmation.
- Local accounts have no refresh action.
- OAuth uses the official system/custom-tab flow and an Eclipse-owned approved client configuration.
- Never display external-password fields for non-Microsoft providers.

### 5.4 Instance actions

- Create, install, select, configure, rename, copy, and remove instance.
- Manage instance-scoped content.
- Install an approved add-on only when a compatible version exists.
- Show incompatible add-ons as disabled with a plain-language reason.
- Do not allow duplicate instance names or invalid path/file characters without field-level validation.

### 5.5 File and content actions

- Import through Android system document creation/selection APIs into app-scoped or instance-scoped storage.
- Export/share through generated content URIs with temporary read grants.
- Copy, move, rename, and delete operate only on content shown by the current scoped instance.
- No unrestricted root, external-storage, or all-files-access route is exposed.
- Destructive operations identify the item or item count and support confirmation.

### 5.6 Settings actions

- Every setting has a visible title, current value, and supporting text where the effect is not obvious.
- Settings that require restart show the pending state and provide one restart action.
- Experimental defaults are absent from release navigation.
- Unsupported hardware or OS capabilities use an explanatory disabled row rather than silently disappearing.

## 6. Containers and layout

### 6.1 Standard containers

- Use a Material 3 top app bar for the launcher title, Downloads, Settings, and nested Up actions.
- Do not introduce a permanent bottom navigation bar; primary routes are reached through the stable top-bar actions defined in section 3.
- Use a Material 3 navigation rail for Downloads/Settings categories on medium and expanded windows; compact windows use the equivalent ordered tab row or drawer.
- Use list-detail containers for collections with a meaningful selected item.
- Use cards only for grouped content; Home management actions may use elevated/filled tonal surfaces to preserve the recognizable large-row relationship without copying legacy drawables.
- Use bottom sheets for compact-window pickers, filters, and compact action menus.
- Use full-screen routes for account OAuth, control editing, color selection, and game sessions.
- Use a snackbar only for brief, non-dismissible status such as a completed copy or save. Errors requiring resolution use a dialog, banner, or dedicated error content.
- Global progress uses a persistent surface that survives navigation, with a details sheet for multiple tasks.

### 6.2 Collection rows

- One-line row minimum height: 56dp.
- Two-line row preferred height: 64–72dp.
- Leading icon: 24dp with 32dp visual container.
- Trailing action: 48dp minimum touch target.
- Avatar: 40–56dp depending on context.
- Checkbox rows expose the entire row as the selection target.
- Selected state uses container color, selected semantics, and a checkmark; color is never the sole indicator.

### 6.3 Forms

- Labels remain visible above inputs; placeholders are examples, not labels.
- Supporting/error text is associated programmatically with its input.
- Required fields are identified in text, not only with an asterisk.
- Password and local-account fields declare appropriate autofill and input semantics.
- Numeric controls expose both slider and typed-entry paths where precision matters.
- Destructive confirmation dialogs identify the exact account, instance, file, or group.

## 7. Spacing, alignment, density, and touch targets

### 7.1 Spacing grid

Use a 4dp base grid:

- 4dp: icon-to-label micro-spacing.
- 8dp: label-to-value and compact control spacing.
- 12dp: related internal padding and narrow-screen gutters.
- 16dp: standard content padding and between related fields.
- 24dp: section separation and expanded gutters.
- 32dp: major group separation on expanded windows.

### 7.2 Alignment

- Primary page content starts at a 16dp compact gutter, 24dp medium gutter, and 32dp expanded gutter.
- List and detail content align to shared start edges within a navigation mode.
- Dialog actions align to the dialog’s end edge; destructive actions must remain visually distinct.
- Settings summaries align below titles, never at a distant right edge on compact widths.
- Avoid hard-coded pixel widths for controls, dialogs, or side panels.

### 7.3 Touch and pointer targets

- Minimum touch target: 48 by 48dp for every interactive element.
- Adjacent destructive and non-destructive actions require at least 8dp separation.
- Entire cards/rows may be clickable only when the entire row has one unambiguous action.
- Hover and keyboard focus appear for mice, keyboards, and large-window environments, but do not create ChromeOS-specific requirements.
- Custom game controls must expose an accessible non-touch alternative wherever practical.

### 7.4 Density and font scaling

- Support Android font scaling through 200% without clipping primary actions.
- At 130%–200% font scale, rows expand vertically and action groups wrap.
- Do not encode units or status exclusively in 8–10sp metadata.
- Use scalable type tokens and maintain at least 4.5:1 text contrast where possible.
- Page opacity theming must never reduce text/background contrast below accessible thresholds.

## 8. State model

Every asynchronous surface must implement explicit states:

1. **Initial** — no request started.
2. **Loading** — visible progress, accessible label, and cancellation where safe.
3. **Refreshing** — existing content remains visible where possible.
4. **Populated** — normal content and actions.
5. **Empty** — explains why content is absent and offers the next action.
6. **Filtered-empty** — identifies active filters and offers reset.
7. **Offline** — distinguishes lack of connectivity from service failure.
8. **Permission-required** — explains impact and offers settings/request action.
9. **Validation-error** — field-level message and focus/error announcement.
10. **Conflict/version-conflict** — explains stale data and offers refresh/retry.
11. **Failure** — concise cause, retry where appropriate, and diagnostics/share option where useful.
12. **Disabled/blocked** — reason remains visible and actionable.
13. **In-progress** — destructive or long-running operation blocks conflicting actions.
14. **Success** — brief confirmation where no navigation occurs.
15. **Cancelled** — returns to the prior stable state without an error unless cancellation was unexpected.

### 8.1 Empty-state requirements

Explicit empty states are required for:

- accounts;
- installed instances;
- selected instance content;
- favorites;
- approved remote catalog results;
- local imports;
- custom layouts when that feature is approved;
- search with zero results.

An empty account list offers official Microsoft or offline local account creation. An empty instance list offers installation or content-assisted creation. An empty remote result offers reset filters, retry, and offline status where relevant.

### 8.2 Disabled-state requirements

Disabled controls explain the condition through supporting text or an accessible description. Examples:

- Launch disabled because no account is selected.
- Refresh disabled for local accounts.
- Add-on disabled because it conflicts with another selected add-on.
- Import disabled while a conflicting write is active.
- Custom path disabled when instance isolation makes it unavailable.
- Gyro or Vulkan option disabled when unsupported, not silently absent.

### 8.3 Progress requirements

- Global progress tracks game download, runtime setup, content installation, account refresh, version refresh, and mod checking.
- Each progress item has a label, state, percentage where known, and cancellation policy.
- Multiple tasks open a task-details sheet.
- A progress state announces start and completion; it does not spam repeated announcements.
- Destructive file progress supports cancel before commit semantics.
- Rotation and process recreation restore progress ownership and reconnect to the underlying task where possible.

## 9. Adaptive window behavior

### 9.1 Compact phone

- Width below 600dp.
- Portrait and landscape are supported for launcher management.
- Preserve the top app bar and global progress dock.
- Home uses the vertical order in section 3.4 rather than a narrow two-pane split.
- Downloads and Settings use ordered horizontal tabs or a navigation drawer instead of a permanently vertical rail.
- Pickers use sheets and editors use full-screen routes.
- Game session requests landscape while foregrounded and restores launcher orientation on exit.
- Primary content must fit short landscape heights without placing actions under the IME.

### 9.2 Medium tablet or large phone

- Width 600–839dp.
- Home uses the two-pane relationship when each pane remains at least 280dp wide; otherwise it uses the compact vertical order.
- Downloads and Settings use a Material 3 navigation rail.
- List-detail is used only when both panes remain usable; the selected category and content order do not change.

### 9.3 Expanded tablet and large window

- Width 840dp or greater.
- Home retains the recognizable management/launch split, constrained to approximately 65–68% and 30–35% rather than growing without bounds.
- Downloads and Settings retain their navigation rail and may use list-detail.
- Maximum readable text width is approximately 720dp; wider containers use space for context, not unbounded line length.

### 9.4 Foldables

- Use WindowManager posture and size-class APIs.
- Tabletop posture separates primary actions/list content from supporting detail.
- Book posture uses the hinge as a spatial boundary when it does not create an unusable narrow pane.
- Fold transitions do not lose route, selection, editor, upload, permission, or progress state.
- Dual-pane and single-pane navigation must be behaviorally equivalent.

### 9.5 Rotation

- Management screens support portrait and landscape.
- Configuration changes update metrics and control positions without resetting state.
- Dialogs and sheets re-anchor within the current window.
- In-game rendering and touch controls use landscape and recalculate dimensions.
- IME appearance uses resize/pan behavior appropriate to the selected editor or form.

## 10. Accessibility and screen-reader order

### 10.1 Global order

For a standard management screen:

1. Navigation bar or rail.
2. Top app bar and Up action.
3. Screen title/heading.
4. Primary status or prerequisite state.
5. Main list/grid or form.
6. Filters/search when they govern the main content.
7. Global or screen-level progress.
8. Persistent bottom action where applicable.
9. Navigation-bar/tab indicators and newly announced overlays.

### 10.2 Home order

1. Current account and account-change action.
2. Current instance and instance-change action.
3. Primary launch action and blocked reason.
4. Quick instance actions.
5. Content shortcuts.
6. Support and diagnostics shortcuts.

### 10.3 Lists

- Announce collection name and item count when the page opens.
- Each row announces title, supporting status, selected state, disabled reason, and available actions.
- Do not announce decorative icons.
- Selection changes use selected semantics.
- Refresh completion is announced once without moving focus.
- Search-result count is a polite live update.

### 10.4 Forms and dialogs

- Move focus to an invalid field on submission.
- Read title, supporting text, input label, error, and actions in logical order.
- Destructive confirmation places the safe/default action first visually and in focus order.
- Bottom sheets have a title, close action, and initial focus that does not cause an unexpected keyboard jump.
- Progress dialogs announce task and cancelability; expandable details are reachable by action, not hidden in a decorative container.

### 10.5 Game and custom controls

- Expose standard actions through an accessible control/action panel.
- Do not label every physical touch point as a button if it represents a game input; instead expose named actions in settings or an accessible editor.
- Custom controls require visible focus, TalkBack labels, state, and adjustable alternatives.
- Drag/resize actions require non-drag increment/decrement controls.
- In-game overlays must not capture accessibility focus continuously; the monitor and logger have explicit open/close actions.

## 11. Offline behavior

### 11.1 Offline-capable routes

These remain available offline:

- startup after prerequisites are satisfied;
- account list and local accounts;
- instance list and selection;
- local configuration;
- scoped local content management;
- settings except network-dependent update checks;
- logs, crash data, and support export;
- game launch when all required files and runtime are present.

### 11.2 Network-required routes

- Official Microsoft OAuth.
- Renewable account refresh.
- Remote catalog browsing and dependency retrieval.
- Runtime download.
- Instance/add-on download.
- App update metadata/download.
- Approved website/support links.

When offline:

- retain the current route and entered data;
- show a persistent offline status near the network-dependent action;
- disable actions that cannot proceed while explaining why;
- keep cached metadata clearly identified as cached;
- offer retry when connectivity returns;
- never substitute “no results” for offline.

## 12. Permissions and storage

### 12.1 Permission policy

- Request notification permission only when a user-visible feature requires it and immediately before use.
- If denied permanently, show an explanatory settings action rather than repeatedly prompting.
- Do not request microphone permission unless an implemented, user-initiated feature requires it and has its own privacy explanation.
- Do not request broad files or all-files access.
- Use app-scoped storage, instance-scoped storage, and system document APIs.
- External-content URIs receive minimum grants and temporary permissions.

### 12.2 Permission rationale

Every rationale states:

1. the user action that needs access;
2. the data protected or feature enabled;
3. whether denial blocks the action or only reduces convenience;
4. the next action.

### 12.3 Permission denial

- Preserve current work.
- Mark the specific action blocked.
- Offer retry when the system permits it.
- Offer app settings only when the permission is permanently denied.
- Do not route to broad storage settings.

## 13. Validation

Validation occurs before destructive or persistent operations:

- account names and local usernames;
- instance names;
- path segments and illegal characters;
- numeric ranges;
- required fields;
- duplicate instance names;
- add-on compatibility;
- permission requirements;
- custom control dimensions and numeric values;
- content source/dependency availability.

Rules:

- Field errors appear adjacent to the field and in accessibility announcements.
- Form submission preserves input.
- Disabled submit is permitted only when the reason is always visible.
- Server/API validation is supplementary and never replaces local validation.
- Destructive validation must identify the object, not only say that an action failed.

## 14. Destructive behavior

The following require confirmation:

- remove account;
- remove instance;
- delete local files/content;
- discard unsaved instance configuration;
- discard unsaved control-layout edits;
- remove selected custom control;
- reset custom icon/cursor/background;
- force stop a running Java/game process;
- clear caches when user data is affected.

Confirmation requirements:

- explicit object name or count;
- consequence summary;
- safe cancel/default action;
- destructive action styled as destructive;
- no accidental Enter-to-confirm behavior;
- irreversible actions disabled while a preflight check is unresolved.

## 15. Process and state restoration

### 15.1 Required saved state

Persist or restore through `savedInstanceState` and durable navigation state:

- selected top-level destination;
- nested route and selected item ID;
- tab/category/filter values;
- search query and results-only/case options;
- form text, validation state, and unsaved-change flag;
- installation-composer name and selected add-on versions;
- selected instance and profile path;
- expanded/collapsed sections;
- dialog/sheet initiating route and selection;
- pending document URI and import destination;
- task identifiers and observable progress ownership;
- OAuth return state that is safe and approved for restoration;
- game/editor route and control-layout edit snapshot when restoration is safe.

### 15.2 Process death

After process death:

- never restore a stale destructive confirmation;
- revalidate files, runtime, account, and instance state before launch;
- reconnect to active work instead of restarting duplicate downloads or imports;
- restore OAuth only when the approved flow supports it; otherwise return to account management with a recoverable message;
- preserve local-account selection;
- restore editor work only after successful file validation;
- restore permission-required states and request access again only through user action.

### 15.3 Configuration changes

- Preserve route, selection, text, progress ownership, editor state, and accessibility focus target where the view is recreated.
- Recalculate display-dependent previews and custom-control dimensions.
- Do not duplicate one-shot dialogs or restart operations on every rotation.

### 15.4 Back-up policy

- Back up non-sensitive account metadata only if the selected authentication flow permits it.
- Never back up passwords, refresh tokens, access tokens, or private OAuth state.
- Local account names and launcher preferences may be backed up according to the approved data policy.
- Logs and crash reports are excluded unless the user explicitly exports them.

## 16. Visual, theme, and branding rules

- Use Material 3 components and accessibility roles rather than recreating source-specific controls.
- Support system, light, dark, and supported dynamic-color modes.
- Dynamic color may affect standard surfaces but not semantic error/warning colors.
- Preserve the observable layout hierarchy and spatial relationships documented in this specification while using original Eclipse colors, typography treatment, icons, illustrations, motion, and component styling.
- Do not copy the reference wordmark, icon artwork, screenshots, strings, or distinctive decorative assets.
- The app icon, final logo, and owned endpoints require provenance and legal approval.
- Do not ship remote news, sponsor, advertising, analytics, or telemetry UI.

## 17. Verification framework

Each parity-matrix row must be verified through:

- route/unit tests for action and state transitions;
- Compose or View UI tests for semantics, labels, enabled/selected state, and focus;
- instrumentation tests for navigation, Back, rotation, process recreation, permissions, document URIs, and destructive flows;
- adaptive screenshot or layout tests at compact, medium, and expanded widths;
- foldable posture tests;
- TalkBack and switch-access review;
- font-scale tests at 100%, 130%, and 200%;
- offline and service-failure tests;
- security/privacy checks for OAuth, URI grants, account storage, and executable content;
- Apache-2.0 license and attribution review.

A row is not considered implemented until its required behavior and verification checks exist and pass.

## 18. Phase 2 implementation record

Phase 2 establishes the first independently authored UI slice without claiming that the complete launcher is finished:

- `:core:designsystem` owns the centralized Material 3 color, typography, shape, dimension, theme, icon, and reusable component tokens.
- `:feature:home` owns an honest Home state model and adaptive Home presentation. The wide layout uses a 6.5/3 management-to-launch relationship; compact windows use the normative account → instance → Launch → management order.
- The current state is a real prerequisite/empty state: no account and no installed instance are shown, Launch is disabled with the first actionable reason, and unavailable shell actions remain disabled with explanatory text. No fake success, progress, or navigation callback is used.
- Previews cover wide light, wide dark, compact, dynamic color, and enlarged text configurations. Compose instrumentation checks semantics, disabled actions, and a screenshot theme contract.
- CI runs the unit/lint/debug verification and connected Compose tests on an Actions emulator. The test workflow does not publish screenshots or APKs as workflow artifacts.

The Home slice is an implementation milestone, not a final parity disposition. F01, S08, S09, A03, and all navigation/repository rows remain subject to their complete behavior and verification requirements.

## 19. Phase 3 implementation record

Phase 3 establishes the stable top-level launcher shell and real destination navigation:

- `:app` owns one Navigation Compose graph with Home as the start destination and Downloads/Settings as sibling top-level destinations.
- Every top-level destination keeps the Eclipse Launcher title leading and Downloads/Settings actions trailing; no permanent bottom navigation is introduced.
- Top-level navigation uses single-top restoration and returns to the root predictably. The connected Actions test covers Downloads, Settings, and Back-to-Home behavior.
- `:feature:downloads` and `:feature:settings` provide saveable local category selection, adaptive tabs/rails, and explicit empty/unavailable content. They do not claim that version sources, settings persistence, rendering, or runtime repositories are connected.
- Category selection and navigation are real state transitions; future repository actions must be injected behind interfaces rather than simulated in these screens.

This milestone does not complete A03, F04, F28, F35, or the remaining parity rows; those still require full behavior, restoration, device, and accessibility verification.
