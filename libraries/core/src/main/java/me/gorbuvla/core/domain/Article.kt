package me.gorbuvla.core.domain

import org.threeten.bp.ZonedDateTime

data class Article(
    val id: String,
    val title: String,
    val content: String,
    val author: String,
    val link: String,
    val createdAt: ZonedDateTime
)