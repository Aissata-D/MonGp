package fr.sitadigi.core.domain.usecase.auth

import fr.sitadigi.core.domain.repository.AuthRepository

class CreateTokenUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): String {
        return authRepository.createToken(email, password)
    }
}