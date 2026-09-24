package me.shadow.eclipse.feature.settings

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsUiState(
    val selectedCategory: SettingsCategory = SettingsCategory.RENDERING,
)

enum class SettingsCategory {
    RENDERING,
    CONTROLS,
    GAME_RUNTIME,
    LAUNCHER,
    ACCESSIBILITY_STORAGE,
}
