package io.rezyfr.muviplayground.navigation

import android.app.Activity
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.doOnPreDraw
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.rezyfr.detail.navigation.DetailNavigation
import io.rezyfr.detail.navigation.detailGraph
import io.rezyfr.home.navigation.HomeNavigation
import io.rezyfr.home.navigation.homeGraph


/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun MuviNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeNavigation.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(
            navigateToDetail = { navController.navigate("${DetailNavigation.route}/$it")}
        )
        detailGraph (
            onBackClick = { navController.popBackStack() }
        )
    }
// Reporting the app fully drawn to get accurate TTFD readings for the baseline profile.
// https://developer.android.com/topic/performance/vitals/launch-time#retrieve-TTFD
//    ReportFullyDrawn(ForYouDestination.route)
}

/**
 * Calling [Activity#reportFullyDrawn] in compose UI.
 */
@Composable
private fun ReportFullyDrawn(destination: String) {
    // Holding on to the local view and calling `reportFullyDrawn` in an `onPreDraw` listener.
    // Compose currently doesn't offer a way to otherwise report fully drawn,
    // so this is a viable approach.
    val localView: View = LocalView.current
    (localView.context as? Activity)?.run {
        localView.doOnPreDraw {
            reportFullyDrawn()
        }
    }
}
