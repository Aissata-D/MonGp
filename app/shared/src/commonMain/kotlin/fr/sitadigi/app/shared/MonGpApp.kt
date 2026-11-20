package fr.sitadigi.app.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import fr.sitadigi.app.shared.di.appModule
import fr.sitadigi.app.shared.navigation.PublishTripRoute
import fr.sitadigi.app.shared.navigation.TripDetailRoute
import fr.sitadigi.app.shared.navigation.TripListRoute
import fr.sitadigi.app.shared.navigation.routeSavedStateConfiguration
import fr.sitadigi.app.shared.presentation.ui.error.toMessage
import fr.sitadigi.app.shared.presentation.ui.publish.PublishTripViewModel
import fr.sitadigi.app.shared.presentation.ui.trips.TripDetailViewModel
import fr.sitadigi.app.shared.presentation.ui.trips.TripListViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.koinConfiguration

@Composable
fun MonGpApp(baseUrl: String) {
    KoinApplication(configuration = koinConfiguration { modules(appModule(baseUrl)) }) {
        fr.sitadigi.app.shared.presentation.ui.theme.MonGpTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(modifier = Modifier.fillMaxSize().safeContentPadding()) {
                    var tripsVersion by remember { mutableIntStateOf(0) }
                    val backStack =
                        rememberNavBackStack(routeSavedStateConfiguration, TripListRoute)

                    NavDisplay(
                        backStack = backStack,
                        onBack = { backStack.removeAt(backStack.lastIndex) },
                        entryDecorators = listOf(
                            rememberSaveableStateHolderNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator(),
                        ),
                        entryProvider = entryProvider {
                            entry<TripListRoute> {
                                val listViewModel =
                                    koinViewModel<TripListViewModel>()
                                LaunchedEffect(tripsVersion) {
                                    listViewModel.refreshIfNeeded(tripsVersion)
                                }
                                fr.sitadigi.app.shared.presentation.ui.trips.TripListScreen(
                                    viewModel = listViewModel,
                                    onTripClick = { tripId -> backStack.add(TripDetailRoute(tripId)) },
                                    onPublishClick = { backStack.add(PublishTripRoute) }
                                )
                            }
                            entry<TripDetailRoute> { route ->
                                fr.sitadigi.app.shared.presentation.ui.trips.TripDetailScreen(
                                    viewModel = koinViewModel<TripDetailViewModel>(
                                        parameters = { parametersOf(route.tripId) }
                                    ),
                                    onBack = { backStack.removeAt(backStack.lastIndex) }
                                )
                            }
                            entry<PublishTripRoute> {
                                val viewModel =
                                    koinViewModel<PublishTripViewModel>()
                                val state by viewModel.uiState.collectAsState()

                                LaunchedEffect(state.published) {
                                    if (state.published) {
                                        tripsVersion++
                                        backStack.removeAt(backStack.lastIndex)
                                    }
                                }

                                fr.sitadigi.app.shared.presentation.ui.publish.PublishTripScreen(
                                    form = state.form,
                                    errors = state.errors,
                                    isSubmitting = state.isSubmitting,
                                    submitError = state.submitError?.toMessage(),
                                    onFormChange = viewModel::onFormChange,
                                    onSubmit = viewModel::submit,
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