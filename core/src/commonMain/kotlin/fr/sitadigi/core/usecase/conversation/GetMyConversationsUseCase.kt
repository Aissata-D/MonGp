package fr.sitadigi.core.usecase.conversation

import fr.sitadigi.core.data.ConversationRepository
import fr.sitadigi.core.domain.Conversation

class GetMyConversationsUseCase(private val conversationRepository: ConversationRepository) {

    suspend operator fun invoke(): List<Conversation> {
        return conversationRepository.getMyConversations()
    }
}