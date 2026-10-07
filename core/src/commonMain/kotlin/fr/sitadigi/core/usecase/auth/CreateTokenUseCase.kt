package fr.sitadigi.core.usecase.auth

import fr.sitadigi.core.data.AuthRepository

class CreateTokenUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): String {
        return authRepository.createToken(email, password)
    }
}