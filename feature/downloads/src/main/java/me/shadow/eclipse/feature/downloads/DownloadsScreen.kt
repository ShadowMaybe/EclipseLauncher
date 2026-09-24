package me.shadow.eclipse.feature.downloads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.pluralStringResource
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
fun DownloadsRoute(
    title: String,
    onDownloads: () -> Unit,
    onSettings: () -> Unit,
) {
    val selectedCategoryId = rememberSaveable { mutableStateOf(DownloadCategory.VERSIONS.name) }
    val selectedCategory = DownloadCategory.entries.firstOrNull {
        it.name == selectedCategoryId.value
    } ?: DownloadCategory.VERSIONS

    DownloadsScreen(
        title = title,
        state = DownloadsUiState(selectedCategory = selectedCategory),
        onDownloads = onDownloads,
        onSettings = onSettings,
        onCategorySelected = { selectedCategoryId.value = it.name },
    )
}

@Composable
fun DownloadsScreen(
    title: String,
    state: DownloadsUiState,
    onDownloads: (() -> Unit)?,
    onSettings: (() -> Unit)?,
    onCategorySelected: (DownloadCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    val categories = downloadCategories()
    Scaffold(
        topBar = {
            EclipseLauncherTopBar(
                title = title,
                downloadsContentDescription = stringResource(R.string.downloads_action_downloads),
                settingsContentDescription = stringResource(R.string.downloads_action_settings),
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
                            onCategorySelected(category.toDownloadCategory())
                        },
                    )
                    VerticalDivider(
                        thickness = EclipseDimensions.DividerThickness,
                        color = MaterialTheme.colorScheme.outlineVariant,
                    )
                    DownloadDestinationContent(
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
                            onCategorySelected(category.toDownloadCategory())
                        },
                        modifier = Modifier.padding(horizontal = EclipseDimensions.SpaceSmall),
                    )
                    DownloadDestinationContent(
                        state = state,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun DownloadDestinationContent(
    state: DownloadsUiState,
    modifier: Modifier = Modifier,
) {
    val category = state.selectedCategory
    val title = when (category) {
        DownloadCategory.VERSIONS -> stringResource(R.string.downloads_versions_title)
        DownloadCategory.CONTENT -> stringResource(R.string.downloads_content_title)
        DownloadCategory.TASKS -> if (state.activeTaskCount > 0) {
            pluralStringResource(
                R.plurals.downloads_active_tasks,
                state.activeTaskCount,
                state.activeTaskCount,
            )
        } else {
            stringResource(R.string.downloads_tasks_title)
        }
    }
    val supportingText = when (category) {
        DownloadCategory.VERSIONS -> stringResource(R.string.downloads_versions_supporting)
        DownloadCategory.CONTENT -> stringResource(R.string.downloads_content_supporting)
        DownloadCategory.TASKS -> stringResource(R.string.downloads_tasks_supporting)
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
private fun downloadCategories(): List<EclipseCategory> = listOf(
    EclipseCategory(
        id = DownloadCategory.VERSIONS.name,
        label = stringResource(R.string.downloads_category_versions),
        icon = EclipseIcons.Download,
    ),
    EclipseCategory(
        id = DownloadCategory.CONTENT.name,
        label = stringResource(R.string.downloads_category_content),
        icon = EclipseIcons.Folder,
    ),
    EclipseCategory(
        id = DownloadCategory.TASKS.name,
        label = stringResource(R.string.downloads_category_tasks),
        icon = EclipseIcons.Share,
    ),
)

private fun DownloadCategory.labelResource() = when (this) {
    DownloadCategory.VERSIONS -> R.string.downloads_category_versions
    DownloadCategory.CONTENT -> R.string.downloads_category_content
    DownloadCategory.TASKS -> R.string.downloads_category_tasks
}

private fun DownloadCategory.icon() = when (this) {
    DownloadCategory.VERSIONS -> EclipseIcons.Download
    DownloadCategory.CONTENT -> EclipseIcons.Folder
    DownloadCategory.TASKS -> EclipseIcons.Share
}

private fun EclipseCategory.toDownloadCategory(): DownloadCategory =
    DownloadCategory.entries.first { it.name == id }

@Preview(name = "Downloads wide light", widthDp = 1280, heightDp = 800, showBackground = true)
@Composable
private fun DownloadsWideLightPreview() {
    EclipseTheme(themeMode = ThemeMode.LIGHT, dynamicColor = false) {
        DownloadsScreen(
            title = "Eclipse Launcher",
            state = DownloadsUiState(),
            onDownloads = null,
            onSettings = null,
            onCategorySelected = {},
        )
    }
}

@Preview(name = "Downloads compact dark", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun DownloadsCompactDarkPreview() {
    EclipseTheme(themeMode = ThemeMode.DARK, dynamicColor = false) {
        DownloadsScreen(
            title = "Eclipse Launcher",
            state = DownloadsUiState(selectedCategory = DownloadCategory.CONTENT),
            onDownloads = null,
            onSettings = null,
            onCategorySelected = {},
        )
    }
}
