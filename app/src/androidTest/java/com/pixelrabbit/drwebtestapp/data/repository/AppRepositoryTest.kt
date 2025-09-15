package com.pixelrabbit.drwebtestapp.data.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pixelrabbit.drwebtestapp.data.model.AppInfo
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppRepositoryTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val repo = AppRepository(context)

    @Test
    fun testGetInstalledApps_returnsNonEmptyList() {
        val apps: List<AppInfo> = repo.getInstalledApps()
        assertNotNull(apps)
        assertTrue(apps.isNotEmpty())
        val first = apps.first()
        assertNotNull(first.appName)
        assertNotNull(first.packageName)
    }
}
