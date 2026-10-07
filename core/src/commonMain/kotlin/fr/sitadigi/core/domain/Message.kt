package fr.sitadigi.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: String,
    val conversationId: String,
    val authorId: String,
    val body: String,
    val sendingDateTime: String,
    val isRead: Boolean = false
)