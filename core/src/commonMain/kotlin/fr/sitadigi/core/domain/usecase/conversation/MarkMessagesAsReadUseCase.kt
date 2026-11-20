package fr.sitadigi.core.domain.usecase.conversation

import fr.sitadigi.core.domain.repository.ConversationRepository

class MarkMessagesAsReadUseCase(private val conversationRepository: ConversationRepository) {
    suspend operator fun invoke(conversationId: String) {
        return conversationRepository.markMessagesAsRead(conversationId)
    }
}