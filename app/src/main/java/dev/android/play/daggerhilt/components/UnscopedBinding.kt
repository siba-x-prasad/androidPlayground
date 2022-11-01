package dev.android.play.daggerhilt.components

import javax.inject.Inject

// This binding is "unscoped".
// Each request for this binding will get a new instance.
class UnscopedBinding @Inject constructor() {
}