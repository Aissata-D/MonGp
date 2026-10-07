package fr.sitadigi.core.usecase.auth

import fr.sitadigi.core.data.AuthRepository

class ConfirmPhoneNumberUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(phoneNumber: String, verificationCode: String): Boolean {
        return authRepository.confirmPhoneNumber(phoneNumber, verificationCode)
    }
}