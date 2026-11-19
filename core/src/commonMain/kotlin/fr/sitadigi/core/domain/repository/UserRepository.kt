package fr.sitadigi.core.domain.repository

import fr.sitadigi.core.domain.model.User

interface UserRepository {
    suspend fun getAllUsers(): List<User>
    suspend fun getUserById(userId: String): User
    suspend fun updateUser(userId: String, firstName: String, lastName: String, phoneNumber: String, photoUrl: String? = null): User
    suspend fun deleteUser(userId: String)
}