package fr.sitadigi.core.domain.usecase.conversation

import fr.sitadigi.core.domain.repository.ConversationRepository
import fr.sitadigi.core.domain.model.Conversation

class GetMyConversationsUseCase(private val conversationRepository: ConversationRepository) {

    suspend operator fun invoke(): List<Conversation> {
        return conversationRepository.getMyConversations()
    }
}