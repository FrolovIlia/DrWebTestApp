package com.pixelrabbit.drwebtestapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.pixelrabbit.drwebtestapp.data.repository.AppRepository
import com.pixelrabbit.drwebtestapp.data.model.AppInfo
import com.pixelrabbit.drwebtestapp.ui.screens.applist.AppListScreen
import com.pixelrabbit.drwebtestapp.ui.screens.appdetails.AppDetailsScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val repo = AppRepository(context)

    val apps by produceState(initialValue = emptyList<AppInfo>(), key1 = context) {
        value = withContext(Dispatchers.IO) {
            repo.getInstalledApps()
        }
    }

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            AppListScreen(apps = apps) { app ->
                navController.navigate("details/${app.packageName}")
            }
        }

        composable(
            route = "details/{packageName}",
            arguments = listOf(navArgument("packageName") { type = NavType.StringType })
        ) { backStackEntry ->
            val pkg = backStackEntry.arguments?.getString("packageName") ?: return@composable
            AppDetailsScreen(packageName = pkg)
        }
    }
}
