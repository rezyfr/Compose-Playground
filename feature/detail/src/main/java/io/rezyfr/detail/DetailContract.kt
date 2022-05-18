package io.rezyfr.detail

import io.rezyfr.component.base.ViewEvent
import io.rezyfr.component.base.ViewSideEffect
import io.rezyfr.component.base.ViewState
import io.rezyfr.domain.model.MovieDomainModel

interface DetailContract {

    sealed class Event : ViewEvent {
        data class FavoriteClicked(val id: Int) : Event()
    }

    sealed interface State : ViewState {
        data class Success(val movieDetail: MovieDomainModel) : State
        object Loading : State
        object Error: State
    }

    sealed class Effect : ViewSideEffect {
        data class FavoriteClicked(val id: Int) : Effect()
    }
}