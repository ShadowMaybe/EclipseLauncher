package me.shadow.eclipse.feature.accounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.shadow.eclipse.core.accounts.Account
import me.shadow.eclipse.core.accounts.AccountRepository
import me.shadow.eclipse.core.accounts.AccountType
import me.shadow.eclipse.core.designsystem.EclipseDimensions
import me.shadow.eclipse.core.designsystem.EclipseIcons
import me.shadow.eclipse.core.designsystem.EclipseLauncherTopBar
import me.shadow.eclipse.core.designsystem.EclipseSectionLabel
import me.shadow.eclipse.core.designsystem.EclipseStatusPanel
import me.shadow.eclipse.core.designsystem.EclipseTheme
import me.shadow.eclipse.core.designsystem.ThemeMode

@Composable
fun AccountsRoute(
    repository: AccountRepository,
    onBack: () -> Unit,
    onDownloads: () -> Unit,
    onSettings: () -> Unit,
) {
    val factory = remember(repository) {
        AccountsViewModelFactory(repository)
    }
    val viewModel: AccountsViewModel = viewModel(factory = factory)
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    var addDialogVisible by rememberSaveable { mutableStateOf(false) }
    var pendingDeleteId by rememberSaveable { mutableStateOf<String?>(null) }

    AccountsScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        addDialogVisible = addDialogVisible,
        pendingDeleteId = pendingDeleteId,
        onBack = onBack,
        onDownloads = onDownloads,
        onSettings = onSettings,
        onShowAddDialog = { addDialogVisible = true },
        onDismissAddDialog = { addDialogVisible = false },
        onAddLocalAccount = viewModel::addLocalAccount,
        onSelectAccount = viewModel::selectAccount,
        onRequestRemove = { pendingDeleteId = it },
        onDismissRemove = { pendingDeleteId = null },
        onRemoveAccount = viewModel::removeAccount,
    )

    LaunchedEffect(viewModel, context) {
        viewModel.events.collect { event ->
            if (event == AccountEvent.ADDED) {
                addDialogVisible = false
            }
            if (event == AccountEvent.REMOVED) {
                pendingDeleteId = null
            }
            snackbarHostState.showSnackbar(context.getString(event.messageResource()))
        }
    }
}

@Composable
fun AccountsScreen(
    state: AccountsUiState,
    snackbarHostState: SnackbarHostState,
    addDialogVisible: Boolean,
    pendingDeleteId: String?,
    onBack: () -> Unit,
    onDownloads: () -> Unit,
    onSettings: () -> Unit,
    onShowAddDialog: () -> Unit,
    onDismissAddDialog: () -> Unit,
    onAddLocalAccount: (String) -> Unit,
    onSelectAccount: (String) -> Unit,
    onRequestRemove: (String) -> Unit,
    onDismissRemove: () -> Unit,
    onRemoveAccount: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            EclipseLauncherTopBar(
                title = stringResource(R.string.accounts_title),
                downloadsContentDescription = stringResource(R.string.accounts_action_downloads),
                settingsContentDescription = stringResource(R.string.accounts_action_settings),
                onDownloads = onDownloads,
                onSettings = onSettings,
                onBack = onBack,
                backContentDescription = stringResource(R.string.accounts_action_back),
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = modifier,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter,
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = EclipseDimensions.ContentMaxWidth),
                contentPadding = PaddingValues(EclipseDimensions.SpaceLarge),
                verticalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceMedium),
            ) {
                if (state.accounts.isEmpty()) {
                    item(key = "empty") {
                        EmptyAccountsState(
                            isSaving = state.isSaving,
                            onAddLocalAccount = onShowAddDialog,
                        )
                    }
                } else {
                    item(key = "accounts-label") {
                        EclipseSectionLabel(
                            text = stringResource(R.string.accounts_section_title),
                        )
                    }
                    items(
                        items = state.accounts,
                        key = Account::id,
                    ) { account ->
                        AccountRow(
                            account = account,
                            selected = account.id == state.selectedAccountId,
                            onSelect = { onSelectAccount(account.id) },
                            onRemove = { onRequestRemove(account.id) },
                        )
                    }
                    item(key = "empty-actions") {
                        AccountActions(
                            isSaving = state.isSaving,
                            onAddLocalAccount = onShowAddDialog,
                        )
                    }
                }
            }
        }
    }

    if (addDialogVisible) {
        AddLocalAccountDialog(
            isSaving = state.isSaving,
            onDismiss = onDismissAddDialog,
            onConfirm = onAddLocalAccount,
        )
    }

    val accountPendingRemoval = state.accounts.firstOrNull { it.id == pendingDeleteId }
    if (accountPendingRemoval != null) {
        RemoveAccountDialog(
            account = accountPendingRemoval,
            onDismiss = onDismissRemove,
            onConfirm = { onRemoveAccount(accountPendingRemoval.id) },
        )
    }
}

@Composable
private fun EmptyAccountsState(
    isSaving: Boolean,
    onAddLocalAccount: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceMedium),
    ) {
        EclipseStatusPanel(
            icon = EclipseIcons.Account,
            title = stringResource(R.string.accounts_empty_title),
            supportingText = stringResource(R.string.accounts_empty_supporting),
        )
        AccountActions(
            isSaving = isSaving,
            onAddLocalAccount = onAddLocalAccount,
        )
    }
}

@Composable
private fun AccountActions(
    isSaving: Boolean,
    onAddLocalAccount: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceSmall),
    ) {
        Button(
            onClick = onAddLocalAccount,
            enabled = !isSaving,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = EclipseDimensions.MinimumTouchTarget),
        ) {
            if (isSaving) {
                CircularProgressIndicator(
                    modifier = Modifier.heightIn(min = EclipseDimensions.SpaceMedium),
                    strokeWidth = 2.dp,
                )
            } else {
                Text(text = stringResource(R.string.accounts_add_local))
            }
        }
        OutlinedButton(
            onClick = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = EclipseDimensions.MinimumTouchTarget),
        ) {
            Text(text = stringResource(R.string.accounts_add_microsoft))
        }
        Text(
            text = stringResource(R.string.accounts_microsoft_blocked),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun AccountRow(
    account: Account,
    selected: Boolean,
    onSelect: () -> Unit,
    onRemove: () -> Unit,
) {
    EclipseStatusPanel(
        icon = EclipseIcons.Account,
        title = account.displayName,
        supportingText = stringResource(account.type.labelResource()),
        onClick = onSelect,
        trailingContent = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceExtraSmall),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selected,
                    onClick = onSelect,
                )
                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = EclipseIcons.Delete,
                        contentDescription = stringResource(R.string.accounts_remove),
                        tint = MaterialTheme.colorScheme.error,
                    )
                }
            }
        },
    )
}

@Composable
private fun AddLocalAccountDialog(
    isSaving: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    var displayName by rememberSaveable { mutableStateOf("") }
    val trimmedName = displayName.trim()
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(R.string.accounts_add_title)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(EclipseDimensions.SpaceSmall)) {
                OutlinedTextField(
                    value = displayName,
                    onValueChange = { displayName = it },
                    label = { Text(text = stringResource(R.string.accounts_name_label)) },
                    supportingText = { Text(text = stringResource(R.string.accounts_name_supporting)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(trimmedName) },
                enabled = trimmedName.isNotEmpty() && !isSaving,
            ) {
                Text(text = stringResource(R.string.accounts_create))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.accounts_cancel))
            }
        },
    )
}

@Composable
private fun RemoveAccountDialog(
    account: Account,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(R.string.accounts_remove_title)) },
        text = {
            Text(
                text = stringResource(R.string.accounts_remove_message, account.displayName),
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                ),
            ) {
                Text(text = stringResource(R.string.accounts_remove_confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.accounts_cancel))
            }
        },
    )
}

private fun AccountType.labelResource() = when (this) {
    AccountType.LOCAL -> R.string.accounts_type_local
    AccountType.MICROSOFT -> R.string.accounts_type_microsoft
}

private fun AccountEvent.messageResource() = when (this) {
    AccountEvent.ADDED -> R.string.accounts_event_added
    AccountEvent.DUPLICATE_NAME -> R.string.accounts_event_duplicate
    AccountEvent.INVALID_BLANK_NAME -> R.string.accounts_event_blank
    AccountEvent.INVALID_LONG_NAME -> R.string.accounts_event_long
    AccountEvent.SELECTED -> R.string.accounts_event_selected
    AccountEvent.REMOVED -> R.string.accounts_event_removed
    AccountEvent.NOT_FOUND -> R.string.accounts_event_not_found
    AccountEvent.FAILED -> R.string.accounts_event_failed
}

@Preview(name = "Accounts wide light", widthDp = 1280, heightDp = 800, showBackground = true)
@Composable
private fun AccountsWideLightPreview() {
    EclipseTheme(themeMode = ThemeMode.LIGHT, dynamicColor = false) {
        AccountsScreen(
            state = AccountsUiState(),
            snackbarHostState = remember { SnackbarHostState() },
            addDialogVisible = false,
            pendingDeleteId = null,
            onBack = {},
            onDownloads = {},
            onSettings = {},
            onShowAddDialog = {},
            onDismissAddDialog = {},
            onAddLocalAccount = {},
            onSelectAccount = {},
            onRequestRemove = {},
            onDismissRemove = {},
            onRemoveAccount = {},
        )
    }
}

@Preview(name = "Accounts compact dark", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun AccountsCompactDarkPreview() {
    EclipseTheme(themeMode = ThemeMode.DARK, dynamicColor = false) {
        AccountsScreen(
            state = AccountsUiState(),
            snackbarHostState = remember { SnackbarHostState() },
            addDialogVisible = false,
            pendingDeleteId = null,
            onBack = {},
            onDownloads = {},
            onSettings = {},
            onShowAddDialog = {},
            onDismissAddDialog = {},
            onAddLocalAccount = {},
            onSelectAccount = {},
            onRequestRemove = {},
            onDismissRemove = {},
            onRemoveAccount = {},
        )
    }
}
