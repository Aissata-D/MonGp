package fr.sitadigi.app.shared.ui.trips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import fr.sitadigi.app.shared.di.appModule
import fr.sitadigi.app.shared.navigation.TripDetailRoute
import fr.sitadigi.app.shared.navigation.TripListRoute
import fr.sitadigi.app.shared.navigation.routeSavedStateConfiguration
import fr.sitadigi.app.shared.ui.theme.MonGpTheme
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TripListApp(baseUrl: String) {
    KoinApplication(application = { modules(appModule(baseUrl)) }) {
        MonGpTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(modifier = Modifier.fillMaxSize().safeContentPadding()) {
                    val backStack = rememberNavBackStack(routeSavedStateConfiguration, TripListRoute)

                    NavDisplay(
                        backStack = backStack,
                        onBack = { backStack.removeAt(backStack.lastIndex) },
                        entryDecorators = listOf(
                            rememberSaveableStateHolderNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator(),
                        ),
                        entryProvider = entryProvider {
                            entry<TripListRoute> {
                                TripListScreen(
                                    viewModel = koinViewModel<TripListViewModel>(),
                                    onTripClick = { tripId -> backStack.add(TripDetailRoute(tripId)) }
                                )
                            }
                            entry<TripDetailRoute> { route ->
                                TripDetailScreen(
                                    viewModel = koinViewModel<TripDetailViewModel>(
                                        parameters = { parametersOf(route.tripId) }
                                    ),
                                    onBack = { backStack.removeAt(backStack.lastIndex) }
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}