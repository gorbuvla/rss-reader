package me.gorbuvla.ui.components

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.layout.fillMaxHeight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Snackbar
import androidx.ui.material.TextButton
import androidx.ui.material.snackbarPrimaryColorFor

/**
 * Basic snackbar with retry button to repeat operation if failed.
 * TODO: shitty one, make it bůtiful, currently displayed in middle of screen 🤮
 */
@Composable
fun RetrySnackbar(text: String, action: () -> Unit = {}) {
    Text(text = "")
    Box(modifier = Modifier.fillMaxWidth() + Modifier.fillMaxHeight()) {
        Snackbar(
            text = { Text(text = text) },
            action = {
                TextButton(
                    contentColor = snackbarPrimaryColorFor(MaterialTheme.colors),
                    onClick = action
                ) {
                    Text("Retry")
                }
            }
        )
    }
}