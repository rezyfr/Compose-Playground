package io.rezyfr.home.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import io.rezyfr.component.base.BaseViewModel
import io.rezyfr.domain.usecase.GetComingSoonMovies
import io.rezyfr.domain.usecase.GetPopularMovies
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDiscoverMovie: GetPopularMovies,
    private val getComingSoonMovies: GetComingSoonMovies
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    init {
        discoverMovies()
    }

    private fun discoverMovies() {
        safeLaunch {
            execute(getDiscoverMovie(Unit)) {
                setState {
                    copy(
                        popularState = popularState.copy(list = it, isLoading = false)
                    )
                }
            }
            execute(getComingSoonMovies(Unit)) {
                setState {
                    copy(comingSoonState = comingSoonState.copy(list = it, isLoading = false))
                }
            }
        }
    }

    override fun setInitialState(): HomeContract.State =
        HomeContract.State(
            comingSoonState = HomeContract.State.MovieListState(listOf()),
            popularState = HomeContract.State.MovieListState(listOf())
        )

    override fun handleEvents(event: HomeContract.Event) {
    }

    override fun handleError(exception: Throwable) {
        setState {
            copy(
                comingSoonState = comingSoonState.copy(isError = true),
                popularState = popularState.copy(isError = true)
            )
        }
    }
}