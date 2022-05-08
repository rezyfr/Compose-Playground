package io.rezyfr.data.remote.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "results") val data: List<MovieResponse>
)
