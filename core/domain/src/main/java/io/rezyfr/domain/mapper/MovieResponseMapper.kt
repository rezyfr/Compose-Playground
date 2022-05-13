package io.rezyfr.domain.mapper

import io.rezyfr.data.remote.model.response.MovieResponse
import io.rezyfr.domain.model.MovieDomainModel

fun MovieResponse.toDomainModel() = MovieDomainModel(
    overview = overview.orEmpty(),
    originalLanguage = originalLanguage.orEmpty(),
    title = title.orEmpty(),
    backdropPath = backdropPath.orEmpty(),
    posterPath = posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    id = id ?: 0,
    voteCount = voteCount ?: 0,
)
