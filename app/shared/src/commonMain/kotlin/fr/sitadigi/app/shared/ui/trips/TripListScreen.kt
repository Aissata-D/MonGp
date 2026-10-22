package fr.sitadigi.app.shared.ui.trips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.sitadigi.app.shared.ui.theme.plexMonoFamily
import fr.sitadigi.app.shared.ui.toMessage
import fr.sitadigi.core.domain.Trip

@Composable
fun TripListScreen(viewModel: TripListViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is TripListUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is TripListUiState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.trips) { trip ->
                    TripItem(trip)
                }
            }
        }
        is TripListUiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.error.toMessage(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }
                Button(onClick = { viewModel.loadTrips() }) {
                    Text("Réessayer")
                }

        }
    }
}

@Composable
fun TripItem(trip: Trip) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${trip.departureCity} → ${trip.arrivalCity}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = trip.flightDateTime,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "${trip.flightNumber} · ${trip.numberOfKiloAvailable} kg · ${trip.priceForOneKilo} €/kg",
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = plexMonoFamily())
            )
        }
    }
}

