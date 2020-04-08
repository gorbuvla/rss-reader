package me.gorbuvla.articles.flow.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Face
import androidx.ui.material.icons.filled.List
import me.gorbuvla.articles.flow.detail.ArticleDetailActivity
import me.gorbuvla.articles.flow.list.ui.ArticleList
import me.gorbuvla.core.domain.ArticleSnapshot
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Activity for screen with feed list.
 */
class ArticleListActivity : AppCompatActivity() {

    private val viewModel: ArticleListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column {
                    TopAppBar(
                        title = { Text(text = "RSS reader") },
                        actions = {
                            when (observe(data = viewModel.loading)) {
                                is ViewState.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
                                is ViewState.Loaded -> IconButton(onClick = { viewModel.loadLatest() }) {
                                    Icon(Icons.Default.Face)
                                }
                            }

                            IconButton(onClick = { openFeeds() }) {
                                Icon(Icons.Default.List)
                            }
                        })

                    when (val state = observe(data = viewModel.articles)) {
                        is ViewState.Loaded -> ArticleList(data = state.data, onItemClick = ::openDetail)
                    }
                }
            }
        }
    }

    private fun openDetail(item: ArticleSnapshot) {
        startActivity(
            Intent(this, ArticleDetailActivity::class.java)
                .putExtras(ArticleDetailActivity.arguments(item.id))
        )
    }

    /**
     * TODO: ok for now, deal with cross feature navigation in future
     */
    private fun openFeeds() {
        startActivity(
            Intent(this, Class.forName("me.gorbuvla.feeds.flows.list.FeedsActivity"))
        )
    }
}