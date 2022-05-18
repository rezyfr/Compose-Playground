package io.rezyfr.detail.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import io.rezyfr.component.widget.ErrorScreen
import io.rezyfr.component.widget.ShimmerAnimation
import io.rezyfr.detail.DetailContract
import io.rezyfr.detail.DetailViewModel
import io.rezyfr.domain.model.MovieDomainModel

@Composable
fun DetailRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    DetailScreen(
        detailState = viewModel.viewState.value,
        onBackClick = onBackClick,
        onFavoriteClick = viewModel::toggleFavorite
    )
}

@Composable
fun DetailScreen(
    detailState: DetailContract.State,
    onBackClick: () -> Unit,
    onFavoriteClick: (Boolean, MovieDomainModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(
                // TODO: Replace with windowInsetsTopHeight after
                //       https://issuetracker.google.com/issues/230383055
                Modifier.windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                )
            )
        }
        when (detailState) {
            DetailContract.State.Loading -> {
                item {
                    DetailToolbar(onBackClick = onBackClick)
                }
                item {
                    ShimmerAnimation(width = 100.dp, height = 150.dp, count = 5)
                }
            }
            DetailContract.State.Error -> {
                item {
                    DetailToolbar(onBackClick = onBackClick)
                }
                item {
                    ErrorScreen(asset = "alien_no_network.json")
                }
            }
            is DetailContract.State.Success -> {
                item {
                    Box() {
                        DetailToolbar(onBackClick = onBackClick)
                        DetailBody(movie = detailState.movieDetail)
                    }
                }
            }
        }
        item {
            Spacer(
                // TODO: Replace with windowInsetsBottomHeight after
                //       https://issuetracker.google.com/issues/230383055
                Modifier.windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom)
                )
            )
        }
    }
}

@Composable
private fun DetailBody(
    movie: MovieDomainModel
) {
    Image(
        painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/" + movie.backdropPath),
        contentDescription = null,
        modifier = Modifier
            .height(500.dp)
            .padding(end = 8.dp),
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun DetailToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
            .background(Color.Transparent.copy(alpha = 0.1f))
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
        }
    }
}
