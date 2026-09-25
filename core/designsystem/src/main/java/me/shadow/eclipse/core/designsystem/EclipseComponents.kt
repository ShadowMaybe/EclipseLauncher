package me.shadow.eclipse.core.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EclipseLauncherTopBar(
    title: String,
    downloadsContentDescription: String,
    settingsContentDescription: String,
    onDownloads: (() -> Unit)?,
    onSettings: (() -> Unit)?,
    onBack: (() -> Unit)? = null,
    backContentDescription: String? = null,
    modifier: Modifier = Modifier,
) {
    val backAction = onBack
    val backDescription = backContentDescription
    require(backAction == null || backDescription != null) {
        "A nested top bar action requires a content description"
    }

    TopAppBar(
        navigationIcon = {
            if (backAction != null) {
                IconButton(onClick = backAction) {
                    Icon(
                        imageVector = EclipseIcons.Back,
                        contentDescription = backDescription,
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        actions = {
            IconButton(
                onClick = { onDownloads?.invoke() },
                enabled = onDownloads != null,
            ) {
                Icon(
                    imageVector = EclipseIcons.Download,
                    contentDescription = downloadsContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
            IconButton(
                onClick = { onSettings?.invoke() },
                enabled = onSettings != null,
            ) {
                Icon(
                    imageVector = EclipseIcons.Settings,
                    contentDescription = settingsContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = modifier,
    )
}

@Composable
fun EclipseActionRow(
    icon: ImageVector,
    title: String,
    supportingText: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = EclipseDimensions.MinimumActionRowHeight),
    ) {
        Row(
            modifier = Modifier.padding(EclipseDimensions.SpaceLarge),
            horizontalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceMedium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(EclipseDimensions.ActionIconSize),
                tint = if (enabled) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceExtraSmall),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = supportingText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
fun EclipseStatusPanel(
    icon: ImageVector,
    title: String,
    supportingText: String,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    val clickableModifier = if (onClick != null) {
        Modifier
            .clickable(role = Role.Button, onClick = onClick)
            .semantics(mergeDescendants = true) {}
    } else {
        Modifier
    }
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .then(clickableModifier),
        color = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier.padding(EclipseDimensions.SpaceLarge),
            horizontalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceMedium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(EclipseDimensions.StatusIconSize),
                tint = MaterialTheme.colorScheme.primary,
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceExtraSmall),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = supportingText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            trailingContent?.invoke()
        }
    }
}

@Composable
fun EclipseSectionLabel(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier.padding(vertical = EclipseDimensions.SpaceSmall),
    )
}
