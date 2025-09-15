package com.pixelrabbit.drwebtestapp.ui.screens.applist

import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.pixelrabbit.drwebtestapp.data.model.AppInfo
import org.junit.Rule
import org.junit.Test

class AppListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun appListScreen_displaysAppItem() {
        val testApp = AppInfo(
            appName = "Test App",
            packageName = "com.example.test",
            versionName = "1.0",
            apkPath = "/path/to/test.apk",
            icon = ColorDrawable(0xFFFF0000.toInt())
        )

        composeTestRule.setContent {
            AppListScreen(apps = listOf(testApp)) {}
        }

        composeTestRule.onNodeWithText("Test App").assertExists()
    }
}
