package fr.sitadigi.core.usecase.trip

import fr.sitadigi.core.data.TripRepository

class DeleteTripUseCase(private val tripRepository: TripRepository) {
    suspend operator fun invoke(tripId: String) {
        return tripRepository.deleteTrip(tripId)
    }
}