package fr.sitadigi.app.shared.ui.trips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.sitadigi.app.shared.ui.components.MonGpButton
import fr.sitadigi.app.shared.ui.theme.plexMonoFamily
import fr.sitadigi.app.shared.ui.toMessage
import fr.sitadigi.core.domain.Trip

@Composable
fun TripDetailScreen(viewModel: TripDetailViewModel, onBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextButton(onClick = onBack) {
            Text("← Retour")
        }

        when (val state = uiState) {
            is TripDetailUiState.Loading -> {
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is TripDetailUiState.Success -> {
                TripDetailContent(state.trip)
            }
            is TripDetailUiState.Error -> {
                Column(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.error.toMessage(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                    MonGpButton(text = "Réessayer", onClick = { viewModel.loadTrip() })
                }
            }
        }
    }
}

@Composable
private fun TripDetailContent(trip: Trip) {
    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "${trip.departureCity} → ${trip.arrivalCity}",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = trip.flightDateTime,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DetailRow(label = "Vol", value = trip.flightNumber)
                DetailRow(label = "Kilos disponibles", value = "${trip.numberOfKiloAvailable} kg")
                DetailRow(label = "Prix indicatif", value = "${trip.priceForOneKilo} €/kg")
            }
        }
        Text(
            text = trip.description,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Le prix est indicatif : il se règle directement avec le voyageur.",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = plexMonoFamily())
        )
    }
}