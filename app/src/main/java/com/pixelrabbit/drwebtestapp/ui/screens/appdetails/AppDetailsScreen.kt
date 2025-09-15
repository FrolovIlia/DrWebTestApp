package com.pixelrabbit.drwebtestapp.ui.screens.appdetails

import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.pixelrabbit.drwebtestapp.util.ChecksumUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(packageName: String) {
    val context = LocalContext.current
    val pm = context.packageManager
    val scope = rememberCoroutineScope()

    var appName by remember { mutableStateOf("") }
    var versionName by remember { mutableStateOf("") }
    var checksum by remember { mutableStateOf<String?>(null) }
    var apkPath by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(packageName) {
        scope.launch {
            loading = true
            try {
                val packageInfo = pm.getPackageInfo(packageName, 0)
                val appInfo = packageInfo.applicationInfo

                appName = appInfo?.loadLabel(pm)?.toString() ?: "Не найдено"
                versionName = packageInfo.versionName ?: "N/A"
                apkPath = appInfo?.sourceDir ?: ""

                checksum = withContext(Dispatchers.IO) {
                    if (apkPath.isNotEmpty()) ChecksumUtil.calculateSHA1(apkPath) else "N/A"
                }
            } catch (e: PackageManager.NameNotFoundException) {
                Log.e("AppDetailsScreen", "Package not found", e)
                appName = "Не найдено"
                versionName = "N/A"
                checksum = null
            }
            loading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Информация о приложении") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Название: $appName", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Пакет: $packageName", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Версия: $versionName", style = MaterialTheme.typography.bodyMedium)

            if (loading) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp))
                    Text("Вычисляем контрольную сумму…", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                Text(text = "SHA-1: ${checksum ?: "Ошибка"}", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val launchIntent = pm.getLaunchIntentForPackage(packageName)
                if (launchIntent != null) {
                    context.startActivity(launchIntent)
                }
            }) {
                Text("Открыть приложение")
            }
        }
    }
}
