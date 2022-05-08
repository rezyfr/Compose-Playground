package io.rezyfr.home.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import io.rezyfr.component.base.BaseViewModel
import io.rezyfr.domain.usecase.GetDiscoverMovie
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDiscoverMovie: GetDiscoverMovie
): BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    init {
        discoverMovies()
    }

    private fun discoverMovies() {
        safeLaunch {
            execute(getDiscoverMovie(Unit)) {
                setState {
                    copy(
                        popularState = popularState.copy(popularList = it, isLoading = false)
                    )
                }
            }
        }
    }

    override fun setInitialState(): HomeContract.State  =
        HomeContract.State(
            nowPlayingState = HomeContract.State.NowPlayingState(listOf()),
            popularState = HomeContract.State.PopularState(listOf())
        )

    override fun handleEvents(event: HomeContract.Event) {

    }
}