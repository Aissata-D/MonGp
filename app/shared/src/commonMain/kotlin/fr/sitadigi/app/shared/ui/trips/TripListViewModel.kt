package fr.sitadigi.app.shared.ui.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.sitadigi.core.domain.error.AppError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TripListViewModel(
    private val getTrips: fr.sitadigi.core.domain.usecase.trip.GetTripsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TripListUiState>(TripListUiState.Loading)
    val uiState: StateFlow<TripListUiState> = _uiState.asStateFlow()
    private var lastRefreshVersion = 0

    fun refreshIfNeeded(version: Int) {
        if (version > lastRefreshVersion) {
            lastRefreshVersion = version
            loadTrips()
        }
    }

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