package fr.sitadigi.core.domain.usecase.trip

import fr.sitadigi.core.domain.repository.TripRepository
import fr.sitadigi.core.domain.model.Trip

class CreateTripUseCase(private val tripRepository: TripRepository) {
    suspend operator fun invoke(departureCity: String, arrivalCity: String, flightDateTime: String, numberOfKiloAvailable: Double,
                                priceForOneKilo: Double, flightNumber: String, description: String): Trip {
        return tripRepository.createTrip(departureCity, arrivalCity, flightDateTime, numberOfKiloAvailable, priceForOneKilo, flightNumber, description)

    }
}