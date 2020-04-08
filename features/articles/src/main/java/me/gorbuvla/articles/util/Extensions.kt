package me.gorbuvla.articles.util

import android.content.Context
import android.text.format.DateUtils
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import org.threeten.bp.ZonedDateTime

@Composable // yes, it has to be... 🙄
fun ZonedDateTime.formatDate(context: Context): String {
    return DateUtils.formatDateTime(ContextAmbient.current, toInstant().toEpochMilli(), DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR)
}