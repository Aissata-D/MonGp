package fr.sitadigi.core.data

import fr.sitadigi.core.domain.Trip

interface TripRepository {
    suspend fun getTrips(departureCity: String? = null, arrivalCity: String? = null): List<Trip>
    suspend fun getMyTrips(): List<Trip>
    suspend fun getTripById(tripId: String): Trip
    suspend fun createTrip(departureCity: String, arrivalCity: String, flightDateTime: String, numberOfKiloAvailable: Double, priceForOneKilo: Double, flightNumber: String, description: String): Trip
    suspend fun updateTrip(tripId: String, departureCity: String, arrivalCity: String, flightDateTime: String, numberOfKiloAvailable: Double, priceForOneKilo: Double, flightNumber: String, description: String): Trip
    suspend fun deleteTrip(tripId: String)
}