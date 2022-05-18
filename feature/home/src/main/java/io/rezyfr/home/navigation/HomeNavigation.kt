package io.rezyfr.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.rezyfr.home.presentation.screens.HomeRoute
import io.rezyfr.navigation.MuviPlaygroundNavigationDestination

object HomeNavigation : MuviPlaygroundNavigationDestination {
    override val route: String = "home_route"
    override val destination: String = "home_destination"
}

fun NavGraphBuilder.homeGraph(
    navigateToDetail: (String) -> Unit,
) {
    composable(route = HomeNavigation.route,){
        HomeRoute(navigateToDetail = navigateToDetail)
    }
}