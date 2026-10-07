package fr.sitadigi.core.data

import fr.sitadigi.core.domain.Payment

interface PaymentRepository {
    suspend fun getAllPayments(): List<Payment>
    suspend fun getMyPayments(): List<Payment>
}