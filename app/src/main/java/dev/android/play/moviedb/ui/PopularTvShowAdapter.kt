package dev.android.play.moviedb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.android.play.BR
import dev.android.play.databinding.ItemviewPopularTvshowBinding
import dev.android.play.moviedb.data.PopularTvResults
import javax.inject.Inject

class PopularTvShowAdapter @Inject constructor() :
    RecyclerView.Adapter<PopularTvShowAdapter.PopularShowViewHolder>() {

    private var beerList: MutableList<PopularTvResults> = ArrayList()

    class PopularShowViewHolder(private val binding: ItemviewPopularTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: PopularTvResults) {
            binding.setVariable(BR.data, obj)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val beerBinding: ItemviewPopularTvshowBinding =
            ItemviewPopularTvshowBinding.inflate(inflater, parent, false)
        return PopularShowViewHolder(beerBinding)
    }

    override fun onBindViewHolder(holder: PopularShowViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount() = differ.currentList.size

    private val beerDiffCallback: DiffUtil.ItemCallback<PopularTvResults> =
        object : DiffUtil.ItemCallback<PopularTvResults>() {
            override fun areItemsTheSame(
                @NonNull oldBeer: PopularTvResults,
                @NonNull newBeer: PopularTvResults
            ): Boolean {
                return oldBeer.id == newBeer.id
            }

            override fun areContentsTheSame(
                @NonNull oldBeer: PopularTvResults,
                @NonNull newBeer: PopularTvResults
            ): Boolean {
                return oldBeer.id == newBeer.id
            }
        }

    private val differ: AsyncListDiffer<PopularTvResults> =
        AsyncListDiffer(this, beerDiffCallback)

    fun updateItems(beers: List<PopularTvResults>?) {
        if (beers?.isNotEmpty() == true) {
            beerList.addAll(beers)
            differ.submitList(beerList)
        }
    }
}