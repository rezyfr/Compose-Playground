package io.rezyfr.home

import MuviColors
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.ramcosta.composedestinations.annotation.Destination
import io.rezyfr.home.presentation.screens.HomeScreen
import io.rezyfr.provider.NavigationProvider
import selectedBottomItemColor
import unselectedBottomItemColor


@Destination(start = true)
@Composable
fun MainScreen(navigator: NavigationProvider) {
    val scaffoldState = rememberScaffoldState()
    val (currentBottomTab, setCurrentBottomTab) = rememberSaveable {
        mutableStateOf(BottomBarHomeItem.HOME)
    }

    Crossfade(currentBottomTab) { bottomTab ->
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = { MainBottomNavigation(bottomTab, setCurrentBottomTab) },
            content = {
                when (bottomTab) {
                    BottomBarHomeItem.HOME -> HomeScreen(
                        navigator = navigator,
                    )
                    BottomBarHomeItem.FAVORITES -> HomeScreen(
                        navigator = navigator
                    )
                }
            }
        )
    }
}


@Composable
private fun MainBottomNavigation(
    bottomTab: BottomBarHomeItem,
    setCurrentBottomTab: (BottomBarHomeItem) -> Unit
) {
    val bottomBarHeight = 56.dp
    val pages = BottomBarHomeItem.values()

    BottomNavigation(
        backgroundColor = MuviColors.background,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsHeight(bottomBarHeight)
    ) {
        pages.forEach { page ->
            val selected = page == bottomTab
            val selectedLabelColor = if (selected) {
                selectedBottomItemColor
            } else {
                unselectedBottomItemColor
            }
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = rememberVectorPainter(image = page.icon),
                        contentDescription = null
                    )
                },
                selected = selected,
                onClick = {
                    setCurrentBottomTab.invoke(page)
                },
                selectedContentColor = selectedBottomItemColor,
                unselectedContentColor = unselectedBottomItemColor,
                alwaysShowLabel = true,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}

enum class BottomBarHomeItem(
    val icon: ImageVector
) {
    HOME(
        icon = Icons.Outlined.Home
    ),
    FAVORITES(
        icon = Icons.Outlined.FavoriteBorder
    ),
}