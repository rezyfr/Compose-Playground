package io.rezyfr.muviplayground.navigation

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import io.rezyfr.home.HomeNavGraph

object RootNavGraph : NavGraphSpec {
    override val route = "root"

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val startRoute = HomeNavGraph

    override val nestedNavGraphs = listOf(
        HomeNavGraph
    )
}