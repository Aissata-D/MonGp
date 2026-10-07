package fr.sitadigi.core.usecase.conversation

import fr.sitadigi.core.data.ConversationRepository

class MarkMessagesAsReadUseCase(private val conversationRepository: ConversationRepository) {
    suspend operator fun invoke(conversationId: String) {
        return conversationRepository.markMessagesAsRead(conversationId)
    }
}