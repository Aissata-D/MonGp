package fr.sitadigi.app.shared.ui.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.sitadigi.core.domain.error.AppError
import fr.sitadigi.core.usecase.trip.GetTripsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TripListViewModel(
    private val getTrips: GetTripsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TripListUiState>(TripListUiState.Loading)
    val uiState: StateFlow<TripListUiState> = _uiState.asStateFlow()

    init {
        loadTrips()
    }

    fun loadTrips(departureCity: String? = null, arrivalCity: String? = null) {
        viewModelScope.launch {
            _uiState.value = TripListUiState.Loading
            _uiState.value = try {
                TripListUiState.Success(getTrips(departureCity, arrivalCity))
            } catch (e: AppError) {
                TripListUiState.Error(e)
            }
        }
    }
}