package com.pixelrabbit.drwebtestapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.pixelrabbit.drwebtestapp.R
import com.pixelrabbit.drwebtestapp.data.model.AppInfo
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.core.graphics.drawable.toBitmap

@Composable
fun AppItemRow(
    appInfo: AppInfo,
    onClick: (String) -> Unit
) {
    val iconPainter: Painter = appInfo.icon?.let {
        BitmapPainter(it.toBitmap().asImageBitmap())
    } ?: painterResource(R.drawable.placeholder)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(appInfo.packageName) }
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = iconPainter,
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = appInfo.appName, style = MaterialTheme.typography.bodyLarge)
            Text(text = appInfo.versionName, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
