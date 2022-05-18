package io.rezyfr.home.presentation

import androidx.lifecycle.viewModelScope
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
                        popularList = if (it.isEmpty()) HomeContract.MovieState.Empty
                        else HomeContract.MovieState.Success(it)
                    )
                }
            }
            execute(getComingSoonMovies(Unit)) {
                setState {
                    copy(
                        nowPlayingList = if (it.isEmpty()) HomeContract.MovieState.Empty
                        else HomeContract.MovieState.Success(it)
                    )
                }
            }
        }
    }

    override fun setInitialState(): HomeContract.State = HomeContract.State(
        popularList = HomeContract.MovieState.Loading,
        nowPlayingList = HomeContract.MovieState.Loading
    )

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.MovieClicked -> {
                setEffect { HomeContract.Effect.MovieClicked(event.id) }
            }
        }
    }

    override fun handleError(exception: Throwable) {
        setEffect {
            HomeContract.Effect.ShowToast(exception.message.orEmpty())
        }
    }
}