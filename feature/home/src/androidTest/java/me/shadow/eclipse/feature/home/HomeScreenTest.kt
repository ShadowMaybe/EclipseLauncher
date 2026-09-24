package me.shadow.eclipse.feature.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.shadow.eclipse.core.designsystem.EclipseTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun emptyHomeExplainsLaunchPrerequisites() {
        composeRule.setContent {
            EclipseTheme(dynamicColor = false) {
                HomeScreen(
                    title = "Eclipse Launcher",
                    state = HomeUiState(),
                )
            }
        }

        composeRule.onNodeWithText("No account selected").assertIsDisplayed()
        composeRule.onNodeWithText("No installed instance").assertIsDisplayed()
        composeRule.onNodeWithText("Launch").assertIsNotEnabled()
        composeRule.onNodeWithText("Select an account before launching.").assertIsDisplayed()
    }

    @Test
    fun unavailableShellActionsRemainDisabled() {
        composeRule.setContent {
            EclipseTheme(dynamicColor = false) {
                HomeScreen(
                    title = "Eclipse Launcher",
                    state = HomeUiState(),
                )
            }
        }

        composeRule.onNodeWithContentDescription("Downloads").assertIsNotEnabled()
        composeRule.onNodeWithContentDescription("Settings").assertIsNotEnabled()
        composeRule.onNodeWithText("Manage control layouts").assertIsNotEnabled()
        composeRule.onNodeWithText("Open instance directory").assertIsNotEnabled()
        composeRule.onNodeWithText("Share diagnostic logs").assertIsNotEnabled()
    }
}
