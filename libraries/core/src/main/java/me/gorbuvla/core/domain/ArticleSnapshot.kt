package me.gorbuvla.core.domain

import org.threeten.bp.ZonedDateTime

data class ArticleSnapshot(
    val id: Int,
    val title: String,
    val preview: String,
    val createdAt: ZonedDateTime
)