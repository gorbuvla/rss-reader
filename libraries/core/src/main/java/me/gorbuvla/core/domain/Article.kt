package me.gorbuvla.core.domain

import org.threeten.bp.ZonedDateTime


/**
 * TODO add class description
 */
data class Article(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: ZonedDateTime
)