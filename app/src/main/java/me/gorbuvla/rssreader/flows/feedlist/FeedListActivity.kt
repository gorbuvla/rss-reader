package me.gorbuvla.rssreader.flows.feedlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.unit.dp

/**
 * Activity for screen with feed list.
 */
class FeedListActivity : AppCompatActivity() {

    private val viewModel = FeedListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column {
                    TopAppBar(title = { Text(text = "RSS reader") })
                    VerticalScroller {
                        Column {
                            viewModel.feed.forEach { item ->
                                ListItem(item = item)
                                Divider(color = Color.Blue, height = 1.dp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItem(item: FeedItem) {
    Card(shape = RoundedCornerShape(8.dp), elevation = 4.dp) {
        Ripple(bounded = true) {
            Column {
                Text(text = item.title)

                Spacer(modifier = LayoutHeight(16.dp))

                Text(text = item.content, maxLines = 5)
            }
        }
    }
}