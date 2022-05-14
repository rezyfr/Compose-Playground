package io.rezyfr.domain.model

data class MovieDomainModel(
    val overview: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val id: Int,
    val voteCount: Int
)
