package me.gorbuvla.rssreader.flows.feedlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.unit.dp
import me.gorbuvla.rssreader.base.ViewState
import me.gorbuvla.rssreader.flows.feeddetail.FeedDetailActivity
import me.gorbuvla.rssreader.model.domain.FeedItem
import me.gorbuvla.rssreader.util.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Activity for screen with feed list.
 */
class FeedListActivity : AppCompatActivity() {

    private val viewModel: FeedListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column {
                    TopAppBar(title = { Text(text = "RSS reader") })


                    when (val state = observe(data = viewModel.state)) {
                        is ViewState.Loading -> {
                            CircularProgressIndicator()
                        }
                        is ViewState.Loaded -> {
                            VerticalScroller {
                                Column {
                                    state.data.forEach { item ->
                                        ListItem(item = item) { openDetail(item) }
                                        Divider(color = Color.Blue, height = 1.dp)
                                    }
                                }
                            }
                        }
                        is ViewState.Error -> {
                            Snackbar(
                                text = { Text(text = state.error.localizedMessage ?: "Error")},
                                action = {
                                    TextButton(
                                        contentColor = snackbarPrimaryColorFor(MaterialTheme.colors()),
                                        onClick = { viewModel.retry() }
                                    ) {
                                        Text("Retry")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun openDetail(item: FeedItem) {
        startActivity(
            Intent(this, FeedDetailActivity::class.java)
                .putExtras(FeedDetailActivity.arguments(item.id))
        )
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

                    Spacer(modifier = LayoutHeight(16.dp))

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