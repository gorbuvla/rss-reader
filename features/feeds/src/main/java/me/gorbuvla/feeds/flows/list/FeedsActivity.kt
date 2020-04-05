package me.gorbuvla.feeds.flows.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.state
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Close
import androidx.ui.material.icons.filled.Favorite
import me.gorbuvla.core.domain.Feed
import me.gorbuvla.feeds.flows.list.ui.AddFeedPrompt
import me.gorbuvla.feeds.flows.list.ui.FeedList
import me.gorbuvla.feeds.flows.list.ui.RemoveFeedPrompt
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URL

/**
 * Activity for screen with list of current feeds.
 */
class FeedsActivity : AppCompatActivity() {

    private val viewModel: FeedsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            MaterialTheme {
                val addPresented = state { false }
                val removePresented = state { false }
                val remove = state<Feed?> { null }

                Column {
                    TopAppBar(
                        title = { Text(text = "Feeds") },
                        navigationIcon = {
                            IconButton(onClick = { finish() }) {
                                Icon(Icons.Filled.Close)
                            }
                        },
                        actions = {
                            IconButton(onClick = { addPresented.value = true }) {
                                Icon(Icons.Default.Favorite) // TODO: change later to add icon?
                            }
                        })

                    when (val state = observe(data = viewModel.feeds)) {
                        is ViewState.Loaded -> FeedList(data = state.data) { feed ->
                            remove.value = feed
                            removePresented.value = true // 😑 🙄 states cannot be mapped
                        }
                        is ViewState.Empty -> Text(text = "You don't have any feeds")
                    }

                    if (addPresented.value) {
                        AddFeedPrompt(presented = addPresented, onSubmit = { name, link -> viewModel.addNew(name, URL(link)) })
                    }

                    if (removePresented.value && remove.value != null) {
                        RemoveFeedPrompt(presented = removePresented, feed = remove.value!!) {
                            viewModel.remove(remove.value!!)
                        }
                    }
                }
            }
        }
    }
}