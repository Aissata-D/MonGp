package fr.sitadigi.core.domain.usecase.trip

import fr.sitadigi.core.domain.repository.TripRepository
import fr.sitadigi.core.domain.model.Trip

class GetTripsUseCase (private val tripRepository: TripRepository){

    suspend operator fun invoke(  departureCity: String? = null, arrivalCity: String? = null): List<Trip>
    {
        return tripRepository.getTrips(departureCity, arrivalCity)
    }


}