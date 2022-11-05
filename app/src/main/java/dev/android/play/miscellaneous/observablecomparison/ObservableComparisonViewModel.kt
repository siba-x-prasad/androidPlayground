package dev.android.play.miscellaneous.observablecomparison

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.utility.ObservableViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class ObservableComparisonViewModel : ObservableViewModel() {

    private val _livedata = MutableLiveData<String>("")
    var liveData: LiveData<String> = _livedata

    private val _stateflow = MutableStateFlow("")
    var stateFlow: StateFlow<String> = _stateflow

    private val _mutableSharedFlow = MutableSharedFlow<String>()
    var mutableSharedFlow: SharedFlow<String> = _mutableSharedFlow

    fun onClickLiveData(view: View) {
        _livedata.value = "LiveData"
    }

    fun onClickStateFlow(view: View) {
        _stateflow.value = "State Flow"

        viewModelScope.launch {
            stateFlow.collectLatest {

            }
        }

    }

    fun onClickFlow(view: View) {
        triggerFlow()
    }

    fun triggerFlow(): Flow<String> {
        return flow {
            repeat(5) {
                emit("Flow value $it")
                delay(1000L)
            }
        }
    }

    fun onClickSharedFlow(view: View) {
        viewModelScope.launch {
            _mutableSharedFlow.emit("Shared flow")
        }
    }
}