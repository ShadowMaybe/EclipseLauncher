package me.shadow.eclipse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import me.shadow.eclipse.core.designsystem.EclipseTheme
import me.shadow.eclipse.feature.home.HomeScreen
import me.shadow.eclipse.feature.home.HomeUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EclipseApplication()
        }
    }
}

@Composable
private fun EclipseApplication() {
    EclipseTheme {
        HomeScreen(
            title = stringResource(R.string.app_name),
            state = HomeUiState(),
        )
    }
}
