package dev.android.play.moviedb.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.moviedb.data.PopularTvShowDataUiState
import dev.android.play.moviedb.data.PopularTvShowResponse
import dev.android.play.utility.NetworkHelper
import dev.android.play.utility.ObservableViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PopularTvShowViewModel
@Inject constructor(
    private val repository: PopularTvShowRepository,
    private val networkHelper: NetworkHelper,
    val adapter: PopularTvShowAdapter
) : ObservableViewModel() {

    private var pageNo: Int = 0

    var uiState = PopularTvShowDataUiState()
        private set

    private val _popularTvShowStateFlow: MutableStateFlow<PopularTvNewsFlowUiState> =
        MutableStateFlow(PopularTvNewsFlowUiState.EmptyState)

    var uiData: StateFlow<PopularTvNewsFlowUiState> = _popularTvShowStateFlow

    init {
        getPopularTvShows()
        observeDataChange()
    }

    fun getPopularTvShows() = viewModelScope.launch {

        pageNo++

        if (!networkHelper.isNetworkConnected()) {
            updateUiState(PopularTvNewsFlowUiState.Error("No Internet Connection"))
            return@launch
        }

        updateUiState(PopularTvNewsFlowUiState.Loading)

        repository.getPopularTvShowByFlow(pageNo).catch {
            updateUiState(PopularTvNewsFlowUiState.Error("No Data Found"))
        }.collect {
            updateUiState(PopularTvNewsFlowUiState.Success(it))
        }
    }

    fun showError(error: String) {
        observableErrorMessage.set(error)
        uiState.apply {
            showList = false
            showErrorState = true
        }
    }

    fun updateMovieList(popularTvShowResponse: PopularTvShowResponse) {
        uiState.apply {
            showList = true
            showErrorState = false
        }
        adapter.updateItems(popularTvShowResponse.results)
    }

    fun showProgress(loading: Boolean) {
        observableLoading.set(loading)
        uiState.apply {
            showList = false
            showErrorState = false
        }
    }

    fun showLoadMoreProgress(isLoadMore: Boolean) {
        observableLoadMore.set(isLoadMore)
        uiState.apply {
            showList = false
            showErrorState = false
        }
    }


    fun updateUiState(state: PopularTvNewsFlowUiState) {
        _popularTvShowStateFlow.value = state
    }

    fun observeDataChange() {
        viewModelScope.launch {
            uiData.collect {
                when (it) {
                    is PopularTvNewsFlowUiState.Loading -> {
                        showLoadMoreProgress(true)
                    }

                    is PopularTvNewsFlowUiState.Success -> {
                        updateMovieList(it.popularTvShows)
                        showLoadMoreProgress(false)
                    }
                    is PopularTvNewsFlowUiState.Error -> {
                        showProgress(false)
                        showLoadMoreProgress(false)
                        showError(it.error)
                    }
                    else -> {}
                }
            }
        }
    }
}

sealed class PopularTvNewsFlowUiState {
    object Loading : PopularTvNewsFlowUiState()
    data class Success(val popularTvShows: PopularTvShowResponse = PopularTvShowResponse()) :
        PopularTvNewsFlowUiState()

    data class Error(val error: String) : PopularTvNewsFlowUiState()
    object EmptyState : PopularTvNewsFlowUiState()
}