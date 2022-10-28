package dev.android.play.daggerhilt.components

import dev.android.play.daggerhilt.di.AuthInterceptorOkHttpClient
import okhttp3.OkHttpClient
import javax.inject.Inject

// Constructor-injected, because Hilt needs to know how to
// provide instances of AnalyticsServiceImpl, too.
class AnalyticsServiceImpl @Inject constructor(
    @AuthInterceptorOkHttpClient private val okHttpClient: OkHttpClient
) : AnalyticsService {
    override fun analyticsMethods() {

    }
}