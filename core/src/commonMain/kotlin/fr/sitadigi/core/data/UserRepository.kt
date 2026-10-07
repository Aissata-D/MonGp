package fr.sitadigi.core.data

import fr.sitadigi.core.domain.User

interface UserRepository {
    suspend fun getAllUsers(): List<User>
    suspend fun getUserById(userId: String): User
    suspend fun updateUser(userId: String, firstName: String, lastName: String, phoneNumber: String, photoUrl: String? = null): User
    suspend fun deleteUser(userId: String)
}