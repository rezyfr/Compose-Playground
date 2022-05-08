package io.rezyfr.home.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.*
import io.rezyfr.home.presentation.HomeViewModel
import io.rezyfr.provider.NavigationProvider

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigator: NavigationProvider
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val state = homeViewModel.viewState.value
    Column() {
        when {
            state.isError -> {
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset("alien_no_network.json"))
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    isPlaying = true
                )
                LottieAnimation(
                    composition = composition,
                    progress = progress,
                    modifier = Modifier.weight(2f)
                )
            }
            state.popularState.popularList.isEmpty() -> {
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset("empty_states.json"))
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    isPlaying = true
                )
                LottieAnimation(
                    composition = composition,
                    progress = progress,
                    modifier = Modifier.weight(2f)
                )
            }
            state.popularState.popularList.isNotEmpty() -> {
                LazyRow (
                    contentPadding = PaddingValues(8.dp),
                    content = {
                        items(state.popularState.popularList) {
                            Image(
                                    painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w200/" + state.popularState.popularList[0].posterPath),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(300.dp)
                                    .width(200.dp),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                )
            }
        }
    }
}
