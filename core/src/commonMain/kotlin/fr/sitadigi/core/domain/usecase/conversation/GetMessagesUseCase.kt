package fr.sitadigi.core.domain.usecase.conversation

import fr.sitadigi.core.domain.repository.ConversationRepository
import fr.sitadigi.core.domain.model.Message

class GetMessagesUseCase(private val conversationRepository: ConversationRepository) {

    suspend operator fun invoke(conversationId: String): List<Message> {
        return conversationRepository.getMessages(conversationId)
    }
}