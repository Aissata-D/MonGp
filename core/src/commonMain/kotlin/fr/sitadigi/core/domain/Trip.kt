package fr.sitadigi.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class Trip(
    val id: String,
    val userId: String,
    val departureCity: String,
    val arrivalCity: String,
    val flightDateTime: String,
    val numberOfKiloAvailable: Double,
    val priceForOneKilo: Double,
    val flightNumber: String,
    val isDeleted: Boolean = false,
    val description: String,
    val publicationDateTime: String
)