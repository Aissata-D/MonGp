package fr.sitadigi.app.shared.presentation.ui.publish

import fr.sitadigi.core.domain.error.AppError

data class PublishTripUiState(
    val form: fr.sitadigi.app.shared.presentation.ui.publish.TripFormState = fr.sitadigi.app.shared.presentation.ui.publish.TripFormState(),
    val errors: fr.sitadigi.app.shared.presentation.ui.publish.TripFormErrors = fr.sitadigi.app.shared.presentation.ui.publish.TripFormErrors(),
    val isSubmitting: Boolean = false,
    val submitError: AppError? = null,
    val published: Boolean = false,
)