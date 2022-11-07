package dev.android.play.xmlparsing.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class SoapResponse @JvmOverloads constructor(
    @field: Element(name = "channel")
    var channel: TrendingSearchResponse? = null
)

@Root(name = "channel", strict = false)
data class TrendingSearchResponse @JvmOverloads constructor(
    @field: ElementList(inline = true)
    var itemList: List<TrendingSearchItem>? = null
)

@Root(name = "item", strict = false)
data class TrendingSearchItem @JvmOverloads constructor(
    @field: Element(name = "title")
    var title: String = "",
    @field: Element(name = "description", required = false)
    var description: String = "",
    @field: Element(name = "link")
    var link: String = ""
)