package fr.sitadigi.core.usecase.trip

import fr.sitadigi.core.data.TripRepository
import fr.sitadigi.core.domain.Trip

class GetTripByIdUseCase(private val tripRepository: TripRepository)
{
    suspend operator fun invoke(tripId: String) : Trip
    {
        return tripRepository.getTripById(tripId)
    }
}