package me.gorbuvla.articles.flow.detail.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.EmphasisLevels
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import me.gorbuvla.core.domain.Article
import org.threeten.bp.ZonedDateTime

/**
 * Composable view of feed detail.
 */
@Composable
fun ArticleDetailContent(data: Article) {
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
    val data = Article(1, "Blog title", "Blog conent", ZonedDateTime.now())
    MaterialTheme {
        ArticleDetailContent(data = data)
    }
}