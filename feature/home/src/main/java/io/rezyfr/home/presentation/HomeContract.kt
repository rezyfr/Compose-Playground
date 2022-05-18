package io.rezyfr.home.presentation

import io.rezyfr.component.base.ViewEvent
import io.rezyfr.component.base.ViewSideEffect
import io.rezyfr.component.base.ViewState
import io.rezyfr.domain.model.MovieDomainModel
const val HOME_EFFECT_LAUNCHED = "home_effect_launched"
class HomeContract {
    sealed class Event : ViewEvent {
        data class MovieClicked(val id: Int) : Event()
    }

    data class State(
        val popularList: MovieState,
        val nowPlayingList: MovieState
    ) : ViewState

    sealed class MovieState {
        data class Success(val movieList: List<MovieDomainModel>) : MovieState()
        object Loading : MovieState()
        object Empty: MovieState()
        data class Error(val message: String) : MovieState()
    }

    sealed class Effect : ViewSideEffect {
        data class MovieClicked(val id: Int) : Effect()
        data class ShowToast(val message: String): Effect()
    }
}