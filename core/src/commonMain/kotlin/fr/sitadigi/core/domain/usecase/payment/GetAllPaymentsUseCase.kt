package fr.sitadigi.core.domain.usecase.payment

import fr.sitadigi.core.domain.repository.PaymentRepository
import fr.sitadigi.core.domain.model.Payment

class GetAllPaymentsUseCase(private val paymentRepository: PaymentRepository) {
    suspend operator fun invoke(): List<Payment> {
        return paymentRepository.getAllPayments()
    }
}