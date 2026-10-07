package fr.sitadigi.core.usecase.trip

import fr.sitadigi.core.data.TripRepository
import fr.sitadigi.core.domain.Trip

class GetTripsUseCase (private val tripRepository: TripRepository){

    suspend operator fun invoke(  departureCity: String? = null, arrivalCity: String? = null): List<Trip>
    {
        return tripRepository.getTrips(departureCity, arrivalCity)
    }


}