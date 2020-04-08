package me.gorbuvla.articles.flow.list.ui

import android.content.Context
import android.text.Html
import android.text.format.DateUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.ambientOf
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Box
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.material.ripple.ripple
import androidx.ui.text.AnnotatedString
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import me.gorbuvla.articles.util.formatDate
import me.gorbuvla.core.domain.ArticleSnapshot
import me.gorbuvla.ui.compose.padding
import me.gorbuvla.ui.compose.plus
import org.threeten.bp.ZonedDateTime
import java.util.*

/**
 * Set of composable views to show list of feeds.
 */

@Composable
fun ArticleList(data: List<ArticleSnapshot>, onItemClick: (ArticleSnapshot) -> Unit) {
    AdapterList(data = data) { item ->
        ArticleItem(article = item) { onItemClick(item) }

        Divider(color = Color.Gray.copy(alpha = .5f))
    }
}

@Composable
private fun ArticleItem(article: ArticleSnapshot, onClick: () -> Unit) {
    val typography = MaterialTheme.typography
    val emphasisLevels = MaterialTheme.emphasisLevels

    Box(modifier = Modifier.fillMaxWidth() + Modifier.ripple()) {
        Clickable(onClick = onClick) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                ProvideEmphasis(emphasis = emphasisLevels.high) {
                    Text(
                        text = article.title,
                        style = typography.subtitle1 + TextStyle(fontWeight = FontWeight.Bold)
                    )
                }

                ProvideEmphasis(emphasis = emphasisLevels.disabled) {
                    Text(
                        text = article.createdAt.formatDate(ContextAmbient.current),
                        style = typography.caption
                    )
                }

                Spacer(modifier = Modifier.preferredHeight(8.dp))

                ProvideEmphasis(emphasis = emphasisLevels.medium) {
                    Text(
                        text = AnnotatedString(Html.fromHtml(article.preview).toString()),
                        style = typography.body2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 5
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FeedListPreview() {
    val feed = (0..2).map {
        ArticleSnapshot(
            it,
            "Blog $it",
            "Blog $it preview",
            ZonedDateTime.now()
        )
    }
    MaterialTheme {
        ArticleList(data = feed, onItemClick = {})
    }
}