package fr.sitadigi.app.shared.data

import fr.sitadigi.core.domain.error.AppError
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class KtorTripRepositoryTest {
    private val tripJson = """
    {
      "id": "t1",
      "userId": "u2",
      "departureCity": "Lyon",
      "arrivalCity": "Bamako",
      "flightDateTime": "2026-11-14T20:00:00Z",
      "numberOfKiloAvailable": 4.5,
      "priceForOneKilo": 12,
      "flightNumber": "AF718",
      "isDeleted": false,
      "description": "Vêtements et documents uniquement, pas de liquide",
      "publicationDateTime": "2026-10-01T10:00:00Z"
    }
"""

    private fun repositoryRespondingWith(status: HttpStatusCode, body: String): KtorTripRepository {
        val engine = MockEngine {
            respond(
                content = body,
                status = status,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = HttpClient(engine) {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        return KtorTripRepository(client, "http://fake")
    }

    @Test
    fun getTrips_returnsTheTripsOfTheServer() = runTest {
        val json = """
            [
              {
                "id": "t1",
                "userId": "u2",
                "departureCity": "Lyon",
                "arrivalCity": "Bamako",
                "flightDateTime": "2026-11-14T20:00:00Z",
                "numberOfKiloAvailable": 4.5,
                "priceForOneKilo": 12,
                "flightNumber": "AF718",
                "isDeleted": false,
                "description": "Vêtements et documents uniquement, pas de liquide",
                "publicationDateTime": "2026-10-01T10:00:00Z"
              },
              {
                "id": "t2",
                "userId": "u2",
                "departureCity": "Lyon",
                "arrivalCity": "Bamako",
                "flightDateTime": "2026-12-02T18:15:00Z",
                "numberOfKiloAvailable": 2,
                "priceForOneKilo": 14,
                "flightNumber": "AF722",
                "isDeleted": false,
                "description": "Petits colis uniquement, moins de 2kg",
                "publicationDateTime": "2026-10-20T09:00:00Z"
              }
            ]
        """
        val repository = repositoryRespondingWith(HttpStatusCode.OK, json)

        val trips = repository.getTrips()

        assertEquals(2, trips.size)
        assertEquals("AF718", trips[0].flightNumber)
        assertEquals("Lyon", trips[0].departureCity)
        assertEquals(4.5, trips[0].numberOfKiloAvailable)
        assertEquals("AF722", trips[1].flightNumber)
    }

    @Test
    fun getTrips_turns402IntoPaymentRequired() = runTest {
        val repository = repositoryRespondingWith(HttpStatusCode.PaymentRequired, "{}")

        assertFailsWith<AppError.PaymentRequired> {
            repository.getTrips()
        }
    }

    @Test
    fun getTripById_returnsTheTrip() = runTest {
        val json = """
        {
          "id": "t1",
          "userId": "u2",
          "departureCity": "Lyon",
          "arrivalCity": "Bamako",
          "flightDateTime": "2026-11-14T20:00:00Z",
          "numberOfKiloAvailable": 4.5,
          "priceForOneKilo": 12,
          "flightNumber": "AF718",
          "isDeleted": false,
          "description": "Vêtements et documents uniquement, pas de liquide",
          "publicationDateTime": "2026-10-01T10:00:00Z"
        }
    """
        val repository = repositoryRespondingWith(HttpStatusCode.OK, json)

        val trip = repository.getTripById("t1")

        assertEquals("t1", trip.id)
        assertEquals("AF718", trip.flightNumber)
    }

    @Test
    fun getTripById_turns404IntoNotFound() = runTest {
        val repository = repositoryRespondingWith(HttpStatusCode.NotFound, "{}")

        assertFailsWith<AppError.NotFound> {
            repository.getTripById("unknown")
        }
    }

    @Test
    fun getMyTrips_returnsMyTrips() = runTest {
        val repository = repositoryRespondingWith(HttpStatusCode.OK, "[$tripJson]")

        val trips = repository.getMyTrips()

        assertEquals(1, trips.size)
        assertEquals("AF718", trips[0].flightNumber)
    }

    @Test
    fun createTrip_returnsTheCreatedTrip() = runTest {
        val repository = repositoryRespondingWith(HttpStatusCode.Created, tripJson)

        val trip = repository.createTrip(
            "Lyon", "Bamako", "2026-11-14T20:00:00Z", 4.5, 12.0, "AF718",
            "Vêtements et documents uniquement, pas de liquide"
        )

        assertEquals("t1", trip.id)
        assertEquals("AF718", trip.flightNumber)
    }

    @Test
    fun createTrip_turns400IntoRequestNotValid() = runTest {
        val repository = repositoryRespondingWith(HttpStatusCode.BadRequest, "{}")

        assertFailsWith<AppError.RequestNotValid> {
            repository.createTrip("Lyon", "Bamako", "2026-11-14T20:00:00Z", 4.5, 12.0, "AF718", "x")
        }
    }

    @Test
    fun updateTrip_returnsTheUpdatedTrip() = runTest {
        val repository = repositoryRespondingWith(HttpStatusCode.OK, tripJson)

        val trip = repository.updateTrip(
            "t1", "Lyon", "Bamako", "2026-11-14T20:00:00Z", 4.5, 12.0, "AF718",
            "Vêtements et documents uniquement, pas de liquide"
        )

        assertEquals("t1", trip.id)
    }

    @Test
    fun updateTrip_turns404IntoNotFound() = runTest {
        val repository = repositoryRespondingWith(HttpStatusCode.NotFound, "{}")

        assertFailsWith<AppError.NotFound> {
            repository.updateTrip("unknown", "Lyon", "Bamako", "2026-11-14T20:00:00Z", 4.5, 12.0, "AF718", "x")
        }
    }

    @Test
    fun deleteTrip_succeedsOn204() = runTest {
        val repository = repositoryRespondingWith(HttpStatusCode.NoContent, "")

        repository.deleteTrip("t1")
    }

    @Test
    fun deleteTrip_turns403IntoAccessDenied() = runTest {
        val repository = repositoryRespondingWith(HttpStatusCode.Forbidden, "{}")

        assertFailsWith<AppError.AccessDenied> {
            repository.deleteTrip("t1")
        }
    }

}