package me.shadow.eclipse.core.designsystem

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object EclipseBrandColors {
    val Night = Color(0xFF0B1020)
    val DeepSpace = Color(0xFF121A2F)
    val CoronaGold = Color(0xFFF4C95D)
    val SolarBlue = Color(0xFF6EA8FE)
    val Moonlight = Color(0xFFF8FAFC)
    val Danger = Color(0xFFBA1A1A)
}

internal val EclipseLightColorScheme: ColorScheme = lightColorScheme(
    primary = Color(0xFF304B9A),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDCE1FF),
    onPrimaryContainer = Color(0xFF00164F),
    secondary = Color(0xFF6F5D00),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFBE287),
    onSecondaryContainer = Color(0xFF231B00),
    tertiary = Color(0xFF006B5B),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFF74F8E3),
    onTertiaryContainer = Color(0xFF00201A),
    error = EclipseBrandColors.Danger,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFAF8FF),
    onBackground = Color(0xFF1B1B21),
    surface = Color(0xFFFAF8FF),
    onSurface = Color(0xFF1B1B21),
    surfaceVariant = Color(0xFFE3E1EC),
    onSurfaceVariant = Color(0xFF46464F),
    outline = Color(0xFF777680),
    outlineVariant = Color(0xFFC7C5D0),
    inverseSurface = Color(0xFF303036),
    inverseOnSurface = Color(0xFFF3EFF7),
    inversePrimary = Color(0xFFB6C4FF),
    scrim = Color.Black,
)

internal val EclipseDarkColorScheme: ColorScheme = darkColorScheme(
    primary = Color(0xFFB6C4FF),
    onPrimary = Color(0xFF002A78),
    primaryContainer = Color(0xFF123B9B),
    onPrimaryContainer = Color(0xFFDCE1FF),
    secondary = Color(0xFFDEC64B),
    onSecondary = Color(0xFF3A2F00),
    secondaryContainer = Color(0xFF564500),
    onSecondaryContainer = Color(0xFFFBE287),
    tertiary = Color(0xFF53DBC7),
    onTertiary = Color(0xFF00382F),
    tertiaryContainer = Color(0xFF005044),
    onTertiaryContainer = Color(0xFF74F8E3),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF121318),
    onBackground = Color(0xFFE4E1E9),
    surface = Color(0xFF121318),
    onSurface = Color(0xFFE4E1E9),
    surfaceVariant = Color(0xFF46464F),
    onSurfaceVariant = Color(0xFFC7C5D0),
    outline = Color(0xFF918F9A),
    outlineVariant = Color(0xFF46464F),
    inverseSurface = Color(0xFFE4E1E9),
    inverseOnSurface = Color(0xFF303036),
    inversePrimary = Color(0xFF304B9A),
    scrim = Color.Black,
)
