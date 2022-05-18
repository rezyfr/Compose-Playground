package io.rezyfr.home.presentation.screens

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import io.rezyfr.component.widget.AutoCarousel
import io.rezyfr.component.widget.ErrorScreen
import io.rezyfr.component.widget.ShimmerAnimation
import io.rezyfr.domain.model.MovieDomainModel
import io.rezyfr.home.R
import io.rezyfr.home.presentation.HOME_EFFECT_LAUNCHED
import io.rezyfr.home.presentation.HomeContract
import io.rezyfr.home.presentation.HomeViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach


@Composable
fun HomeRoute(
    navigateToDetail: (String) -> Unit, modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        state = viewModel.viewState.value,
        effect = viewModel.effect,
        navigateToDetail = navigateToDetail,
        onClicked = {
            viewModel.setEvent(it)
        }
    )
}

@Composable
fun HomeScreen(
    state: HomeContract.State,
    navigateToDetail: (String) -> Unit,
    effect: Flow<HomeContract.Effect>,
    onClicked: (HomeContract.Event) -> Unit
) {
    EffectListener(
        effect,
        navigateToDetail
    )
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if (state.popularList is HomeContract.MovieState.Success) {
            AutoCarousel(state.popularList.movieList.map { it.backdropPath })
        }
        MovieList(state.popularList, R.string.popular_movies, onClicked)
        MovieList(state.nowPlayingList, R.string.coming_soon, onClicked)
    }
}

@Composable
fun EffectListener(effect: Flow<HomeContract.Effect>, navigateToDetail: (String) -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(key1 = HOME_EFFECT_LAUNCHED) {
        effect.onEach {
            when (it) {
                is HomeContract.Effect.MovieClicked -> navigateToDetail.invoke(it.id.toString())
                is HomeContract.Effect.ShowToast -> Toast.makeText(
                    context,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@Composable
fun ColumnScope.MovieList(
    state: HomeContract.MovieState,
    @StringRes label: Int,
    onClicked: (HomeContract.Event) -> Unit
) {
    Text(
        text = stringResource(id = label),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(bottom = 8.dp, top = 24.dp, start = 16.dp)
    )
    when (state) {
        is HomeContract.MovieState.Loading -> {
            ShimmerAnimation(width = 100.dp, height = 150.dp, count = 5)
        }
        is HomeContract.MovieState.Error -> {
            ErrorScreen(asset = "alien_no_network.json")
        }
        is HomeContract.MovieState.Success -> {
            if (state.movieList.isEmpty()) ErrorScreen(asset = "empty_states.json")
            else HorizontalMovieList(items = state.movieList, onClicked)
        }
    }
}

@Composable
fun HorizontalMovieList(items: List<MovieDomainModel>, onClicked: (HomeContract.Event) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        LazyRow(
            modifier = Modifier.padding(start = 8.dp),
            content = {
                items(items) { movie ->
                    Image(
                        painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w200/" + movie.posterPath),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(150.dp)
                            .width(100.dp)
                            .padding(end = 8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                onClicked.invoke(HomeContract.Event.MovieClicked(movie.id))
                            },
                    )
                }
            },
        )
    }
}
