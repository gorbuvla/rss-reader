package me.gorbuvla.feeds.flows.list.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.EmphasisAmbient
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.material.ripple.ripple
import androidx.ui.unit.dp
import me.gorbuvla.core.domain.Feed


@Composable
fun FeedList(data: List<Feed>, onClick: (Feed) -> Unit) {
    VerticalScroller {
        Column {
            data.forEach { feed ->
                FeedItem(item = feed, onClick = { onClick(feed) })
            }
        }

        Spacer(modifier = Modifier.preferredHeight(8.dp))
    }
}

@Composable
private fun FeedItem(item: Feed, onClick: () -> Unit) {
    val typography = MaterialTheme.typography
    val emphasisLevels = EmphasisAmbient.current

    Clickable(modifier = Modifier.ripple(bounded = true), onClick = onClick) {
        Column {
            Column(modifier = Modifier.fillMaxWidth() + Modifier.padding(16.dp)) {
                ProvideEmphasis(emphasis = emphasisLevels.high) {
                    Text(
                        text = item.name,
                        style = typography.subtitle1
                    )
                }

                Spacer(modifier = Modifier.preferredHeight(8.dp))

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