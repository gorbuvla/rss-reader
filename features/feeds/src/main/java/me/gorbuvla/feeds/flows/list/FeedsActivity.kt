package me.gorbuvla.feeds.flows.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Close
import me.gorbuvla.feeds.flows.list.ui.FeedList
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Activity for screen with list of current feeds.
 */
class FeedsActivity : AppCompatActivity() {

    private val viewModel: FeedsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            MaterialTheme {
                Column {
                    TopAppBar(
                        title = { Text(text = "Feeds") },
                        navigationIcon = {
                            IconButton(onClick = { finish() }) {
                                Icon(Icons.Filled.Close)
                            }
                        })

                    when (val state = observe(data = viewModel.feeds)) {
                        is ViewState.Loaded -> FeedList(data = state.data) {  }
                    }
                }
            }
        }
    }
}