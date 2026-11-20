package fr.sitadigi.app.shared.presentation.ui.publish

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class TripFormValidationTest {

    private val validForm = TripFormState(
        departureCity = "Lyon",
        arrivalCity = "Bamako",
        flightDate = "2026-12-02",
        flightTime = "18:15",
        flightNumber = "AF722",
        kilos = "4.5",
        pricePerKilo = "12",
        description = "Vêtements et documents uniquement.",
    )

    @Test
    fun validForm_hasNoErrors() {
        assertFalse(validForm.validate().hasErrors)
    }

    @Test
    fun shortDescription_isRejected() {
        val errors = validForm.copy(description = "abc").validate()
        assertNotNull(errors.description)
    }

    @Test
    fun frenchDecimalComma_isAccepted() {
        val errors = validForm.copy(kilos = "4,5").validate()
        assertNull(errors.kilos)
    }
}