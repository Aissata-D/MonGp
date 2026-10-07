package fr.sitadigi.core.usecase.trip

import fr.sitadigi.core.data.TripRepository
import fr.sitadigi.core.domain.Trip

class UpdateTripUseCase(private val tripRepository: TripRepository) {

    suspend operator fun invoke(tripId: String, departureCity: String, arrivalCity: String, flightDateTime: String, numberOfKiloAvailable: Double,
                                priceForOneKilo: Double, flightNumber: String, description: String): Trip
    {
       return tripRepository.updateTrip(tripId, departureCity, arrivalCity, flightDateTime, numberOfKiloAvailable, priceForOneKilo, flightNumber, description)
    }

}