package fr.sitadigi.app.shared.presentation.ui.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.sitadigi.core.domain.error.AppError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TripDetailViewModel(
    private val tripId: String,
    private val getTripById: fr.sitadigi.core.domain.usecase.trip.GetTripByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<fr.sitadigi.app.shared.presentation.ui.trips.TripDetailUiState>(
        fr.sitadigi.app.shared.presentation.ui.trips.TripDetailUiState.Loading)
    val uiState: StateFlow<fr.sitadigi.app.shared.presentation.ui.trips.TripDetailUiState> = _uiState.asStateFlow()

    init {
        loadTrip()
    }

    fun loadTrip() {
        viewModelScope.launch {
            _uiState.value = fr.sitadigi.app.shared.presentation.ui.trips.TripDetailUiState.Loading
            _uiState.value = try {
                fr.sitadigi.app.shared.presentation.ui.trips.TripDetailUiState.Success(getTripById(tripId))
            } catch (e: AppError) {
                fr.sitadigi.app.shared.presentation.ui.trips.TripDetailUiState.Error(e)
            }
        }
    }
}