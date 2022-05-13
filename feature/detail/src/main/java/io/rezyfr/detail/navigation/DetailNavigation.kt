package io.rezyfr.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ramcosta.composedestinations.utils.composable
import io.rezyfr.navigation.MuviPlaygroundNavigationDestination

object DetailNavigation : MuviPlaygroundNavigationDestination {
    override val route: String = "detail_route"
    override val destination: String = "detail_destination"
    const val movieId = "movieId"
}

fun NavGraphBuilder.detailGraph(
    onBackClick: () -> Unit
) {
    composable(
        route = "${DetailNavigation.route}/{${DetailNavigation.movieId}}",
        arguments = listOf(
            navArgument(DetailNavigation.movieId){
                type = NavType.StringType
            }
        )
    ){

    }
}