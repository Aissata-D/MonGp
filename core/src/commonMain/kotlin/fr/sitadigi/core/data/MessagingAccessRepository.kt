package fr.sitadigi.core.data

import fr.sitadigi.core.domain.Access

interface MessagingAccessRepository {
    suspend fun getMyMessageAccess(): Access?
}