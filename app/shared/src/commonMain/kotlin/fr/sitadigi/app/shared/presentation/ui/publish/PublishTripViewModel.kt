package fr.sitadigi.app.shared.presentation.ui.publish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.sitadigi.app.shared.presentation.ui.publish.toPositiveNumberOrNull
import fr.sitadigi.app.shared.presentation.ui.publish.validate
import fr.sitadigi.core.domain.error.AppError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PublishTripViewModel(
    private val createTrip: fr.sitadigi.core.domain.usecase.trip.CreateTripUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(fr.sitadigi.app.shared.presentation.ui.publish.PublishTripUiState())
    val uiState: StateFlow<fr.sitadigi.app.shared.presentation.ui.publish.PublishTripUiState> = _uiState.asStateFlow()

    fun onFormChange(form: fr.sitadigi.app.shared.presentation.ui.publish.TripFormState) {
        _uiState.update { it.copy(form = form) }
    }

    fun submit() {
        val form = _uiState.value.form
        val errors = form.validate()
        val kilos = form.kilos.toPositiveNumberOrNull()
        val price = form.pricePerKilo.toPositiveNumberOrNull()

        // Une erreur de validation : on l'affiche, et on n'appelle pas le réseau.
        if (errors.hasErrors || kilos == null || price == null) {
            _uiState.update { it.copy(errors = errors, submitError = null) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(errors = errors, isSubmitting = true, submitError = null) }
            try {
                createTrip(
                    departureCity = form.departureCity.trim(),
                    arrivalCity = form.arrivalCity.trim(),
                    // Heure locale du pays de départ, sans fuseau : "2026-11-14T20:00:00".
                    flightDateTime = "${form.flightDate.trim()}T${form.flightTime.trim()}:00",
                    numberOfKiloAvailable = kilos,
                    priceForOneKilo = price,
                    flightNumber = form.flightNumber.trim(),
                    description = form.description.trim(),
                )
                _uiState.update { it.copy(isSubmitting = false, published = true) }
            } catch (e: AppError) {
                _uiState.update { it.copy(isSubmitting = false, submitError = e) }
            }
        }
    }
}