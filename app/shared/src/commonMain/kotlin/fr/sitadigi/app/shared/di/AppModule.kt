package fr.sitadigi.app.shared.di

import fr.sitadigi.app.shared.data.repositoryimpl.KtorTripRepository
import fr.sitadigi.app.shared.data.remote.createHttpClient
import fr.sitadigi.app.shared.presentation.ui.publish.PublishTripViewModel
import fr.sitadigi.app.shared.presentation.ui.trips.TripDetailViewModel
import fr.sitadigi.app.shared.presentation.ui.trips.TripListViewModel
import fr.sitadigi.core.domain.repository.TripRepository
import fr.sitadigi.core.domain.usecase.trip.CreateTripUseCase
import fr.sitadigi.core.domain.usecase.trip.GetTripByIdUseCase
import fr.sitadigi.core.domain.usecase.trip.GetTripsUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun appModule(baseUrl: String) = module {
    single { createHttpClient() }
    single<TripRepository> { KtorTripRepository(get(), baseUrl) }
    factory { GetTripsUseCase(get()) }
    factory { GetTripByIdUseCase(get()) }
    viewModel {
        TripListViewModel(
            get()
        )
    }
    viewModel { params ->
        TripDetailViewModel(
            params.get(),
            get()
        )
    }
    factory { CreateTripUseCase(get()) }
    viewModel {
        PublishTripViewModel(
            get()
        )
    }

}