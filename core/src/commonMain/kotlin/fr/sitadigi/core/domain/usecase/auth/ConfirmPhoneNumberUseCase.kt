package fr.sitadigi.core.domain.usecase.auth

import fr.sitadigi.core.domain.repository.AuthRepository

class ConfirmPhoneNumberUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(phoneNumber: String, verificationCode: String): Boolean {
        return authRepository.confirmPhoneNumber(phoneNumber, verificationCode)
    }
}