package me.gorbuvla.rssreader.flows.feedlist

/**
 * TODO add class description
 */
class FeedListViewModel {

    val feed = (0..10).map { id ->
        FeedItem(
            id = id,
            title = "Anko: Tips & Tricks",
            content = "Some time ago I decided to try Kotlin in production and a few weeks ago I started developing an application that is the one, the one with Kotlin under the hood. When I discovered Anko library I was amazed by its simplicity and advantages over standard xml approach.\n" +
                    "\n" +
                    "\n" +
                    "A lot of great articles were written about introduction to Anko (like here or here) and this series of mini posts shouldn’t be one of them. My idea is that this will be just a bunch of neat tips and tricks that I (or my colleagues) discover, use and we don’t want to hide under pillow.\n" +
                    "\n" +
                    "Disclaimer: I am very new to Anko and Kotlin in general and it’s possible that sometimes I am reinventing the wheel or that some thing could be done in better way. Please, feel free to point it up in discussion. This feedback is very welcome (or any other feedback as well :))"
        )
    }
}

data class FeedItem(
    val id: Int,
    val title: String,
    val content: String
)