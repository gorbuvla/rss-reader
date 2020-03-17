package me.gorbuvla.rssreader.flows.feedlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.*
import androidx.ui.material.*
import me.gorbuvla.rssreader.base.ViewState
import me.gorbuvla.rssreader.components.CircularProgress
import me.gorbuvla.rssreader.components.RetrySnackbar
import me.gorbuvla.rssreader.flows.feeddetail.FeedDetailActivity
import me.gorbuvla.rssreader.flows.feedlist.ui.FeedList
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
                        is ViewState.Loading -> CircularProgress()
                        is ViewState.Loaded -> FeedList(data = state.data, onItemClick = ::openDetail)
                        is ViewState.Error -> RetrySnackbar(text = state.error.localizedMessage ?: "Error") { viewModel.retry() }
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