package fr.sitadigi.core.data

import fr.sitadigi.core.domain.User

interface AuthRepository {
    suspend fun createUser(firstName: String, lastName: String, phoneNumber: String, email: String, password: String): User
    suspend fun createToken(email: String, password: String): String
    suspend fun sendPhoneNumberVerification(phoneNumber: String): Boolean
    suspend fun confirmPhoneNumber(phoneNumber: String, verificationCode: String): Boolean
}