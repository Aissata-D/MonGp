package fr.sitadigi.core.usecase.payment

import fr.sitadigi.core.data.PaymentRepository
import fr.sitadigi.core.domain.Payment

class GetAllPaymentsUseCase(private val paymentRepository: PaymentRepository) {
    suspend operator fun invoke(): List<Payment> {
        return paymentRepository.getAllPayments()
    }
}