package dev.android.play.utility


import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import dev.android.play.R
import dev.android.play.moviedb.api.RestConfig
import dev.android.play.moviedb.ui.PopularTvShowAdapter


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("malthopdapter")
    fun setMaltHopAdapter(view: RecyclerView, adapter: PopularTvShowAdapter) {
        view.adapter = adapter
    }


    @JvmStatic
    @BindingAdapter("adapter")
    fun setAdapter(view: RecyclerView, adapter: PopularTvShowAdapter) {
        view.adapter = adapter
    }


    @JvmStatic
    @BindingAdapter("movieImage")
    fun loadImage(imageview: AppCompatImageView, posterPath: String?) {
        val url = "${RestConfig.BASE_IMAGE_URL}${posterPath}"
        Log.i("movieImage", "=== $url")
        Picasso.with(imageview.context)
            .load(url)
            .into(imageview)
    }

    @JvmStatic
    @BindingAdapter("show")
    fun showOrHideProgress(view: View, showHideStatus: Boolean) {
        view.visibility = if (showHideStatus) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("errorVisibleGone")
    fun showOrHideError(view: View, error: String) {
        view.visibility = if (error.isEmpty()) View.GONE else View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter("errorMessage")
    fun showErrorMessage(view: AppCompatTextView, message: String) {
        message.let {
            view.text = message
        }
    }

    @set:BindingAdapter("invisible")
    var View.invisible
        get() = visibility == View.INVISIBLE
        set(value) {
            visibility = if (value) View.INVISIBLE else View.VISIBLE
        }

    @set:BindingAdapter("gone")
    var View.gone
        get() = visibility == View.GONE
        set(value) {
            visibility = if (value) View.GONE else View.VISIBLE
        }

}