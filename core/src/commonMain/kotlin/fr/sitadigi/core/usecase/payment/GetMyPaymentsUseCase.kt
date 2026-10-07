package fr.sitadigi.core.usecase.payment

import fr.sitadigi.core.data.PaymentRepository
import fr.sitadigi.core.domain.Payment

class GetMyPaymentsUseCase(private val paymentRepository: PaymentRepository) {
    suspend operator fun invoke(): List<Payment> {
        return paymentRepository.getMyPayments()
    }
}