package me.gorbuvla.ui.components

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.layout.fillMaxHeight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview

/**
 * Component to show full screen progress indicator
 */
@Composable
fun CircularProgress() {
    Box(modifier = Modifier.fillMaxHeight() + Modifier.fillMaxWidth()) {
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