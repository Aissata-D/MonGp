package fr.sitadigi.core.usecase.user

import fr.sitadigi.core.data.UserRepository

class GetAllUsersUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getAllUsers()
}