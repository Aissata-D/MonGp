package fr.sitadigi.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class Access(
    val id: String,
    val userId: String,
    val startDate: String,
    val expirationDate: String,
    val revenueCatEventId: String
)