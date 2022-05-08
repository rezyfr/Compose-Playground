package io.rezyfr.data.repositories

import io.rezyfr.data.remote.model.response.MovieResponse
import io.rezyfr.data.remote.service.MuviService

interface MuviRepository {
    suspend fun getPopularMovies(): List<MovieResponse>
    suspend fun getComingSoonMovies(): List<MovieResponse>
}

class MuviRepositoryImpl(
    private val service: MuviService
) : MuviRepository {
    override suspend fun getPopularMovies(): List<MovieResponse> {
        val response = service.getPopularMovies()
        return response.data
    }
    override suspend fun getComingSoonMovies(): List<MovieResponse> {
        val response = service.getComingSoonMovies()
        return response.data
    }
}