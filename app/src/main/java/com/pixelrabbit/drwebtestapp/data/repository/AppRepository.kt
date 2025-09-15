package com.pixelrabbit.drwebtestapp.data.repository

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import com.pixelrabbit.drwebtestapp.data.model.AppInfo

class AppRepository(private val context: Context) {
    fun getInstalledApps(): List<AppInfo> {
        val pm = context.packageManager
        val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfos = pm.queryIntentActivities(mainIntent, PackageManager.GET_META_DATA)

        return resolveInfos.mapNotNull { ri ->
            val ai = ri.activityInfo ?: return@mapNotNull null
            val packageName = ai.packageName ?: return@mapNotNull null

            val appName = ai.loadLabel(pm)?.toString() ?: packageName
            val icon = ai.loadIcon(pm)
            val versionName = try {
                pm.getPackageInfo(packageName, 0).versionName ?: "N/A"
            } catch (e: NameNotFoundException) {
                "N/A"
            }
            val apkPath = ai.applicationInfo?.sourceDir ?: ""

            AppInfo(
                appName = appName,
                packageName = packageName,
                versionName = versionName,
                apkPath = apkPath,
                icon = icon
            )
        }.sortedBy { it.appName }
    }
}
