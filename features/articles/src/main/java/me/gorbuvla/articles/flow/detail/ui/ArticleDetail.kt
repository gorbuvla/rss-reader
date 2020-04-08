package me.gorbuvla.articles.flow.detail.ui

import android.text.Html
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import me.gorbuvla.articles.util.formatDate
import me.gorbuvla.core.domain.Article
import org.threeten.bp.ZonedDateTime

/**
 * Composable view of feed detail.
 */
@Composable
fun ArticleDetailContent(data: Article) {
    val typography = MaterialTheme.typography
    val emphasisLevels = MaterialTheme.emphasisLevels
    VerticalScroller {
        Column(modifier = Modifier.fillMaxSize() + Modifier.padding(16.dp)) {
            ProvideEmphasis(emphasis = emphasisLevels.high) {
                Text(
                    text = data.title,
                    style = typography.h6
                )
            }

            Spacer(modifier = Modifier.preferredHeight(4.dp))

            ProvideEmphasis(emphasis = emphasisLevels.disabled) {
                Text(
                    text = data.createdAt.formatDate(ContextAmbient.current) + " " + data.author,
                    style = typography.caption
                )
            }

            Spacer(modifier = Modifier.preferredHeight(8.dp))

            ProvideEmphasis(emphasis = emphasisLevels.medium) {
                Text(
                    text = Html.fromHtml(data.content).toString(),
                    style = typography.body1
                )
            }
        }
    }
}

@Preview
@Composable
private fun FeedDetailPreview() {
    val data = Article("id", "Blog title", "Blog conent", "", "", ZonedDateTime.now())
    MaterialTheme {
        ArticleDetailContent(data = data)
    }
}