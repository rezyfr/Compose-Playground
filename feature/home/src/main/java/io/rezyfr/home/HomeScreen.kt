package io.rezyfr.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import io.rezyfr.provider.NavigationProvider


@Destination(start = true)
@Composable
fun HomeScreen(navigator: NavigationProvider) {
    val scaffoldState = rememberScaffoldState()
//    val (currentBottomTab, setCurrentBottomTab) = rememberSaveable {
//        mutableStateOf(BottomBarHomeItem.CHARACTERS)
//    }
//    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

//    Crossfade(currentBottomTab) { bottomTab ->
//        Scaffold(
//            scaffoldState = scaffoldState,
//            bottomBar = { HomeBottomNavigation(bottomTab, setCurrentBottomTab) },
//            content = {
//                val modifier = Modifier.padding(it)
//                when (bottomTab) {
//                    BottomBarHomeItem.CHARACTERS -> CharactersScreen(
//                        modifier = modifier,
//                        navigator = navigator,
//                        bottomSheetState = bottomSheetState
//                    )
//                    BottomBarHomeItem.EPISODES -> EpisodesScreen(
//                        modifier = modifier,
//                        navigator = navigator
//                    )
//                    BottomBarHomeItem.LOCATIONS -> LocationsScreen(
//                        modifier = modifier,
//                        navigator = navigator
//                    )
//                    BottomBarHomeItem.SETTINGS -> SettingsScreen(
//                        modifier = modifier,
//                        navigator = navigator
//                    )
//                }
//            }
//        )
//    }
}