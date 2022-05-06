package io.rezyfr.home

import com.ramcosta.composedestinations.spec.NavGraphSpec
import io.rezyfr.home.destinations.*

object HomeNavGraph : NavGraphSpec {
    
    override val route = "home"
    
    override val startRoute = HomeScreenDestination
    
    override val destinationsByRoute = listOf(
		HomeScreenDestination
    ).associateBy { it.route }
}
