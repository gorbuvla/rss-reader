package me.gorbuvla.core.domain

import org.threeten.bp.ZonedDateTime

data class Article(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: ZonedDateTime
)