package me.gorbuvla.feeds.flows.list.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.EmphasisLevels
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.material.ripple.Ripple
import androidx.ui.unit.dp
import me.gorbuvla.core.domain.Feed


@Composable
fun FeedList(data: List<Feed>, onClick: (Feed) -> Unit) {
    VerticalScroller {
        Column {
            data.forEach { feed ->
                FeedItem(item = feed, onLongClick = { onClick(feed) })
            }
        }

        Spacer(modifier = LayoutHeight(16.dp))
    }
}

@Composable
private fun FeedItem(item: Feed, onLongClick: () -> Unit) {
    val typography = MaterialTheme.typography()
    val emphasisLevels = EmphasisLevels()

    Ripple(bounded = true) {
        Clickable(onClick = onLongClick) {
            Column {
                Column(modifier = LayoutWidth.Fill + LayoutPadding(16.dp)) {
                    ProvideEmphasis(emphasis = emphasisLevels.high) {
                        Text(
                            text = item.name,
                            style = typography.subtitle1
                        )
                    }

                    Spacer(modifier = LayoutHeight(8.dp))

                    ProvideEmphasis(emphasis = emphasisLevels.medium) {
                        Text(
                            text = item.url.toString(),
                            style = typography.body2
                        )
                    }
                }
            }
        }
    }
}