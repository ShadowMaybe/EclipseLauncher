package me.shadow.eclipse

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
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
        composeRule.onNodeWithText("No account selected").assertIsDisplayed()
    }

    @Test
    fun offlineLocalAccountCanBeCreatedAndRemoved() {
        composeRule.setContent {
            EclipseTheme(dynamicColor = false) {
                EclipseApp()
            }
        }

        composeRule.onNode(hasText("No account selected") and hasClickAction()).performClick()
        composeRule.onNodeWithText("No accounts yet").assertIsDisplayed()

        composeRule.onNodeWithText("Add offline account").performClick()
        composeRule.onNode(hasSetTextAction()).performTextInput("Test Player")
        composeRule.onNodeWithText("Create account").performClick()
        composeRule.waitForIdle()
        composeRule.onNodeWithText("Test Player").assertIsDisplayed()

        composeRule.onNodeWithContentDescription("Remove account").performClick()
        composeRule.onNode(hasText("Remove") and hasClickAction()).performClick()
        composeRule.waitForIdle()
        composeRule.onNodeWithText("No accounts yet").assertIsDisplayed()
    }
}
