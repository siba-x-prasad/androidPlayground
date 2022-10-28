package dev.android.play.data

import androidx.annotation.Keep

@Keep
data class PopularTvShowResponse(
    var page: Int? = null,
    var results: ArrayList<PopularTvShowResults> = arrayListOf(),
    var totalPages: Int? = null,
    var totalResults: Int? = null
)

@Keep
data class PopularTvShowResults(
    var backdropPath: String? = null,
    var firstAirDate: String? = null,
    var genreIds: ArrayList<Int> = arrayListOf(),
    var id: Int? = null,
    var name: String? = null,
    var originCountry: ArrayList<String> = arrayListOf(),
    var originalLanguage: String? = null,
    var originalName: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null

)