package com.pixelrabbit.drwebtestapp.data.model

import android.graphics.drawable.ColorDrawable
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AppInfoUnitTest {

    @Test
    fun appInfo_hasCorrectData() {
        val app = AppInfo(
            appName = "Test App",
            packageName = "com.example.test",
            versionName = "1.0",
            apkPath = "/path/to/test.apk",
            icon = ColorDrawable(0xFF0000.toInt())
        )

        assertEquals("Test App", app.appName)
        assertEquals("com.example.test", app.packageName)
        assertEquals("1.0", app.versionName)
        assertTrue(app.apkPath.endsWith(".apk"))
    }
}
