package com.swasi.common.recyclerViewHeader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.android.play.R
import dev.android.play.recyclerViewHeader.data.ItemData
import dev.android.play.recyclerViewHeader.viewholder.BaseViewHolder
import dev.android.play.recyclerViewHeader.viewholder.HeaderViewHolder
import dev.android.play.recyclerViewHeader.viewholder.ItemViewHolder

class RecyclerviewHeaderAdapter(val list: List<ItemData>, val map: Map<String, ItemData>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            0 -> {
                HeaderViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.itemview_header, parent, false)
                )
            }
            else -> {
                ItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.itemview_price, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val data = map[list[position].createdOn]
                val headerViewHolder = holder as HeaderViewHolder
                headerViewHolder.bindData(list[position])
            }
            else -> {
                val headerViewHolder = holder as ItemViewHolder
                headerViewHolder.bindData(list[position])
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        if (position == 0 || isDifferentDates(position)) {
            return 0 // header type
        } else {
            return 1 // Item type
        }

    }

    private fun isDifferentDates(position: Int): Boolean {
        if (position > 0) {
            if (list[position - 1].createdOn.equals(list[position].createdOn)) {
                return true
            } else {
                return false
            }
        }
        return false
    }

}