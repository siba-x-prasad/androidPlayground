package dev.android.play.moviedb.data

import com.google.gson.annotations.SerializedName
import dev.android.play.moviedb.api.RestConfig

data class PopularTvShowResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<PopularTvResults> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)


data class PopularTvResults(
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("first_air_date") var firstAirDate: String? = null,
    @SerializedName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("origin_country") var originCountry: ArrayList<String> = arrayListOf(),
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_name") var originalName: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null
) {
    var fullImageUrl = "${RestConfig.BASE_IMAGE_URL}${posterPath}"
}