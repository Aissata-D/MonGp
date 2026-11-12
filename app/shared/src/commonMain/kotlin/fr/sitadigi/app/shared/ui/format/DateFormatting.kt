package fr.sitadigi.app.shared.ui.format

private val isoDate = Regex("""(\d{4})-(\d{2})-(\d{2})""")
private val isoDateTime = Regex("""(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}).*""")

// "2026-11-14" devient "14/11/2026". Si le texte n'a pas ce format, on le rend tel quel.
fun String.toFrenchDate(): String =
    isoDate.matchEntire(this)?.destructured?.let { (year, month, day) ->
        "$day/$month/$year"
    } ?: this

// "2026-11-14T20:00:00Z" devient "14/11/2026 à 20h00".
// L'heure d'un vol est celle du pays de départ : on reformate le texte, on ne la convertit jamais.
fun String.toFrenchDateTime(): String =
    isoDateTime.matchEntire(this)?.destructured?.let { (year, month, day, hour, minute) ->
        "$day/$month/$year à ${hour}h$minute"
    } ?: this