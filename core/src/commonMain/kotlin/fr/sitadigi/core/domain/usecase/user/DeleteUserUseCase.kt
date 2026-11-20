package fr.sitadigi.core.domain.usecase.user

import fr.sitadigi.core.domain.repository.UserRepository

class DeleteUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: String) {
        userRepository.deleteUser(userId)
    }
}