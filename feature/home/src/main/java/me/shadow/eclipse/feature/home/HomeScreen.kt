package me.shadow.eclipse.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.shadow.eclipse.core.designsystem.EclipseActionRow
import me.shadow.eclipse.core.designsystem.EclipseDimensions
import me.shadow.eclipse.core.designsystem.EclipseIcons
import me.shadow.eclipse.core.designsystem.EclipseLauncherTopBar
import me.shadow.eclipse.core.designsystem.EclipseSectionLabel
import me.shadow.eclipse.core.designsystem.EclipseStatusPanel
import me.shadow.eclipse.core.designsystem.EclipseTheme
import me.shadow.eclipse.core.designsystem.ThemeMode

@Composable
fun HomeScreen(
    title: String,
    state: HomeUiState,
    modifier: Modifier = Modifier,
    onDownloads: (() -> Unit)? = null,
    onSettings: (() -> Unit)? = null,
    onAccounts: (() -> Unit)? = null,
    onAction: ((HomeAction) -> Unit)? = null,
    onLaunch: (() -> Unit)? = null,
    onManageInstance: (() -> Unit)? = null,
) {
    Scaffold(
        topBar = {
            EclipseLauncherTopBar(
                title = title,
                downloadsContentDescription = stringResource(R.string.home_action_downloads),
                settingsContentDescription = stringResource(R.string.home_action_settings),
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
                HomeWideLayout(
                    state = state,
                    onAction = onAction,
                    onLaunch = onLaunch,
                    onManageInstance = onManageInstance,
                    onAccounts = onAccounts,
                )
            } else {
                HomeCompactLayout(
                    state = state,
                    onAction = onAction,
                    onLaunch = onLaunch,
                    onManageInstance = onManageInstance,
                    onAccounts = onAccounts,
                )
            }
        }
    }
}

@Composable
private fun HomeWideLayout(
    state: HomeUiState,
    onAction: ((HomeAction) -> Unit)?,
    onLaunch: (() -> Unit)?,
    onManageInstance: (() -> Unit)?,
    onAccounts: (() -> Unit)?,
) {
    Row(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .weight(EclipseDimensions.ManagementPaneWeight)
                .fillMaxHeight(),
            color = MaterialTheme.colorScheme.surface,
        ) {
            ManagementActions(
                state = state,
                onAction = onAction,
            )
        }
        VerticalDivider(
            thickness = EclipseDimensions.DividerThickness,
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Surface(
            modifier = Modifier
                .weight(EclipseDimensions.LaunchPaneWeight)
                .fillMaxHeight(),
            color = MaterialTheme.colorScheme.surfaceContainerLow,
        ) {
            LaunchPane(
                state = state,
                onLaunch = onLaunch,
                onManageInstance = onManageInstance,
                onAccounts = onAccounts,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun HomeCompactLayout(
    state: HomeUiState,
    onAction: ((HomeAction) -> Unit)?,
    onLaunch: (() -> Unit)?,
    onManageInstance: (() -> Unit)?,
    onAccounts: (() -> Unit)?,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(EclipseDimensions.SpaceLarge),
        verticalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceMedium),
    ) {
        item(key = "account") {
            AccountStatus(
                state = state,
                onAccounts = onAccounts,
            )
        }
        item(key = "instance") {
            InstanceStatus(
                state = state,
                onManageInstance = onManageInstance,
            )
        }
        item(key = "launch") {
            LaunchControls(
                state = state,
                onLaunch = onLaunch,
            )
        }
        item(key = "tools-label") {
            EclipseSectionLabel(text = stringResource(R.string.home_tools_title))
        }
        items(
            items = HomeAction.entries,
            key = HomeAction::name,
        ) { action ->
            HomeActionRow(
                action = action,
                enabled = onAction != null && action in state.availableActions,
                onClick = { onAction?.invoke(action) },
            )
        }
    }
}

@Composable
private fun ManagementActions(
    state: HomeUiState,
    onAction: ((HomeAction) -> Unit)?,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(EclipseDimensions.SpaceMedium),
        verticalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceMedium),
    ) {
        items(
            items = HomeAction.entries,
            key = HomeAction::name,
        ) { action ->
            HomeActionRow(
                action = action,
                enabled = onAction != null && action in state.availableActions,
                onClick = { onAction?.invoke(action) },
            )
        }
    }
}

@Composable
private fun LaunchPane(
    state: HomeUiState,
    onLaunch: (() -> Unit)?,
    onManageInstance: (() -> Unit)?,
    onAccounts: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(EclipseDimensions.SpaceLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        AccountStatus(
            state = state,
            onAccounts = onAccounts,
            modifier = Modifier.widthIn(max = EclipseDimensions.ContentMaxWidth),
        )
        Spacer(modifier = Modifier.weight(1f))
        InstanceStatus(
            state = state,
            onManageInstance = onManageInstance,
            modifier = Modifier.widthIn(max = EclipseDimensions.ContentMaxWidth),
        )
        LaunchControls(
            state = state,
            onLaunch = onLaunch,
            modifier = Modifier
                .widthIn(max = EclipseDimensions.ContentMaxWidth)
                .padding(top = EclipseDimensions.SpaceSmall),
        )
    }
}

@Composable
private fun AccountStatus(
    state: HomeUiState,
    onAccounts: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val accountName = state.accountName
    val accountType = state.accountType
    EclipseStatusPanel(
        icon = EclipseIcons.Account,
        title = accountName ?: stringResource(R.string.home_no_account_title),
        supportingText = if (accountName != null && accountType != null) {
            stringResource(accountType.labelResource())
        } else {
            stringResource(R.string.home_no_account_supporting)
        },
        onClick = onAccounts,
        modifier = modifier,
    )
}

@Composable
private fun InstanceStatus(
    state: HomeUiState,
    onManageInstance: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val instanceName = state.instanceName
    val versionName = state.versionName
    EclipseStatusPanel(
        icon = EclipseIcons.Download,
        title = instanceName ?: stringResource(R.string.home_no_instance_title),
        supportingText = when {
            instanceName != null && versionName != null -> versionName
            state.activeTaskCount > 0 -> pluralStringResource(
                R.plurals.home_active_tasks,
                state.activeTaskCount,
                state.activeTaskCount,
            )
            else -> stringResource(R.string.home_no_instance_supporting)
        },
        trailingContent = {
            IconButton(
                onClick = { onManageInstance?.invoke() },
                enabled = onManageInstance != null,
            ) {
                Icon(
                    imageVector = EclipseIcons.Settings,
                    contentDescription = stringResource(R.string.home_manage_instance),
                )
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun LaunchControls(
    state: HomeUiState,
    onLaunch: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val launchReady = state.launchEnabled && onLaunch != null
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceSmall),
    ) {
        Button(
            onClick = { onLaunch?.invoke() },
            enabled = launchReady,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = EclipseDimensions.MinimumTouchTarget),
        ) {
            Text(
                text = stringResource(R.string.home_launch),
                style = MaterialTheme.typography.labelLarge,
            )
        }
        if (!launchReady) {
            Text(
                text = stringResource(
                    if (state.launchEnabled) {
                        R.string.home_launch_blocked_generic
                    } else {
                        state.launchBlockedMessage()
                    },
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun HomeActionRow(
    action: HomeAction,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    EclipseActionRow(
        icon = action.icon(),
        title = stringResource(action.titleResource()),
        supportingText = stringResource(action.supportingTextResource()),
        enabled = enabled,
        onClick = onClick,
    )
}

private fun HomeAction.icon() = when (this) {
    HomeAction.ABOUT -> EclipseIcons.Info
    HomeAction.CONTROL_LAYOUTS -> EclipseIcons.Controls
    HomeAction.INSTANCE_DIRECTORY -> EclipseIcons.Folder
    HomeAction.SHARE_LOGS -> EclipseIcons.Share
}

private fun HomeAction.titleResource() = when (this) {
    HomeAction.ABOUT -> R.string.home_action_about
    HomeAction.CONTROL_LAYOUTS -> R.string.home_action_controls
    HomeAction.INSTANCE_DIRECTORY -> R.string.home_action_directory
    HomeAction.SHARE_LOGS -> R.string.home_action_logs
}

private fun HomeAction.supportingTextResource() = when (this) {
    HomeAction.ABOUT -> R.string.home_action_about_supporting
    HomeAction.CONTROL_LAYOUTS -> R.string.home_action_controls_supporting
    HomeAction.INSTANCE_DIRECTORY -> R.string.home_action_directory_supporting
    HomeAction.SHARE_LOGS -> R.string.home_action_logs_supporting
}

private fun AccountType.labelResource() = when (this) {
    AccountType.MICROSOFT -> R.string.home_account_microsoft
    AccountType.LOCAL -> R.string.home_account_local
}

private fun HomeUiState.launchBlockedMessage(): Int = when {
    accountName == null -> R.string.home_launch_blocked_account
    instanceName == null || versionName == null -> R.string.home_launch_blocked_instance
    activeTaskCount > 0 -> R.string.home_launch_blocked_task
    else -> R.string.home_launch_blocked_generic
}

@Preview(name = "Home wide light", widthDp = 1280, heightDp = 800, showBackground = true)
@Composable
private fun HomeWideLightPreview() {
    EclipseTheme(themeMode = ThemeMode.LIGHT, dynamicColor = false) {
        HomeScreen(title = "Eclipse Launcher", state = HomeUiState())
    }
}

@Preview(name = "Home wide dark", widthDp = 1280, heightDp = 800, showBackground = true)
@Composable
private fun HomeWideDarkPreview() {
    EclipseTheme(themeMode = ThemeMode.DARK, dynamicColor = false) {
        HomeScreen(title = "Eclipse Launcher", state = HomeUiState())
    }
}

@Preview(name = "Home compact", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun HomeCompactPreview() {
    EclipseTheme(themeMode = ThemeMode.LIGHT, dynamicColor = false) {
        HomeScreen(title = "Eclipse Launcher", state = HomeUiState())
    }
}

@Preview(
    name = "Home dynamic large font",
    widthDp = 600,
    heightDp = 960,
    fontScale = 2f,
    showBackground = true,
)
@Composable
private fun HomeDynamicLargeFontPreview() {
    EclipseTheme(dynamicColor = true) {
        HomeScreen(title = "Eclipse Launcher", state = HomeUiState())
    }
}
