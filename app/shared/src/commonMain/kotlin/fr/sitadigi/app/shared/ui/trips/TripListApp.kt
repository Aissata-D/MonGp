package fr.sitadigi.app.shared.ui.trips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.sitadigi.app.shared.data.KtorTripRepository
import fr.sitadigi.app.shared.data.createHttpClient
import fr.sitadigi.core.usecase.trip.GetTripsUseCase

import fr.sitadigi.app.shared.di.appModule
import fr.sitadigi.app.shared.ui.theme.MonGpTheme
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TripListApp(baseUrl: String) {
    MonGpTheme {
        val viewModel = viewModel {
            val repository = KtorTripRepository(createHttpClient(), baseUrl)
            TripListViewModel(GetTripsUseCase(repository))
        }
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize().safeContentPadding()) {
                TripListScreen(viewModel)
            }
        }
    }
}

@Composable
fun TripListAppK(baseUrl: String) {
    KoinApplication(application = { modules(appModule(baseUrl)) }) {
        MaterialTheme {
            val viewModel = koinViewModel<TripListViewModel>()
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxSize().safeContentPadding()) {
                    TripListScreen(viewModel)
                }
            }
        }
    }
}
