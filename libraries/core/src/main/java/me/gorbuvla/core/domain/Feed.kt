package me.gorbuvla.core.domain

import java.net.URL

/**
 * Representation of feed resource.
 */
data class Feed(
    val id: String,
    val name: String,
    val url: URL
)