package fr.sitadigi.core.usecase.auth

import fr.sitadigi.core.data.AuthRepository

class SendPhoneNumberVerificationUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(phoneNumber: String): Boolean {
        return authRepository.sendPhoneNumberVerification(phoneNumber)
    }
}