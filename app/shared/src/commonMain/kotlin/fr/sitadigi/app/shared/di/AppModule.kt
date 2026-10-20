package fr.sitadigi.app.shared.di

import fr.sitadigi.app.shared.data.KtorTripRepository
import fr.sitadigi.app.shared.data.createHttpClient
import fr.sitadigi.app.shared.ui.trips.TripListViewModel
import fr.sitadigi.core.data.TripRepository
import fr.sitadigi.core.usecase.trip.GetTripsUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun appModule(baseUrl: String) = module {
    single { createHttpClient() }
    single<TripRepository> { KtorTripRepository(get(), baseUrl) }
    factory { GetTripsUseCase(get()) }
    viewModel { TripListViewModel(get()) }
}