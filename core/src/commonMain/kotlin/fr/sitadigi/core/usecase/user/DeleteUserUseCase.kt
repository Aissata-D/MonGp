package fr.sitadigi.core.usecase.user

import fr.sitadigi.core.data.UserRepository

class DeleteUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: String) {
        userRepository.deleteUser(userId)
    }
}