package me.shadow.eclipse.feature.downloads

import androidx.compose.runtime.Immutable

@Immutable
data class DownloadsUiState(
    val selectedCategory: DownloadCategory = DownloadCategory.VERSIONS,
    val activeTaskCount: Int = 0,
)

enum class DownloadCategory {
    VERSIONS,
    CONTENT,
    TASKS,
}
