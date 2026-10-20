package fr.sitadigi.app.shared.ui.trips

import fr.sitadigi.core.domain.Trip
import fr.sitadigi.core.domain.error.AppError

sealed interface TripListUiState {
    data object Loading : TripListUiState
    data class Success(val trips: List<Trip>) : TripListUiState
    data class Error(val error: AppError) : TripListUiState
}