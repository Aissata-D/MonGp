package fr.sitadigi.core.domain

import kotlinx.serialization.Serializable

@Serializable
enum class Role {
    ADMIN,
    USER
}

@Serializable
data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val isConversationFreeUsed: Boolean = false,
    val registerDate: String,
    val role: Role,
    val photoUrl: String? = null
)