package me.gorbuvla.ui.compose

import androidx.ui.core.Modifier
import androidx.ui.layout.padding
import androidx.ui.text.TextStyle
import androidx.ui.unit.Dp

fun Modifier.padding(vertical: Dp, horizontal: Dp) = padding(start = horizontal, top = vertical, end = horizontal, bottom = vertical)

operator fun TextStyle.plus(textStyle: TextStyle) = this.merge(textStyle)