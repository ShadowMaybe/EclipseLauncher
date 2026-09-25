package me.shadow.eclipse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import me.shadow.eclipse.core.accounts.LocalAccountRepository
import me.shadow.eclipse.feature.accounts.AccountsRoute
import me.shadow.eclipse.feature.downloads.DownloadsRoute
import me.shadow.eclipse.feature.home.HomeScreen
import me.shadow.eclipse.feature.home.HomeUiState
import me.shadow.eclipse.feature.settings.SettingsRoute

private object EclipseRoutes {
    const val HOME = "home"
    const val ACCOUNTS = "accounts"
    const val DOWNLOADS = "downloads"
    const val SETTINGS = "settings"
}

@Composable
fun EclipseApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val accountRepository = remember(context) {
        LocalAccountRepository.create(context)
    }
    val title = stringResource(R.string.app_name)

    NavHost(
        navController = navController,
        startDestination = EclipseRoutes.HOME,
        modifier = modifier.fillMaxSize(),
    ) {
        composable(EclipseRoutes.HOME) {
            HomeScreen(
                title = title,
                state = HomeUiState(),
                onDownloads = { navController.navigateTopLevel(EclipseRoutes.DOWNLOADS) },
                onSettings = { navController.navigateTopLevel(EclipseRoutes.SETTINGS) },
                onAccounts = { navController.navigate(EclipseRoutes.ACCOUNTS) { launchSingleTop = true } },
            )
        }
        composable(EclipseRoutes.ACCOUNTS) {
            AccountsRoute(
                repository = accountRepository,
                onBack = { navController.popBackStack() },
                onDownloads = { navController.navigateTopLevel(EclipseRoutes.DOWNLOADS) },
                onSettings = { navController.navigateTopLevel(EclipseRoutes.SETTINGS) },
            )
        }
        composable(EclipseRoutes.DOWNLOADS) {
            DownloadsRoute(
                title = title,
                onDownloads = { navController.navigateTopLevel(EclipseRoutes.DOWNLOADS) },
                onSettings = { navController.navigateTopLevel(EclipseRoutes.SETTINGS) },
            )
        }
        composable(EclipseRoutes.SETTINGS) {
            SettingsRoute(
                title = title,
                onDownloads = { navController.navigateTopLevel(EclipseRoutes.DOWNLOADS) },
                onSettings = { navController.navigateTopLevel(EclipseRoutes.SETTINGS) },
            )
        }
    }
}

private fun NavHostController.navigateTopLevel(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
