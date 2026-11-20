package fr.sitadigi.app.shared.presentation.ui.trips

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

    private val _uiState = MutableStateFlow<fr.sitadigi.app.shared.presentation.ui.trips.TripListUiState>(
        fr.sitadigi.app.shared.presentation.ui.trips.TripListUiState.Loading)
    val uiState: StateFlow<fr.sitadigi.app.shared.presentation.ui.trips.TripListUiState> = _uiState.asStateFlow()
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
            _uiState.value = fr.sitadigi.app.shared.presentation.ui.trips.TripListUiState.Loading
            _uiState.value = try {
                fr.sitadigi.app.shared.presentation.ui.trips.TripListUiState.Success(getTrips(departureCity, arrivalCity))
            } catch (e: AppError) {
                fr.sitadigi.app.shared.presentation.ui.trips.TripListUiState.Error(e)
            }
        }
    }
}