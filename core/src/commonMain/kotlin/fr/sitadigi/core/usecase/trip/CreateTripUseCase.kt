package fr.sitadigi.core.usecase.trip

import fr.sitadigi.core.data.TripRepository
import fr.sitadigi.core.domain.Trip

class CreateTripUseCase(private val tripRepository: TripRepository) {
    suspend operator fun invoke(departureCity: String, arrivalCity: String, flightDateTime: String, numberOfKiloAvailable: Double,
                                priceForOneKilo: Double, flightNumber: String, description: String): Trip {
        return tripRepository.createTrip(departureCity, arrivalCity, flightDateTime, numberOfKiloAvailable, priceForOneKilo, flightNumber, description)

    }
}