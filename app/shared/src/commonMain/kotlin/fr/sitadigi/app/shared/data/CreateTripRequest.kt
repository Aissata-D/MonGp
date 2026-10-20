package fr.sitadigi.app.shared.data

    import kotlinx.serialization.Serializable

    @Serializable
    data class CreateTripRequest(
        val departureCity: String,
        val arrivalCity: String,
        val flightDateTime: String,
        val numberOfKiloAvailable: Double,
        val priceForOneKilo: Double,
        val flightNumber: String,
        val description: String
    )
