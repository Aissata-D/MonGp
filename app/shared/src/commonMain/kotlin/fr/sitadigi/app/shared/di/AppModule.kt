package fr.sitadigi.app.shared.di

import fr.sitadigi.app.shared.data.repositoryIMPL.KtorTripRepository
import fr.sitadigi.app.shared.data.remote.createHttpClient
import fr.sitadigi.app.shared.ui.publish.PublishTripViewModel
import fr.sitadigi.app.shared.ui.trips.TripDetailViewModel
import fr.sitadigi.app.shared.ui.trips.TripListViewModel
import fr.sitadigi.core.domain.repository.TripRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun appModule(baseUrl: String) = module {
    single { createHttpClient() }
    single<TripRepository> { KtorTripRepository(get(), baseUrl) }
    factory { fr.sitadigi.core.domain.usecase.trip.GetTripsUseCase(get()) }
    factory { fr.sitadigi.core.domain.usecase.trip.GetTripByIdUseCase(get()) }
    viewModel { TripListViewModel(get()) }
    viewModel { params -> TripDetailViewModel(params.get(), get()) }
    factory { fr.sitadigi.core.domain.usecase.trip.CreateTripUseCase(get()) }
    viewModel { PublishTripViewModel(get()) }

}