package me.gorbuvla.rssreader.model.domain

import org.threeten.bp.ZonedDateTime

data class FeedItem(
    val id: Int,
    val title: String,
    val contentPreview: String,
    val createdAt: ZonedDateTime
)

data class FeedDetail(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: ZonedDateTime
)