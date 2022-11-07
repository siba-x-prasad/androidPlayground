package dev.android.play.xmlparsing.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class RssFeed(
    @Element
    var channel: RssChannel? = null
)

@Root(name = "channel", strict = false)
data class RssChannel(
    @Element
    var title: String,

    @Element
    var image: RssImage,

    @ElementList(inline = true, required = false)
    var item: List<RssItem>
)

@Root(name = "image", strict = false)
data class RssImage(
    @Element
    val url: String? = null,

    @Element
    val width: String? = null,

    @Element
    val height: String? = null
)

@Root(name = "item", strict = false)
data class RssItem(
    @Element
    val title: String? = null,

    @Element
    val link: String? = null,

    @Element
    val pubDate: String? = null,

    @Element
    val description: String? = null
)