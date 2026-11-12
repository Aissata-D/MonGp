package fr.sitadigi.app.shared.ui.publish
private val dateRegex = Regex("""\d{4}-\d{2}-\d{2}""")
private val timeRegex = Regex("""\d{2}:\d{2}""")

private fun String.toPositiveNumberOrNull(): Double? =
    replace(',', '.').toDoubleOrNull()?.takeIf { it > 0 }

fun TripFormState.validate(): TripFormErrors = TripFormErrors(
    departureCity = if (departureCity.isBlank()) "Indique la ville de départ." else null,
    arrivalCity = if (arrivalCity.isBlank()) "Indique la ville d'arrivée." else null,
    flightDate = if (!dateRegex.matches(flightDate.trim())) "Format attendu : AAAA-MM-JJ." else null,
    flightTime = if (!timeRegex.matches(flightTime.trim())) "Format attendu : HH:MM." else null,
    flightNumber = if (flightNumber.isBlank()) "Indique le numéro du vol." else null,
    kilos = if (kilos.toPositiveNumberOrNull() == null) "Indique un nombre positif de kilos." else null,
    pricePerKilo = if (pricePerKilo.toPositiveNumberOrNull() == null) "Indique un prix positif par kilo." else null,
    description = if (description.trim().length !in 10..500) "La description doit faire entre 10 et 500 caractères." else null,
)