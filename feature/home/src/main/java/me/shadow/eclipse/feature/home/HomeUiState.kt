package me.shadow.eclipse.feature.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeUiState(
    val accountName: String? = null,
    val accountType: AccountType? = null,
    val instanceName: String? = null,
    val versionName: String? = null,
    val activeTaskCount: Int = 0,
    val availableActions: Set<HomeAction> = emptySet(),
) {
    val launchEnabled: Boolean
        get() = accountName != null && instanceName != null && versionName != null && activeTaskCount == 0
}

enum class AccountType {
    MICROSOFT,
    LOCAL,
}

enum class HomeAction {
    ABOUT,
    CONTROL_LAYOUTS,
    INSTANCE_DIRECTORY,
    SHARE_LOGS,
}
