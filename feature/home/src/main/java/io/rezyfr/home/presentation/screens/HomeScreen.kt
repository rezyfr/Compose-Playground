package io.rezyfr.home.presentation.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.*
import io.rezyfr.component.widget.AutoCarousel
import io.rezyfr.component.widget.ErrorScreen
import io.rezyfr.component.widget.ShimmerAnimation
import io.rezyfr.domain.model.MovieDomainModel
import io.rezyfr.home.R
import io.rezyfr.home.presentation.HomeContract
import io.rezyfr.home.presentation.HomeViewModel
import io.rezyfr.provider.NavigationProvider

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigator: NavigationProvider
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val state = homeViewModel.viewState.value
    Column(modifier = Modifier.verticalScroll(rememberScrollState())){
        AutoCarousel(state.popularState.list.map { it.backdropPath })
        MovieList(state.popularState, R.string.popular_movies)
        MovieList(state.comingSoonState, R.string.coming_soon)
    }
}
@Composable
fun ColumnScope.MovieList(state: HomeContract.State.MovieListState, @StringRes label: Int) {
    Text(
        text = stringResource(id = label),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(bottom = 8.dp, top = 24.dp, start = 16.dp)
    )
    when {
        state.isLoading -> {
            ShimmerAnimation(width = 100.dp, height = 150.dp, count = 5)
        }
        state.isError -> {
            ErrorScreen(asset = "alien_no_network.json")
        }
        state.list.isEmpty() -> {
            ErrorScreen(asset = "empty_states.json")
        }
        state.list.isNotEmpty() -> {
            HorizontalMovieList(items = state.list)
        }
    }
}
@Composable
fun HorizontalMovieList(items: List<MovieDomainModel>) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        LazyRow(modifier = Modifier.padding(start = 8.dp),
            content = {
                items(items) { movie ->
                    Image(
                        painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w200/" + movie.posterPath),
                        contentDescription = null,
                        modifier = Modifier
                            .height(150.dp)
                            .width(100.dp)
                            .padding(end = 8.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Fit,
                    )
                }
            },
        )
    }
}
