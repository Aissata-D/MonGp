package fr.sitadigi.app.shared.ui.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.sitadigi.core.domain.error.AppError
import fr.sitadigi.core.usecase.trip.GetTripByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TripDetailViewModel(
    private val tripId: String,
    private val getTripById: GetTripByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TripDetailUiState>(TripDetailUiState.Loading)
    val uiState: StateFlow<TripDetailUiState> = _uiState.asStateFlow()

    init {
        loadTrip()
    }

    fun loadTrip() {
        viewModelScope.launch {
            _uiState.value = TripDetailUiState.Loading
            _uiState.value = try {
                TripDetailUiState.Success(getTripById(tripId))
            } catch (e: AppError) {
                TripDetailUiState.Error(e)
            }
        }
    }
}