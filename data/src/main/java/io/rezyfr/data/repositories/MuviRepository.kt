package io.rezyfr.data.repositories

import io.rezyfr.data.remote.model.response.MovieResponse
import io.rezyfr.data.remote.service.MuviService

interface MuviRepository {
    suspend fun discoverMovie(): List<MovieResponse>
}

class MuviRepositoryImpl(
    private val service: MuviService
) : MuviRepository {
    override suspend fun discoverMovie(): List<MovieResponse> {
        val response = service.getPopularMovies()
        return response.data
    }
}