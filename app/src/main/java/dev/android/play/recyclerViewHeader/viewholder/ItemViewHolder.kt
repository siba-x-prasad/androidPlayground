package dev.android.play.recyclerViewHeader.viewholder


import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import dev.android.play.R
import dev.android.play.recyclerViewHeader.data.ItemData

class ItemViewHolder(view: View) : BaseViewHolder(view) {

    val tvTitle = view.findViewById<AppCompatTextView>(R.id.tvTitle)
    val tvPrice = view.findViewById<AppCompatTextView>(R.id.tvPrice)

    fun bindData(itemData: ItemData) {
        tvTitle.text = itemData.createdOn
        tvPrice.text = ""
    }
}