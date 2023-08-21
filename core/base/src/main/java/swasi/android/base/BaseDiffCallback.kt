package swasi.android.base

import androidx.recyclerview.widget.DiffUtil

open class BaseDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}