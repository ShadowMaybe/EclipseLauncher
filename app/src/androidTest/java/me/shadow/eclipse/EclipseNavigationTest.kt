package me.shadow.eclipse

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.shadow.eclipse.core.designsystem.EclipseTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EclipseNavigationTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun topLevelActionsNavigateAndBackReturnsHome() {
        composeRule.setContent {
            EclipseTheme(dynamicColor = false) {
                EclipseApp()
            }
        }

        composeRule.onNodeWithText("No account selected").assertIsDisplayed()

        composeRule.onNodeWithContentDescription("Downloads").performClick()
        composeRule.onNodeWithText("No game versions yet").assertIsDisplayed()

        composeRule.onNodeWithContentDescription("Settings").performClick()
        composeRule.onNodeWithText("No renderer selected").assertIsDisplayed()

        Espresso.pressBack()
        composeRule.onNodeWithText("No game versions yet").assertIsDisplayed()

        Espresso.pressBack()
        composeRule.onNodeWithText("No account selected").assertIsDisplayed()
    }
}
