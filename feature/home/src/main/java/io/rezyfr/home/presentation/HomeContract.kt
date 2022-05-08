package io.rezyfr.home.presentation

import io.rezyfr.component.base.ViewEvent
import io.rezyfr.component.base.ViewSideEffect
import io.rezyfr.component.base.ViewState
import io.rezyfr.domain.model.MovieDomainModel

class HomeContract {
    sealed class Event : ViewEvent {
        data class MovieClicked(val id: Int) : Event()
    }

    data class State(
        val popularState: PopularState,
        val nowPlayingState: NowPlayingState,
        val isError: Boolean = false
    ) : ViewState {
        data class PopularState(
            val popularList: List<MovieDomainModel>,
            val isLoading: Boolean = false,
        )

        data class NowPlayingState(
            val nowPlayingList: List<MovieDomainModel>,
            val isLoading: Boolean = false
        )
    }

    sealed class Effect : ViewSideEffect {
        data class MovieClicked(val id: Int) : Effect()
    }
}