package fr.sitadigi.app.shared.presentation.ui.publish

data class TripFormState(
    val departureCity: String = "",
    val arrivalCity: String = "",
    val flightDate: String = "",   // AAAA-MM-JJ
    val flightTime: String = "",   // HH:MM
    val flightNumber: String = "",
    val kilos: String = "",
    val pricePerKilo: String = "",
    val description: String = "",
)

data class TripFormErrors(
    val departureCity: String? = null,
    val arrivalCity: String? = null,
    val flightDate: String? = null,
    val flightTime: String? = null,
    val flightNumber: String? = null,
    val kilos: String? = null,
    val pricePerKilo: String? = null,
    val description: String? = null,
) {
    val hasErrors: Boolean
        get() = listOf(
            departureCity, arrivalCity, flightDate, flightTime,
            flightNumber, kilos, pricePerKilo, description
        ).any { it != null }
}