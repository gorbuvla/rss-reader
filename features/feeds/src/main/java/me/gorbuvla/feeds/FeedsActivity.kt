package me.gorbuvla.feeds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import java.net.URL

/**
 * TODO add class description
 */
class FeedsActivity : AppCompatActivity() {

    private val feeds = listOf(
        "http://android-developers.blogspot.com/atom.xml",
        "https://swift.org/atom.xml?format=xml"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            MaterialTheme {
                Column {
                    
                    feeds.forEach {
                        Text(text = it)
                    }
                }
            }
        }
    }

}