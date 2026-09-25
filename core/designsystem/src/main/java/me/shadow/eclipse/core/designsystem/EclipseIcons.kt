package me.shadow.eclipse.core.designsystem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object EclipseIcons {
    val Download = eclipseIcon("EclipseDownload") {
        moveTo(11f, 2f)
        lineTo(13f, 2f)
        lineTo(13f, 11f)
        lineTo(16f, 11f)
        lineTo(12f, 16f)
        lineTo(8f, 11f)
        lineTo(11f, 11f)
        close()
        moveTo(4f, 18f)
        lineTo(20f, 18f)
        lineTo(20f, 21f)
        lineTo(4f, 21f)
        close()
    }

    val Settings = eclipseIcon("EclipseSettings") {
        moveTo(3f, 4f)
        lineTo(15f, 4f)
        lineTo(15f, 6f)
        lineTo(3f, 6f)
        close()
        moveTo(18f, 3f)
        lineTo(21f, 3f)
        lineTo(21f, 7f)
        lineTo(18f, 7f)
        close()
        moveTo(3f, 10f)
        lineTo(20f, 10f)
        lineTo(20f, 12f)
        lineTo(3f, 12f)
        close()
        moveTo(5f, 9f)
        lineTo(8f, 9f)
        lineTo(8f, 13f)
        lineTo(5f, 13f)
        close()
        moveTo(3f, 16f)
        lineTo(15f, 16f)
        lineTo(15f, 18f)
        lineTo(3f, 18f)
        close()
        moveTo(18f, 15f)
        lineTo(21f, 15f)
        lineTo(21f, 19f)
        lineTo(18f, 19f)
        close()
    }

    val Back = eclipseIcon("EclipseBack") {
        moveTo(20f, 11f)
        lineTo(13f, 11f)
        lineTo(18f, 6f)
        lineTo(16f, 4f)
        lineTo(8f, 12f)
        lineTo(16f, 20f)
        lineTo(18f, 18f)
        lineTo(13f, 13f)
        lineTo(20f, 13f)
        close()
    }

    val Delete = eclipseIcon("EclipseDelete") {
        moveTo(9f, 3f)
        lineTo(15f, 3f)
        lineTo(15f, 5f)
        lineTo(20f, 5f)
        lineTo(20f, 7f)
        lineTo(4f, 7f)
        lineTo(4f, 5f)
        lineTo(9f, 5f)
        close()
        moveTo(6f, 8f)
        lineTo(18f, 8f)
        lineTo(17f, 21f)
        lineTo(7f, 21f)
        close()
        moveTo(10f, 10f)
        lineTo(12f, 10f)
        lineTo(12f, 19f)
        lineTo(10f, 19f)
        close()
        moveTo(14f, 10f)
        lineTo(16f, 10f)
        lineTo(16f, 19f)
        lineTo(14f, 19f)
        close()
    }

    val Info = eclipseIcon("EclipseInfo") {
        moveTo(10f, 3f)
        lineTo(14f, 3f)
        lineTo(14f, 7f)
        lineTo(10f, 7f)
        close()
        moveTo(10f, 9f)
        lineTo(14f, 9f)
        lineTo(14f, 20f)
        lineTo(10f, 20f)
        close()
        moveTo(8f, 21f)
        lineTo(16f, 21f)
        lineTo(16f, 23f)
        lineTo(8f, 23f)
        close()
    }

    val Controls = eclipseIcon("EclipseControls") {
        moveTo(3f, 6f)
        lineTo(21f, 6f)
        lineTo(21f, 9f)
        lineTo(3f, 9f)
        close()
        moveTo(3f, 15f)
        lineTo(21f, 15f)
        lineTo(21f, 18f)
        lineTo(3f, 18f)
        close()
        moveTo(3f, 9f)
        lineTo(6f, 9f)
        lineTo(6f, 15f)
        lineTo(3f, 15f)
        close()
        moveTo(18f, 9f)
        lineTo(21f, 9f)
        lineTo(21f, 15f)
        lineTo(18f, 15f)
        close()
        moveTo(7f, 10f)
        lineTo(10f, 10f)
        lineTo(10f, 13f)
        lineTo(7f, 13f)
        close()
        moveTo(10f, 11f)
        lineTo(15f, 11f)
        lineTo(15f, 14f)
        lineTo(10f, 14f)
        close()
    }

    val Folder = eclipseIcon("EclipseFolder") {
        moveTo(2f, 5f)
        lineTo(10f, 5f)
        lineTo(12f, 8f)
        lineTo(22f, 8f)
        lineTo(22f, 20f)
        lineTo(2f, 20f)
        close()
    }

    val Share = eclipseIcon("EclipseShare") {
        moveTo(16f, 2f)
        lineTo(21f, 2f)
        lineTo(21f, 7f)
        lineTo(16f, 7f)
        close()
        moveTo(3f, 10f)
        lineTo(8f, 10f)
        lineTo(8f, 15f)
        lineTo(3f, 15f)
        close()
        moveTo(16f, 17f)
        lineTo(21f, 17f)
        lineTo(21f, 22f)
        lineTo(16f, 22f)
        close()
        moveTo(8f, 11f)
        lineTo(17f, 5f)
        lineTo(18f, 7f)
        lineTo(9f, 13f)
        close()
        moveTo(8f, 14f)
        lineTo(17f, 20f)
        lineTo(16f, 22f)
        lineTo(7f, 16f)
        close()
    }

    val Account = eclipseIcon("EclipseAccount") {
        moveTo(9f, 2f)
        lineTo(15f, 2f)
        lineTo(15f, 8f)
        lineTo(9f, 8f)
        close()
        moveTo(4f, 21f)
        lineTo(4f, 17f)
        lineTo(7f, 13f)
        lineTo(17f, 13f)
        lineTo(20f, 17f)
        lineTo(20f, 21f)
        close()
    }
}

private fun eclipseIcon(
    name: String,
    geometry: PathBuilder.() -> Unit,
): ImageVector = ImageVector.Builder(
    name = name,
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    path(fill = SolidColor(Color.Black)) {
        geometry()
    }
}.build()
