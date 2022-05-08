package io.rezyfr.home

import MuviColors
import MuviTypography
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
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
            topBar = {
                TopAppBar(
                    title = {
                        Text(style = MuviTypography.h2, text = buildAnnotatedString {
                            withStyle(style = SpanStyle(MuviColors.onSurface)) {
                                append("Muvi")
                            }
                            withStyle(style = SpanStyle(MuviColors.primary)) {
                                append("DB")
                            }
                        })
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    },
                    backgroundColor = MaterialTheme.colors.background
                )
            },
            backgroundColor = MaterialTheme.colors.surface,
            bottomBar = { MainBottomNavigation(bottomTab, setCurrentBottomTab) },
            content = { innerPadding ->
                Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())) {
                    when (bottomTab) {
                        BottomBarHomeItem.HOME -> HomeScreen(
                            navigator = navigator,
                        )
                        BottomBarHomeItem.FAVORITES -> HomeScreen(
                            navigator = navigator
                        )
                    }
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