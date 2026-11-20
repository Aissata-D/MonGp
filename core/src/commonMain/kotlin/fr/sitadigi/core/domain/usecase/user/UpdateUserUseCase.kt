package fr.sitadigi.core.domain.usecase.user

import fr.sitadigi.core.domain.repository.UserRepository

class UpdateUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: String, firstName: String, lastName: String, phoneNumber: String, photoUrl: String? = null) =
        userRepository.updateUser(userId, firstName, lastName, phoneNumber, photoUrl)
}