package me.gorbuvla.articles.flow.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.*
import androidx.ui.material.*
import me.gorbuvla.articles.flow.detail.ArticleDetailActivity
import me.gorbuvla.articles.flow.list.ui.ArticleList
import me.gorbuvla.core.domain.Article
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.components.CircularProgress
import me.gorbuvla.ui.components.RetrySnackbar
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
                    TopAppBar(title = { Text(text = "RSS reader") })

                    when (val state =
                        observe(data = viewModel.state)) {
                        is ViewState.Loading -> CircularProgress()
                        is ViewState.Loaded -> ArticleList(data = state.data, onItemClick = ::openDetail)
                        is ViewState.Error -> RetrySnackbar(text = state.error.localizedMessage ?: "Error") { viewModel.retry() }
                    }
                }
            }
        }
    }

    private fun openDetail(item: Article) {
        startActivity(
            Intent(this, ArticleDetailActivity::class.java)
                .putExtras(ArticleDetailActivity.arguments(item.id))
        )
    }
}