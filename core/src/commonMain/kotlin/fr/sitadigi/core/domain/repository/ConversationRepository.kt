package fr.sitadigi.core.domain.repository

import fr.sitadigi.core.domain.model.Conversation
import fr.sitadigi.core.domain.model.Message

interface ConversationRepository {
    suspend fun getMyConversations(): List<Conversation>
    suspend fun createConversation(tripId: String, firstMessage: String): Conversation
    suspend fun getMessages(conversationId: String): List<Message>
    suspend fun sendMessage(conversationId: String, body: String): Message
    suspend fun markMessagesAsRead(conversationId: String)
}