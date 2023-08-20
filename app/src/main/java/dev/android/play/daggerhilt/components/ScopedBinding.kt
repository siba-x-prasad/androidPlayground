package dev.android.play.daggerhilt.components

import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

// This binding is "scoped".
// Each request from the same component instance for this binding will
// get the same instance. Since this is the fragment component, this means
// each request from the same fragment.
@FragmentScoped
class ScopedBinding @Inject constructor() {
}