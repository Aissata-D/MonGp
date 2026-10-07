package fr.sitadigi.core.usecase.conversation

import fr.sitadigi.core.data.ConversationRepository
import fr.sitadigi.core.domain.Conversation

class CreateConversationUseCase(private val conversationRepository: ConversationRepository) {
    suspend operator fun invoke(tripId: String, firstMessage: String): Conversation{
        require(firstMessage.isNotBlank()){"Le message ne peut pas être vide."}
        return conversationRepository.createConversation(tripId, firstMessage)
    }
}