package fr.sitadigi.core.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
enum class AccessEventType {
    INITIAL_PURCHASE,
    RENEWAL,
    CANCELLATION,
    EXPIRATION
}

@Serializable
data class AccessEvent(
    val id: String,
    val accessId: String,
    val userId: String,
    val revenueCatEventId: String,
    val eventType: AccessEventType,
    val occurredAt: String,
    val rawPayload: JsonElement
)