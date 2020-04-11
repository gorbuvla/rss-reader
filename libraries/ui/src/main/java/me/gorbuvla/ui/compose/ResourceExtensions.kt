package me.gorbuvla.ui.compose

import androidx.annotation.StringRes
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient

@Composable
fun string(@StringRes resId: Int): String = ContextAmbient.current.getString(resId)