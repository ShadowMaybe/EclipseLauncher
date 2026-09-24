package me.shadow.eclipse.core.designsystem

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class EclipseCategory(
    val id: String,
    val label: String,
    val icon: ImageVector,
)

@Composable
fun EclipseCategoryRail(
    categories: List<EclipseCategory>,
    selectedCategoryId: String,
    onCategorySelected: (EclipseCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(
        modifier = modifier.fillMaxHeight(),
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        categories.forEach { category ->
            NavigationRailItem(
                selected = category.id == selectedCategoryId,
                onClick = { onCategorySelected(category) },
                icon = {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = null,
                    )
                },
                label = { Text(text = category.label) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EclipseCategoryTabs(
    categories: List<EclipseCategory>,
    selectedCategoryId: String,
    onCategorySelected: (EclipseCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedIndex = categories.indexOfFirst { it.id == selectedCategoryId }.coerceAtLeast(0)
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier,
        edgePadding = EclipseDimensions.SpaceMedium,
    ) {
        categories.forEach { category ->
            Tab(
                selected = category.id == selectedCategoryId,
                onClick = { onCategorySelected(category) },
                text = { Text(text = category.label) },
                icon = {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = null,
                    )
                },
            )
        }
    }
}
