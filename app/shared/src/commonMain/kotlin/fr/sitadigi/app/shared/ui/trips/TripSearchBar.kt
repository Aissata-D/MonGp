package fr.sitadigi.app.shared.ui.trips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TripSearchBar(onSearch: (departureCity: String?, arrivalCity: String?) -> Unit) {
    var departureCity by rememberSaveable { mutableStateOf("") }
    var arrivalCity by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = departureCity,
            onValueChange = { departureCity = it },
            label = { Text("Ville de départ") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = arrivalCity,
            onValueChange = { arrivalCity = it },
            label = { Text("Ville d'arrivée") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            onSearch(departureCity.trim().ifBlank { null },
                arrivalCity.trim().ifBlank { null })
        }) {
            Text("Rechercher")
        }
    }
}