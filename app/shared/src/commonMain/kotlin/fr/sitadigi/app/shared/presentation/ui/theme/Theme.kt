package fr.sitadigi.app.shared.presentation.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val MonGpColorScheme = lightColorScheme(
    primary = fr.sitadigi.app.shared.presentation.ui.theme.Indigo,
    onPrimary = fr.sitadigi.app.shared.presentation.ui.theme.White,
    primaryContainer = fr.sitadigi.app.shared.presentation.ui.theme.IndigoLight,
    onPrimaryContainer = fr.sitadigi.app.shared.presentation.ui.theme.White,
    secondary = fr.sitadigi.app.shared.presentation.ui.theme.Ochre,
    onSecondary = fr.sitadigi.app.shared.presentation.ui.theme.Indigo,
    secondaryContainer = fr.sitadigi.app.shared.presentation.ui.theme.OchreLight,
    onSecondaryContainer = fr.sitadigi.app.shared.presentation.ui.theme.Anthracite,
    tertiary = fr.sitadigi.app.shared.presentation.ui.theme.Green,
    onTertiary = fr.sitadigi.app.shared.presentation.ui.theme.White,
    tertiaryContainer = fr.sitadigi.app.shared.presentation.ui.theme.GreenLight,
    onTertiaryContainer = fr.sitadigi.app.shared.presentation.ui.theme.Green,
    error = fr.sitadigi.app.shared.presentation.ui.theme.Red,
    onError = fr.sitadigi.app.shared.presentation.ui.theme.White,
    errorContainer = fr.sitadigi.app.shared.presentation.ui.theme.RedLight,
    onErrorContainer = fr.sitadigi.app.shared.presentation.ui.theme.Red,
    background = fr.sitadigi.app.shared.presentation.ui.theme.OffWhite,
    onBackground = fr.sitadigi.app.shared.presentation.ui.theme.Anthracite,
    surface = fr.sitadigi.app.shared.presentation.ui.theme.White,
    onSurface = fr.sitadigi.app.shared.presentation.ui.theme.Anthracite,
    surfaceVariant = fr.sitadigi.app.shared.presentation.ui.theme.LightGray,
    onSurfaceVariant = fr.sitadigi.app.shared.presentation.ui.theme.Gray,
    outline = fr.sitadigi.app.shared.presentation.ui.theme.Gray,
    outlineVariant = fr.sitadigi.app.shared.presentation.ui.theme.LightGray,
    surfaceContainerHighest = fr.sitadigi.app.shared.presentation.ui.theme.White,
)

val MonGpShapes = Shapes(
    medium = RoundedCornerShape(14.dp),
    small = RoundedCornerShape(12.dp),
)

@Composable
fun MonGpTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = fr.sitadigi.app.shared.presentation.ui.theme.MonGpColorScheme,
        typography = fr.sitadigi.app.shared.presentation.ui.theme.monGpTypography(),
        shapes = fr.sitadigi.app.shared.presentation.ui.theme.MonGpShapes,
        content = content
    )
}