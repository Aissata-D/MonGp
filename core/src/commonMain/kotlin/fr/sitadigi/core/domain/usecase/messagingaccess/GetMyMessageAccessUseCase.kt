package fr.sitadigi.core.domain.usecase.messagingaccess

import fr.sitadigi.core.domain.repository.MessagingAccessRepository
import fr.sitadigi.core.domain.model.Access

class GetMyMessageAccessUseCase(private val messagingAccessRepository: MessagingAccessRepository) {
    suspend operator fun invoke(): Access? {
        return messagingAccessRepository.getMyMessageAccess()
    }
}