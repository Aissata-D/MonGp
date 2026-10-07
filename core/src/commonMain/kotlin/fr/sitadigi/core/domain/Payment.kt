package fr.sitadigi.core.domain

import kotlinx.serialization.Serializable

@Serializable
enum class PaymentEventType {
    INITIAL_PURCHASE,
    RENEWAL
}

@Serializable
data class Payment(
    val id: String,
    val userId: String,
    val revenueCatEventId: String,
    val transactionId: String,
    val eventType: PaymentEventType,
    val amountInCents: Int,
    val currency: String,
    val receivedAt: String
)