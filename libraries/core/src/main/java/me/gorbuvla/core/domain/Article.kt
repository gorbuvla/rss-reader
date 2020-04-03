package me.gorbuvla.core.domain

import org.threeten.bp.ZonedDateTime

data class Article(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: ZonedDateTime
)