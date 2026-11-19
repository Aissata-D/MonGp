package fr.sitadigi.core.domain.usecase.trip

import fr.sitadigi.core.domain.repository.TripRepository

class DeleteTripUseCase(private val tripRepository: TripRepository) {
    suspend operator fun invoke(tripId: String) {
        return tripRepository.deleteTrip(tripId)
    }
}