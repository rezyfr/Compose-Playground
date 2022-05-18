package io.rezyfr.muviplayground.presentation

import MuviColors
import MuviTheme
import MuviTypography
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.rezyfr.muviplayground.navigation.MuviNavHost
import selectedBottomItemColor
import unselectedBottomItemColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MuviApp() {

    MuviTheme(darkTheme = false) {
        val navController = rememberNavController()
        val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntryAsState?.destination

        SetupSystemUi(rememberSystemUiController(), MuviColors.background)

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MuviColors.background
        ) {
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
                            MuviNavHost(
                                navController = navController,
                                modifier = androidx.compose.ui.Modifier
                                    .padding(innerPadding)
                                    .consumedWindowInsets(innerPadding)
                            )
                        }
                    }
                )
            }
        }
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

@Composable
fun SetupSystemUi(
    systemUiController: SystemUiController,
    systemColor: Color
) {
    SideEffect {
        systemUiController.setSystemBarsColor(color = systemColor)
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