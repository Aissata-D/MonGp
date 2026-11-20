package fr.sitadigi.core.domain.usecase.trip

import fr.sitadigi.core.domain.repository.TripRepository
import fr.sitadigi.core.domain.model.Trip

class UpdateTripUseCase(private val tripRepository: TripRepository) {

    suspend operator fun invoke(tripId: String, departureCity: String, arrivalCity: String, flightDateTime: String, numberOfKiloAvailable: Double,
                                priceForOneKilo: Double, flightNumber: String, description: String): Trip
    {
       return tripRepository.updateTrip(tripId, departureCity, arrivalCity, flightDateTime, numberOfKiloAvailable, priceForOneKilo, flightNumber, description)
    }

}