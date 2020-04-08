package me.gorbuvla.articles.flow.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.os.bundleOf
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.*
import me.gorbuvla.articles.flow.detail.ui.ArticleDetailContent
import me.gorbuvla.ui.components.CircularProgress
import me.gorbuvla.ui.components.RetrySnackbar
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.DefinitionParameters
import org.koin.core.parameter.parametersOf
import java.net.URL

/**
 * Activity for feed detail screen.
 */
class ArticleDetailActivity : AppCompatActivity() {

    companion object {

        private const val ID_KEY = "id_key"

        fun arguments(id: Int): Bundle = bundleOf(ID_KEY to id)

        private fun vmParams(arguments: Bundle): DefinitionParameters {
            return parametersOf(arguments.getInt(ID_KEY))
        }
    }

    private val viewModel: ArticleDetailViewModel by viewModel {
        vmParams(intent.extras ?: Bundle())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column {
                    TopAppBar(title = { Text(text = "Detail")},
                        navigationIcon = {
                            IconButton(onClick = { finish() }) {
                                Icon(Icons.Filled.ArrowBack)
                            }
                        },
                        actions = {
                            IconButton(onClick = { openLink() }) {
                                Icon(Icons.Filled.Info)
                            }
                            IconButton(onClick = { share() }) {
                                Icon(Icons.Default.Share)
                            }
                        })


                    when (val state = observe(data = viewModel.state)) {
                        is ViewState.Loading -> CircularProgress()
                        is ViewState.Loaded -> ArticleDetailContent(data = state.data)
                        is ViewState.Error -> RetrySnackbar(text = state.error.localizedMessage ?: "Error")
                    }
                }
            }
        }
    }

    private fun share() {
        viewModel.article?.let { article ->
            val intent = Intent()
                .setAction(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, article.toString())
                .setType("text/plain")

            startActivity(Intent.createChooser(intent, null))
        }
    }

    private fun openLink() {
        viewModel.article?.let { article ->
            CustomTabsIntent.Builder()
                .build()
                .launchUrl(this, Uri.parse(article.link))
        }
    }
}