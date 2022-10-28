package dev.android.play.daggerhilt.components

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FooViewModel @Inject constructor(
    val handle: SavedStateHandle,
    val bar: Bar
) : ViewModel()