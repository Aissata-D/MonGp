package fr.sitadigi.core.domain.usecase.conversation

import fr.sitadigi.core.domain.repository.ConversationRepository
import fr.sitadigi.core.domain.model.Message

class SendMessageUseCase(private val conversationRepository: ConversationRepository) {
    suspend operator fun invoke(conversationId: String, body: String): Message {
        require(body.isNotBlank()){"Le message ne peut pas être vide."}
        return conversationRepository.sendMessage(conversationId, body)
    }
}
