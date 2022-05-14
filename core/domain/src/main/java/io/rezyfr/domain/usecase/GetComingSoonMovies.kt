package io.rezyfr.domain.usecase

import io.rezyfr.data.repositories.MuviRepository
import io.rezyfr.data.utils.DataState
import io.rezyfr.data.utils.apiCall
import io.rezyfr.domain.mapper.toDomainModel
import io.rezyfr.domain.model.MovieDomainModel
import io.rezyfr.domain.utils.DataStateUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetComingSoonMovies @Inject constructor(
    private val repository: MuviRepository
) : DataStateUseCase<Unit, List<MovieDomainModel>>() {

    override suspend fun FlowCollector<DataState<List<MovieDomainModel>>>.execute(params: Unit) {
        val response = apiCall { repository.getComingSoonMovies().map { it.toDomainModel() } }
        emit(response)
    }
}