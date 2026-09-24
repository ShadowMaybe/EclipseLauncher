package me.shadow.eclipse.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.shadow.eclipse.core.designsystem.EclipseCategory
import me.shadow.eclipse.core.designsystem.EclipseCategoryRail
import me.shadow.eclipse.core.designsystem.EclipseCategoryTabs
import me.shadow.eclipse.core.designsystem.EclipseDimensions
import me.shadow.eclipse.core.designsystem.EclipseIcons
import me.shadow.eclipse.core.designsystem.EclipseLauncherTopBar
import me.shadow.eclipse.core.designsystem.EclipseStatusPanel
import me.shadow.eclipse.core.designsystem.EclipseTheme
import me.shadow.eclipse.core.designsystem.ThemeMode

@Composable
fun SettingsRoute(
    title: String,
    onDownloads: () -> Unit,
    onSettings: () -> Unit,
) {
    val selectedCategoryId = rememberSaveable { mutableStateOf(SettingsCategory.RENDERING.name) }
    val selectedCategory = SettingsCategory.entries.firstOrNull {
        it.name == selectedCategoryId.value
    } ?: SettingsCategory.RENDERING

    SettingsScreen(
        title = title,
        state = SettingsUiState(selectedCategory = selectedCategory),
        onDownloads = onDownloads,
        onSettings = onSettings,
        onCategorySelected = { selectedCategoryId.value = it.name },
    )
}

@Composable
fun SettingsScreen(
    title: String,
    state: SettingsUiState,
    onDownloads: (() -> Unit)?,
    onSettings: (() -> Unit)?,
    onCategorySelected: (SettingsCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    val categories = settingsCategories()
    Scaffold(
        topBar = {
            EclipseLauncherTopBar(
                title = title,
                downloadsContentDescription = stringResource(R.string.settings_action_downloads),
                settingsContentDescription = stringResource(R.string.settings_action_settings),
                onDownloads = onDownloads,
                onSettings = onSettings,
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (maxWidth >= EclipseDimensions.ExpandedWidth) {
                Row(modifier = Modifier.fillMaxSize()) {
                    EclipseCategoryRail(
                        categories = categories,
                        selectedCategoryId = state.selectedCategory.name,
                        onCategorySelected = { category ->
                            onCategorySelected(category.toSettingsCategory())
                        },
                    )
                    VerticalDivider(
                        thickness = EclipseDimensions.DividerThickness,
                        color = MaterialTheme.colorScheme.outlineVariant,
                    )
                    SettingsDestinationContent(
                        state = state,
                        modifier = Modifier.weight(1f),
                    )
                }
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    EclipseCategoryTabs(
                        categories = categories,
                        selectedCategoryId = state.selectedCategory.name,
                        onCategorySelected = { category ->
                            onCategorySelected(category.toSettingsCategory())
                        },
                        modifier = Modifier.padding(horizontal = EclipseDimensions.SpaceSmall),
                    )
                    SettingsDestinationContent(
                        state = state,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsDestinationContent(
    state: SettingsUiState,
    modifier: Modifier = Modifier,
) {
    val category = state.selectedCategory
    val title = when (category) {
        SettingsCategory.RENDERING -> stringResource(R.string.settings_rendering_title)
        SettingsCategory.CONTROLS -> stringResource(R.string.settings_controls_title)
        SettingsCategory.GAME_RUNTIME -> stringResource(R.string.settings_game_runtime_title)
        SettingsCategory.LAUNCHER -> stringResource(R.string.settings_launcher_title)
        SettingsCategory.ACCESSIBILITY_STORAGE -> stringResource(R.string.settings_accessibility_storage_title)
    }
    val supportingText = when (category) {
        SettingsCategory.RENDERING -> stringResource(R.string.settings_rendering_supporting)
        SettingsCategory.CONTROLS -> stringResource(R.string.settings_controls_supporting)
        SettingsCategory.GAME_RUNTIME -> stringResource(R.string.settings_game_runtime_supporting)
        SettingsCategory.LAUNCHER -> stringResource(R.string.settings_launcher_supporting)
        SettingsCategory.ACCESSIBILITY_STORAGE -> stringResource(R.string.settings_accessibility_storage_supporting)
    }

    Column(
        modifier = modifier.padding(EclipseDimensions.SpaceLarge),
        verticalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceMedium),
    ) {
        Text(
            text = stringResource(category.labelResource()),
            style = MaterialTheme.typography.headlineSmall,
        )
        EclipseStatusPanel(
            icon = category.icon(),
            title = title,
            supportingText = supportingText,
        )
    }
}

@Composable
private fun settingsCategories(): List<EclipseCategory> = listOf(
    EclipseCategory(
        id = SettingsCategory.RENDERING.name,
        label = stringResource(R.string.settings_category_rendering),
        icon = EclipseIcons.Settings,
    ),
    EclipseCategory(
        id = SettingsCategory.CONTROLS.name,
        label = stringResource(R.string.settings_category_controls),
        icon = EclipseIcons.Controls,
    ),
    EclipseCategory(
        id = SettingsCategory.GAME_RUNTIME.name,
        label = stringResource(R.string.settings_category_game_runtime),
        icon = EclipseIcons.Download,
    ),
    EclipseCategory(
        id = SettingsCategory.LAUNCHER.name,
        label = stringResource(R.string.settings_category_launcher),
        icon = EclipseIcons.Info,
    ),
    EclipseCategory(
        id = SettingsCategory.ACCESSIBILITY_STORAGE.name,
        label = stringResource(R.string.settings_category_accessibility_storage),
        icon = EclipseIcons.Account,
    ),
)

private fun SettingsCategory.labelResource() = when (this) {
    SettingsCategory.RENDERING -> R.string.settings_category_rendering
    SettingsCategory.CONTROLS -> R.string.settings_category_controls
    SettingsCategory.GAME_RUNTIME -> R.string.settings_category_game_runtime
    SettingsCategory.LAUNCHER -> R.string.settings_category_launcher
    SettingsCategory.ACCESSIBILITY_STORAGE -> R.string.settings_category_accessibility_storage
}

private fun SettingsCategory.icon() = when (this) {
    SettingsCategory.RENDERING -> EclipseIcons.Settings
    SettingsCategory.CONTROLS -> EclipseIcons.Controls
    SettingsCategory.GAME_RUNTIME -> EclipseIcons.Download
    SettingsCategory.LAUNCHER -> EclipseIcons.Info
    SettingsCategory.ACCESSIBILITY_STORAGE -> EclipseIcons.Account
}

private fun EclipseCategory.toSettingsCategory(): SettingsCategory =
    SettingsCategory.entries.first { it.name == id }

@Preview(name = "Settings wide light", widthDp = 1280, heightDp = 800, showBackground = true)
@Composable
private fun SettingsWideLightPreview() {
    EclipseTheme(themeMode = ThemeMode.LIGHT, dynamicColor = false) {
        SettingsScreen(
            title = "Eclipse Launcher",
            state = SettingsUiState(),
            onDownloads = null,
            onSettings = null,
            onCategorySelected = {},
        )
    }
}

@Preview(name = "Settings compact dark", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun SettingsCompactDarkPreview() {
    EclipseTheme(themeMode = ThemeMode.DARK, dynamicColor = false) {
        SettingsScreen(
            title = "Eclipse Launcher",
            state = SettingsUiState(selectedCategory = SettingsCategory.CONTROLS),
            onDownloads = null,
            onSettings = null,
            onCategorySelected = {},
        )
    }
}
