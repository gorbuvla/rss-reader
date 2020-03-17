package me.gorbuvla.rssreader.flows.feeddetail.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.EmphasisLevels
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import me.gorbuvla.rssreader.model.domain.FeedDetail
import org.threeten.bp.ZonedDateTime

/**
 * TODO add class description
 */
@Composable
fun FeedDetailContent(data: FeedDetail) {
    val typography = MaterialTheme.typography()
    val emphasisLevels = EmphasisLevels()
    VerticalScroller {
        Column(modifier = LayoutSize.Fill + LayoutPadding(16.dp)) {
            ProvideEmphasis(emphasis = emphasisLevels.high) {
                Text(
                    text = data.title,
                    style = typography.h6
                )
            }

            Spacer(modifier = LayoutHeight(4.dp))

            ProvideEmphasis(emphasis = emphasisLevels.disabled) {
                Text(
                    text = data.createdAt.toString(),
                    style = typography.caption
                )
            }

            Spacer(modifier = LayoutHeight(8.dp))

            ProvideEmphasis(emphasis = emphasisLevels.medium) {
                Text(
                    text = data.content,
                    style = typography.body1
                )
            }
        }
    }
}

@Preview
@Composable
private fun FeedDetailPreview() {
    val data = FeedDetail(1, "Blog title", "Blog conent", ZonedDateTime.now())
    MaterialTheme {
        FeedDetailContent(data = data)
    }
}