package me.gorbuvla.rssreader.components

import androidx.compose.Composable
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutWidth
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview

/**
 * Component to show full screen progress indicator
 */
@Composable
fun CircularProgress() {
    Container(modifier = LayoutWidth.Fill + LayoutHeight.Fill) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun CircularProgressPreview() {
    MaterialTheme {
        CircularProgress()
    }
}