package dev.android.play.recyclerViewHeader.data

data class ItemData(
    val requestId: String,
    val txnAmount: String,
    var expiryTime: String,
    val requestStatus: String,
    val createdOn: String,
    var totalAmount: Int,
    val type: Int
)
