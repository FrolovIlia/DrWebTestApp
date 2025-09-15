package com.pixelrabbit.drwebtestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pixelrabbit.drwebtestapp.ui.navigation.AppNavHost
import com.pixelrabbit.drwebtestapp.ui.theme.DrWebTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrWebTestAppTheme {
                AppNavHost()
            }
        }
    }
}
