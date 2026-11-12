package fr.sitadigi.app.shared.ui.publish

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
import fr.sitadigi.app.shared.ui.components.MonGpButton
import fr.sitadigi.app.shared.ui.components.MonGpTextField
import fr.sitadigi.app.shared.ui.theme.MonGpTheme


@Composable
fun PublishTripScreen(
    form: TripFormState,
    errors: TripFormErrors,
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

            MonGpTextField(
                value = form.departureCity,
                onValueChange = { onFormChange(form.copy(departureCity = it)) },
                label = "Ville de départ",
                error = errors.departureCity,
                modifier = Modifier.fillMaxWidth()
            )
            MonGpTextField(
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
                MonGpTextField(
                    value = form.flightDate,
                    onValueChange = { onFormChange(form.copy(flightDate = it)) },
                    label = "Date du vol (AAAA-MM-JJ)",
                    error = errors.flightDate,
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number
                )
                MonGpTextField(
                    value = form.flightTime,
                    onValueChange = { onFormChange(form.copy(flightTime = it)) },
                    label = "Heure du vol (HH:MM)",
                    error = errors.flightTime,
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number
                )
            }
            MonGpTextField(
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
                MonGpTextField(
                    value = form.kilos,
                    onValueChange = { onFormChange(form.copy(kilos = it)) },
                    label = "Poids (kg)",
                    error = errors.kilos,
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Decimal
                )
                MonGpTextField(
                    value = form.pricePerKilo,
                    onValueChange = { onFormChange(form.copy(pricePerKilo = it)) },
                    label = "Prix au kilo (€)",
                    error = errors.pricePerKilo,
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Decimal
                )
            }
            MonGpTextField(
                value = form.description,
                onValueChange = { onFormChange(form.copy(description = it)) },
                label = "Description",
                error = errors.description,
                modifier = Modifier.fillMaxWidth(),
                minLines = 4
            )

            MonGpButton(
                text = "Publier",
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun PublishTripScreenPreview() {
    MonGpTheme {
        PublishTripScreen(
            form = TripFormState(),
            errors = TripFormErrors(),
            onFormChange = {},
            onSubmit = {},
            onBack = {},
        )
    }
}