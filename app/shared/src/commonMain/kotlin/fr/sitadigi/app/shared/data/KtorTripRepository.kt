package fr.sitadigi.app.shared.data

import fr.sitadigi.core.data.TripRepository
import fr.sitadigi.core.domain.Trip
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorTripRepository(
    private val client: HttpClient,
    private val baseUrl: String
) : TripRepository {
    override suspend fun getTrips(
        departureCity: String?,
        arrivalCity: String?
    ): List<Trip> = safeCall {
        val response = client.get("$baseUrl/trips") {
            departureCity?.let { parameter("departureCity", it) }
            arrivalCity?.let { parameter("arrivalCity", it) }
        }
        response.throwIfError()
        response.body()
    }

    override suspend fun getMyTrips(): List<Trip>  = safeCall {
        val response = client.get("$baseUrl/trips/me")
        response.throwIfError()
        response.body()
    }
    override suspend fun getTripById(tripId: String): Trip = safeCall {
        val response = client.get("$baseUrl/trips/$tripId")
        response.throwIfError()
        response.body()
    }

    override suspend fun createTrip(
        departureCity: String,
        arrivalCity: String,
        flightDateTime: String,
        numberOfKiloAvailable: Double,
        priceForOneKilo: Double,
        flightNumber: String,
        description: String
    ): Trip = safeCall {
        val response = client.post("$baseUrl/trips") {
            contentType(ContentType.Application.Json)
            setBody(
                TripRequest(
                    departureCity, arrivalCity, flightDateTime,
                    numberOfKiloAvailable, priceForOneKilo, flightNumber, description
                )
            )
        }
        response.throwIfError()
        response.body()
    }

    override suspend fun updateTrip(
        tripId: String,
        departureCity: String,
        arrivalCity: String,
        flightDateTime: String,
        numberOfKiloAvailable: Double,
        priceForOneKilo: Double,
        flightNumber: String,
        description: String
    ): Trip = safeCall {
        val response = client.patch("$baseUrl/trips/$tripId") {
            contentType(ContentType.Application.Json)
            setBody(
                TripRequest(
                    departureCity, arrivalCity, flightDateTime,
                    numberOfKiloAvailable, priceForOneKilo, flightNumber, description
                )
            )
        }
        response.throwIfError()
        response.body()
    }

    override suspend fun deleteTrip(tripId: String) = safeCall {
        val response = client.delete("$baseUrl/trips/$tripId")
        response.throwIfError()
    }
}
