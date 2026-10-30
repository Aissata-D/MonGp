package fr.sitadigi.app.shared.ui.trips

import fr.sitadigi.core.domain.Trip
import fr.sitadigi.core.domain.error.AppError

sealed interface TripDetailUiState {
    data object Loading : TripDetailUiState
    data class Success(val trip: Trip) : TripDetailUiState
    data class Error(val error: AppError) : TripDetailUiState
}