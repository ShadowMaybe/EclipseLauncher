package me.shadow.eclipse.core.designsystem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import org.junit.Assert.assertTrue
import org.junit.Test

class ColorContrastTest {
    @Test
    fun lightSchemeTextPairsMeetNormalTextContrast() {
        assertTextContrast(EclipseLightColorScheme.primary, EclipseLightColorScheme.onPrimary)
        assertTextContrast(EclipseLightColorScheme.secondary, EclipseLightColorScheme.onSecondary)
        assertTextContrast(EclipseLightColorScheme.background, EclipseLightColorScheme.onBackground)
        assertTextContrast(EclipseLightColorScheme.surface, EclipseLightColorScheme.onSurface)
        assertTextContrast(EclipseLightColorScheme.error, EclipseLightColorScheme.onError)
    }

    @Test
    fun darkSchemeTextPairsMeetNormalTextContrast() {
        assertTextContrast(EclipseDarkColorScheme.primary, EclipseDarkColorScheme.onPrimary)
        assertTextContrast(EclipseDarkColorScheme.secondary, EclipseDarkColorScheme.onSecondary)
        assertTextContrast(EclipseDarkColorScheme.background, EclipseDarkColorScheme.onBackground)
        assertTextContrast(EclipseDarkColorScheme.surface, EclipseDarkColorScheme.onSurface)
        assertTextContrast(EclipseDarkColorScheme.error, EclipseDarkColorScheme.onError)
    }

    private fun assertTextContrast(foreground: Color, background: Color) {
        val lighter = maxOf(foreground.luminance(), background.luminance())
        val darker = minOf(foreground.luminance(), background.luminance())
        val ratio = (lighter + 0.05f) / (darker + 0.05f)
        assertTrue("Contrast ratio was $ratio", ratio >= 4.5f)
    }
}
