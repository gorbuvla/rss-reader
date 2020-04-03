package me.gorbuvla.core.domain

import java.net.URL

/**
 * Representation of feed resource.
 */
data class Feed(
    val id: Int,
    val name: String,
    val url: URL
)