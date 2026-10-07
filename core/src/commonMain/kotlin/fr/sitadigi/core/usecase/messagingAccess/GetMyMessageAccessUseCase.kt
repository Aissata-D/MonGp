package fr.sitadigi.core.usecase.messagingAccess

import fr.sitadigi.core.data.MessagingAccessRepository
import fr.sitadigi.core.domain.Access

class GetMyMessageAccessUseCase(private val messagingAccessRepository: MessagingAccessRepository) {
    suspend operator fun invoke(): Access? {
        return messagingAccessRepository.getMyMessageAccess()
    }
}