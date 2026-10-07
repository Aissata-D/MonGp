package fr.sitadigi.core.usecase.conversation

import fr.sitadigi.core.data.ConversationRepository
import fr.sitadigi.core.domain.Message

class GetMessagesUseCase(private val conversationRepository: ConversationRepository) {

    suspend operator fun invoke(conversationId: String): List<Message> {
        return conversationRepository.getMessages(conversationId)
    }
}