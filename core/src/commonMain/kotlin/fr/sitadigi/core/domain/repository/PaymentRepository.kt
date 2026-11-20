package fr.sitadigi.core.domain.repository

import fr.sitadigi.core.domain.model.Payment

interface PaymentRepository {
    suspend fun getAllPayments(): List<Payment>
    suspend fun getMyPayments(): List<Payment>
}