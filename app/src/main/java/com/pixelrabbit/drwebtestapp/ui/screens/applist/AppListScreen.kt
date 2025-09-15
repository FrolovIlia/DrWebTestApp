package com.pixelrabbit.drwebtestapp.ui.screens.applist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pixelrabbit.drwebtestapp.data.model.AppInfo
import com.pixelrabbit.drwebtestapp.ui.components.AppItemRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(apps: List<AppInfo>, onAppClick: (AppInfo) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Установленные приложения") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(apps) { app ->
                AppItemRow(
                    appInfo = app,
                    onClick = { onAppClick(app) }
                )
            }
        }
    }
}
