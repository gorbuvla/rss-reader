package me.gorbuvla.rssreader.components

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutWidth
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Snackbar
import androidx.ui.material.TextButton
import androidx.ui.material.snackbarPrimaryColorFor

/**
 * Basic snackbar with retry button to repeat operation if failed.
 * TODO: shitty one, make it bůtiful, currently displayed in middle of screen 🤮
 */
@Composable
fun RetrySnackbar(text: String, action: () -> Unit) {
    Container(modifier = LayoutWidth.Fill + LayoutHeight.Fill) {
        Snackbar(
            text = { Text(text = text) },
            action = {
                TextButton(
                    contentColor = snackbarPrimaryColorFor(MaterialTheme.colors()),
                    onClick = action
                ) {
                    Text("Retry")
                }
            }
        )
    }
}