package fr.sitadigi.core.domain.usecase.trip

import fr.sitadigi.core.domain.repository.TripRepository
import fr.sitadigi.core.domain.model.Trip

class GetMyTripsUseCase(private val tripRepository: TripRepository) {
    suspend operator fun invoke(): List<Trip>{
        return tripRepository.getMyTrips()
    }
}