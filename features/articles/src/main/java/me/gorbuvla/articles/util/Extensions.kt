package me.gorbuvla.articles.util

import android.content.Context
import android.text.format.DateUtils
import org.threeten.bp.ZonedDateTime

fun ZonedDateTime.formatDate(context: Context): String {
    return DateUtils.formatDateTime(context, toInstant().toEpochMilli(), DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR)
}