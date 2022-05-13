package io.rezyfr.data.remote.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "overview")
    val overview: String? = null,
    @Json(name = "original_language")
    val originalLanguage: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "backdrop_path")
    val backdropPath: String? = null,
    @Json(name = "poster_path")
    val posterPath: String? = null,
    @Json(name = "release_date")
    val releaseDate: String? = null,
    @Json(name = "vote_average")
    val voteAverage: Double? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "vote_count")
    val voteCount: Int? = null
)

