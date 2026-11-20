package fr.sitadigi.core.domain.usecase.trip

import fr.sitadigi.core.domain.repository.TripRepository
import fr.sitadigi.core.domain.model.Trip

class GetTripByIdUseCase(private val tripRepository: TripRepository)
{
    suspend operator fun invoke(tripId: String) : Trip
    {
        return tripRepository.getTripById(tripId)
    }
}