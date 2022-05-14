package io.rezyfr.detail

import io.rezyfr.component.base.ViewEvent
import io.rezyfr.component.base.ViewSideEffect
import io.rezyfr.component.base.ViewState
import io.rezyfr.domain.model.MovieDomainModel

interface DetailContract {

    sealed class Event : ViewEvent {
        data class MovieClicked(val id: Int) : Event()
    }

    data class State(
        val movieDetail: MovieDomainModel? = null,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data class MovieClicked(val id: Int) : Effect()
    }
}