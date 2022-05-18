package io.rezyfr.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class NiaTopLevelNavigation(private val navController: NavHostController) {

    fun navigateTo(destination: TopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

data class TopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)
//
//val TOP_LEVEL_DESTINATIONS = listOf(
//    TopLevelDestination(
//        route = ForYouDestination.route,
//        selectedIcon = Icons.Filled.Upcoming,
//        unselectedIcon = Icons.Outlined.Upcoming,
//        iconTextId = for_you
//    ),
//    TopLevelDestination(
//        route = InterestsDestination.route,
//        selectedIcon = Icons.Filled.Grid3x3,
//        unselectedIcon = Icons.Outlined.Grid3x3,
//        iconTextId = interests
//    )
//)
