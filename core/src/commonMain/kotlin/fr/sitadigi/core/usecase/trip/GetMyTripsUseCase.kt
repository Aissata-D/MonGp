package fr.sitadigi.core.usecase.trip

import fr.sitadigi.core.data.TripRepository
import fr.sitadigi.core.domain.Trip

class GetMyTripsUseCase(private val tripRepository: TripRepository) {
    suspend operator fun invoke(): List<Trip>{
        return tripRepository.getMyTrips()
    }
}