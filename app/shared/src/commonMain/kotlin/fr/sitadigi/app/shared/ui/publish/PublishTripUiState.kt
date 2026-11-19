package fr.sitadigi.app.shared.ui.publish

import fr.sitadigi.core.domain.error.AppError

data class PublishTripUiState(
    val form: TripFormState = TripFormState(),
    val errors: TripFormErrors = TripFormErrors(),
    val isSubmitting: Boolean = false,
    val submitError: AppError? = null,
    val published: Boolean = false,
)