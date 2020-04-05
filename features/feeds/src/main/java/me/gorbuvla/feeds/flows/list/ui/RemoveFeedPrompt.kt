package me.gorbuvla.feeds.flows.list.ui

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.ui.foundation.Text
import androidx.ui.material.AlertDialog
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import me.gorbuvla.core.domain.Feed

@Composable
fun RemoveFeedPrompt(presented: MutableState<Boolean>, feed: Feed, onPositive: () -> Unit) {
    if (presented.value) {
        AlertDialog(
            title = { Text(text = "Delete Feed") },
            onCloseRequest = { presented.value = false },
            dismissButton = {
                Button(onClick = { presented.value = false }, backgroundColor = MaterialTheme.colors.error) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                Button(onClick = { onPositive(); presented.value = false }) {
                    Text(text = "Delete")
                }
            },
            text = {
                Text(text = "Remove ${feed.name}?")
            }
        )
    }
}