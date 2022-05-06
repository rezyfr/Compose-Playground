package io.rezyfr.home.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ramcosta.composedestinations.scope.DestinationScope
import com.ramcosta.composedestinations.navigation.DestinationDependenciesContainer
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navargs.DestinationsStringNavType
import com.ramcosta.composedestinations.spec.*
import androidx.compose.runtime.remember
import io.rezyfr.home.HomeScreen
import io.rezyfr.provider.NavigationProvider

object HomeScreenDestination : DirectionDestinationSpec {
         
    operator fun invoke() = this
    
    override val routeId = "home_screen"

    override val route = routeId
    
    @Composable
    override fun DestinationScope<Unit>.Content(
		dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<Unit>.() -> Unit
    ) {
		val dependencyContainer = remember { DestinationDependenciesContainer(this) }
		dependencyContainer.apply { dependenciesContainerBuilder() }

		HomeScreen(
			navigator = dependencyContainer.require()
		)
    }
    
}