package fr.sitadigi.app.shared.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import fr.sitadigi.app.shared.presentation.ui.components.toUtcDate
import fr.sitadigi.app.shared.presentation.ui.format.toFrenchDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Instant

private fun Long.toUtcDate() =
    Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC).date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonGpDateField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    error: String? = null,
) {
    var showPicker by remember { mutableStateOf(false) }

    // Le champ est en lecture seule : un calque transparent capte le toucher.
    Box(modifier = modifier) {
        fr.sitadigi.app.shared.presentation.ui.components.MonGpTextField(
            value = value.toFrenchDate(),
            onValueChange = {},
            label = label,
            error = error,
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Box(modifier = Modifier.matchParentSize().clickable { showPicker = true })
    }

    if (showPicker) {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val pickerState = rememberDatePickerState(
            selectableDates = object : SelectableDates {
                // Les dates passées ne sont pas sélectionnables.
                override fun isSelectableDate(utcTimeMillis: Long): Boolean =
                    utcTimeMillis.toUtcDate() >= today
            }
        )
        DatePickerDialog(
            onDismissRequest = { showPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    pickerState.selectedDateMillis?.let { millis ->
                        onValueChange(millis.toUtcDate().toString()) // AAAA-MM-JJ
                    }
                    showPicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showPicker = false }) {
                    Text("Annuler")
                }
            },
        ) {
            DatePicker(state = pickerState)
        }
    }
}