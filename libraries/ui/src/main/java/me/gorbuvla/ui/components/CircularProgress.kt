package me.gorbuvla.ui.components

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.layout.fillMaxSize
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview

/**
 * Component to show full screen progress indicator
 */
@Composable
fun CircularProgress() {
    Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
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