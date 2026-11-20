package fr.sitadigi.app.shared.presentation.ui.publish

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PublishTripScreen(
    form: TripFormState,
    errors: TripFormErrors,
    isSubmitting: Boolean,
    submitError: String?,
    onFormChange: (TripFormState) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextButton(onClick = onBack) {
            Text("← Retour")
        }
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Publier un voyage", style = MaterialTheme.typography.headlineMedium)

            fr.sitadigi.app.shared.presentation.ui.components.MonGpTextField(
                value = form.departureCity,
                onValueChange = { onFormChange(form.copy(departureCity = it)) },
                label = "Ville de départ",
                error = errors.departureCity,
                modifier = Modifier.fillMaxWidth()
            )
            fr.sitadigi.app.shared.presentation.ui.components.MonGpTextField(
                value = form.arrivalCity,
                onValueChange = { onFormChange(form.copy(arrivalCity = it)) },
                label = "Ville d'arrivée",
                error = errors.arrivalCity,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                fr.sitadigi.app.shared.presentation.ui.components.MonGpDateField(
                    value = form.flightDate,
                    onValueChange = { onFormChange(form.copy(flightDate = it)) },
                    label = "Date du vol",
                    error = errors.flightDate,
                    modifier = Modifier.weight(1f)
                )
                fr.sitadigi.app.shared.presentation.ui.components.MonGpTimeField(
                    value = form.flightTime,
                    onValueChange = { onFormChange(form.copy(flightTime = it)) },
                    label = "Heure du vol",
                    error = errors.flightTime,
                    modifier = Modifier.weight(1f)
                )

            }
            fr.sitadigi.app.shared.presentation.ui.components.MonGpTextField(
                value = form.flightNumber,
                onValueChange = { onFormChange(form.copy(flightNumber = it)) },
                label = "Numéro du vol",
                error = errors.flightNumber,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                fr.sitadigi.app.shared.presentation.ui.components.MonGpTextField(
                    value = form.kilos,
                    onValueChange = { onFormChange(form.copy(kilos = it)) },
                    label = "Poids (kg)",
                    error = errors.kilos,
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Decimal
                )
                fr.sitadigi.app.shared.presentation.ui.components.MonGpTextField(
                    value = form.pricePerKilo,
                    onValueChange = { onFormChange(form.copy(pricePerKilo = it)) },
                    label = "Prix au kilo (€)",
                    error = errors.pricePerKilo,
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Decimal
                )
            }
            fr.sitadigi.app.shared.presentation.ui.components.MonGpTextField(
                value = form.description,
                onValueChange = { onFormChange(form.copy(description = it)) },
                label = "Description",
                error = errors.description,
                modifier = Modifier.fillMaxWidth(),
                minLines = 4
            )

            fr.sitadigi.app.shared.presentation.ui.components.MonGpButton(
                text = if (isSubmitting) "Publication…" else "Publier",
                onClick = onSubmit,
                enabled = !isSubmitting,
                modifier = Modifier.fillMaxWidth()
            )
            if (submitError != null) {
                Text(
                    text = submitError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PublishTripScreenPreview() {
    fr.sitadigi.app.shared.presentation.ui.theme.MonGpTheme {
        PublishTripScreen(
            form = TripFormState(),
            errors = TripFormErrors(),
            isSubmitting = false,
            submitError = null,
            onFormChange = {},
            onSubmit = {},
            onBack = {},
        )
    }
}