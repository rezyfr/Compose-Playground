package io.rezyfr.data.remote.service

import io.rezyfr.data.remote.model.response.MovieListResponse
import io.rezyfr.data.remote.model.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MuviService {
    @GET("movie/")
    suspend fun discoverMovie(
        @Query("page") page: Int
    ): List<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieListResponse

    @GET("movie/now_playing")
    fun getLatestMovies(): List<MovieResponse>

//
//    @GET("movie/{type}")
//    suspend fun getMovieByType(
//        @Path("type") list: String,
//        @Query("page") page: Int
//    ): MovieListResponse
//
//    @GET("search/movie")
//    suspend fun searchMovie(
//        @Query("query") query: String
//    ): MovieListResponse
//
//    @GET("movie/{movie_id}")
//    suspend fun getMovieDetail(
//        @Path("movie_id") movieId: Int
//    ): MovieDetailResponse
}