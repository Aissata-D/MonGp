package fr.sitadigi.app.shared.presentation.ui.trips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.sitadigi.app.shared.presentation.ui.components.MonGpButton
import fr.sitadigi.app.shared.presentation.ui.components.MonGpTextField

@Composable
fun TripSearchBar(onSearch: (departureCity: String?, arrivalCity: String?) -> Unit) {
    var departureCity by rememberSaveable { mutableStateOf("") }
    var arrivalCity by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        fr.sitadigi.app.shared.presentation.ui.components.MonGpTextField(
            value = departureCity,
            onValueChange = { departureCity = it },
            label = "Ville de départ",
            modifier = Modifier.fillMaxWidth()
        )
        fr.sitadigi.app.shared.presentation.ui.components.MonGpTextField(
            value = arrivalCity,
            onValueChange = { arrivalCity = it },
            label = "Ville d'arrivée",
            modifier = Modifier.fillMaxWidth()
        )
        fr.sitadigi.app.shared.presentation.ui.components.MonGpButton(
            text = "Rechercher",
            onClick = {
                onSearch(
                    departureCity.trim().ifBlank { null },
                    arrivalCity.trim().ifBlank { null }
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}