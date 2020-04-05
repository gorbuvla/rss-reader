package me.gorbuvla.feeds.flows.list.ui

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.FocusManagerAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextField
import androidx.ui.input.ImeAction
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.Stack
import androidx.ui.layout.preferredHeight
import androidx.ui.material.AlertDialog
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import me.gorbuvla.feeds.BuildConfig
import java.lang.Exception
import java.net.URL


@Composable
fun AddFeedPrompt(presented: MutableState<Boolean>, onSubmit: (String, String) -> Unit) {
    val nameField = state { if (BuildConfig.DEBUG) "Kotlin Official Blog" else ""  }
    val nameError = state { "" }
    val linkField = state { if (BuildConfig.DEBUG) "http://feeds.feedburner.com/kotlin" else "" }
    val linkError = state { "" }

    fun validateAndSubmit() {
        val nameValidation = "Must not be empty".takeIf { nameField.value.isEmpty() }
        val linkValidation = try {
            URL(linkField.value)
            null
        } catch (_: Exception) {
            "Link is in incorrect format"
        }

        nameError.value = nameValidation.orEmpty()
        linkError.value = linkValidation.orEmpty()

        if (nameValidation == null && linkValidation == null) {
            onSubmit(nameField.value, linkField.value)
            presented.value = false
        }
    }

    if (presented.value) {
        AlertDialog(
            title = { Text(text = "Add Feed") },
            onCloseRequest = { presented.value = false },
            confirmButton = {
                Button(onClick = { validateAndSubmit() }) {
                    Text(text = "Add")
                }
            },
            dismissButton = {
                Button(onClick = { presented.value = false }, backgroundColor = MaterialTheme.colors.error) {
                    Text(text = "Cancel")
                }
            },
            text = {
                val focusManager = FocusManagerAmbient.current

                Column {
                    Stack {
                        TextField(
                            value = nameField.value,
                            onValueChange = {
                                nameField.value = it
                                nameError.value = ""
                            },
                            imeAction = ImeAction.Next,
                            focusIdentifier = "1",
                            onImeActionPerformed = { action ->
                                if (action == ImeAction.Next) {
                                    focusManager.requestFocusById("2")
                                }
                            }
                        )

                        if (nameField.value.isEmpty()) {
                            Text("Title")
                        }
                    }

                    if (nameError.value.isNotEmpty()) {
                        Text(text = nameError.value, style = TextStyle(color = MaterialTheme.colors.error))
                    }
                    
                    Spacer(modifier = Modifier.preferredHeight(8.dp))

                    Stack {
                        TextField(
                            focusIdentifier = "2",
                            value = linkField.value,
                            onValueChange = {
                                linkField.value = it
                                linkError.value = ""
                            },
                            imeAction = ImeAction.Go,
                            onImeActionPerformed = { action ->
                                if (action == ImeAction.Go) {
                                    validateAndSubmit()
                                }
                            }
                        )

                        if (linkField.value.isEmpty()) Text(text = "Link")
                    }

                    if (linkError.value.isNotEmpty()) {
                        Text(text = linkError.value, style = TextStyle(color = MaterialTheme.colors.error))
                    }
                }
            }
        )
    }
}