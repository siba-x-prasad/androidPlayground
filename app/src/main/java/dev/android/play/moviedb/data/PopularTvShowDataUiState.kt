package dev.android.play.moviedb.data

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import dev.android.play.BR
import dev.android.play.utility.visibilityIf

data class PopularTvShowDataUiState(
    @get: Bindable var progressBarVisibility: Int = View.VISIBLE,
    @get: Bindable var listVisibility: Int = View.GONE,
    @get: Bindable var errorStateVisibility: Int = View.GONE
) : BaseObservable() {

    var showErrorState: Boolean = false
        set(value) {
            field = value
            progressBarVisibility = visibilityIf(visible = !value && !showList)
            errorStateVisibility = visibilityIf(visible = value)
            notifyPropertyChanged(BR.progressBarVisibility)
            notifyPropertyChanged(BR.errorStateVisibility)
        }

    var showList: Boolean = false
        set(value) {
            field = value
            progressBarVisibility = visibilityIf(visible = !value && !showErrorState)
            listVisibility = visibilityIf(visible = value)
            notifyPropertyChanged(BR.listVisibility)
            notifyPropertyChanged(BR.progressBarVisibility)
        }
}