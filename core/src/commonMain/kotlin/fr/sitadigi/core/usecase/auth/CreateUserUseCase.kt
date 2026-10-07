package fr.sitadigi.core.usecase.auth

import fr.sitadigi.core.data.AuthRepository
import fr.sitadigi.core.domain.User

class CreateUserUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(firstName: String, lastName: String, phoneNumber: String, email: String, password: String): User {
        return authRepository.createUser(firstName, lastName, phoneNumber, email, password)
    }
}
