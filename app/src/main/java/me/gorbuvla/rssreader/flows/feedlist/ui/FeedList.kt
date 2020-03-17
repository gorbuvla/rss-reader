package me.gorbuvla.rssreader.flows.feedlist.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import me.gorbuvla.rssreader.model.domain.FeedItem
import org.threeten.bp.ZonedDateTime

/**
 * Set of composable views to show list of feeds.
 */

/**
 * Uses [VerticalScrolled] instead of [AdapterList] because later was introduced in dev05 and has a lot of issues
 * @see https://issuetracker.google.com/issues/149976090
 */
@Composable
fun FeedList(data: List<FeedItem>, onItemClick: (FeedItem) -> Unit) {
    VerticalScroller {
        Column {
            data.forEach {  item ->
                ListItem(item = item, onClick = { onItemClick(item) })
            }

            Spacer(modifier = LayoutHeight(16.dp))
        }
    }
}

@Composable
private fun ListItem(item: FeedItem, onClick: () -> Unit) {
    val typography = MaterialTheme.typography()
    Card(shape = RoundedCornerShape(8.dp), elevation = 4.dp, modifier = LayoutWidth.Fill + LayoutPadding(8.dp)) {
        Ripple(bounded = true) {
            Clickable(onClick = onClick) {
                Column(modifier = LayoutWidth.Fill) {
                    Text(
                        text = item.title,
                        style = typography.h6
                    )

                    Spacer(modifier = LayoutHeight(8.dp))

                    Text(
                        text = item.contentPreview,
                        style = typography.body2,
                        maxLines = 5
                    )
                }
            }
        }
    }
}

//@Composable
//private fun FeedList(data: List<FeedItem>, onItemClick: (FeedItem) -> Unit) {
//    val typography = MaterialTheme.typography()
//    AdapterList(data = data) { item ->
//        Card(shape = RoundedCornerShape(8.dp), elevation = 4.dp, modifier = LayoutWidth.Fill + LayoutPadding(8.dp)) {
//            Ripple(bounded = true) {
//                Clickable(onClick = { onItemClick(item) }) {
//                    Column(modifier = LayoutWidth.Fill) {
//                        Text(
//                            text = item.title,
//                            style = typography.h6
//                        )
//
//                        Spacer(modifier = LayoutHeight(16.dp))
//
//                        Text(
//                            text = item.contentPreview,
//                            style = typography.body2,
//                            maxLines = 5
//                        )
//                    }
//                }
//            }
//        }
//    }
//}

@Preview
@Composable
private fun FeedListPreview() {
    val feed = (0..2).map { FeedItem(it, "Blog $it", "Blog $it preview", ZonedDateTime.now()) }
    MaterialTheme {
        FeedList(data = feed, onItemClick = {})
    }
}