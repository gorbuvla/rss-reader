package me.gorbuvla.rssreader.flows.feeddetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.material.*
import me.gorbuvla.rssreader.base.ViewState
import me.gorbuvla.rssreader.util.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.DefinitionParameters
import org.koin.core.parameter.parametersOf

/**
 * Activity for feed detail screen.
 */
class FeedDetailActivity : AppCompatActivity() {

    companion object {

        private const val ID_KEY = "id_key"

        fun arguments(id: Int): Bundle = bundleOf(ID_KEY to id)

        private fun vmParams(arguments: Bundle): DefinitionParameters {
            return parametersOf(arguments.getInt(ID_KEY))
        }
    }

    private val viewModel: FeedDetailViewModel by viewModel { vmParams(intent.extras ?: Bundle()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                TopAppBar(title = { Text(text = "Detail") })

                when (val state = observe(data = viewModel.state)) {
                    is ViewState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is ViewState.Loaded -> {
                        Text(text = state.data.title)
                    }
                    is ViewState.Error -> {
                        Snackbar(
                            text = { Text(text = state.error.localizedMessage ?: "Error") },
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