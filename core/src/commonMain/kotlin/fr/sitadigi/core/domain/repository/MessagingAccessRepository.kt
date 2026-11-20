package fr.sitadigi.core.domain.repository

import fr.sitadigi.core.domain.model.Access

interface MessagingAccessRepository {
    suspend fun getMyMessageAccess(): Access?
}