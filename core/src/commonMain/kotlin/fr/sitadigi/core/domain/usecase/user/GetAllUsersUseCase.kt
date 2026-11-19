package fr.sitadigi.core.domain.usecase.user

import fr.sitadigi.core.domain.repository.UserRepository

class GetAllUsersUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getAllUsers()
}