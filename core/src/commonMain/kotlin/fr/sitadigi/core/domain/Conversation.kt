package fr.sitadigi.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class Conversation(
    val id: String,
    val tripId: String,
    val userSendingBaggageId: String,
    val creationDateTime: String
)