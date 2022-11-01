package dev.android.play.recyclerViewHeader.viewholder


import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import dev.android.play.R
import dev.android.play.recyclerViewHeader.data.ItemData

class HeaderViewHolder(view: View) : BaseViewHolder(view) {
    val tvMonth = view.findViewById<AppCompatTextView>(R.id.tvMonth)
    val tvTotalPrice = view.findViewById<AppCompatTextView>(R.id.tvTotalPrice)

    fun bindData(itemData: ItemData) {
        tvMonth.text = itemData.createdOn
        tvTotalPrice.text = itemData.totalAmount.toString()
    }
}