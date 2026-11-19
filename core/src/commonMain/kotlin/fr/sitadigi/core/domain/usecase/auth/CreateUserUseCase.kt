package fr.sitadigi.core.domain.usecase.auth

import fr.sitadigi.core.domain.repository.AuthRepository
import fr.sitadigi.core.domain.model.User

class CreateUserUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(firstName: String, lastName: String, phoneNumber: String, email: String, password: String): User {
        return authRepository.createUser(firstName, lastName, phoneNumber, email, password)
    }
}
